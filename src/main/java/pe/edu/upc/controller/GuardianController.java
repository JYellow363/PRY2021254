package pe.edu.upc.controller;

import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import pe.edu.upc.dto.*;
import pe.edu.upc.service.IChildService;
import pe.edu.upc.service.IGuardianService;
import pe.edu.upc.service.IUserLoginService;
import pe.edu.upc.util.Constants;
import pe.edu.upc.util.JWTGenerator;

import java.util.List;

@CrossOrigin
@Api(tags="Guardian")
@RestController
@RequestMapping(path = "/guardians")
public class GuardianController {

	@Autowired
	private IGuardianService guardianService;

	@Autowired
	private IChildService childService;

	@GetMapping(path = "/{idGuardian}", produces = "application/json")
	public ResponseEntity<?> listByIdGuardian(@PathVariable int idGuardian) {
		GuardianDto guardian = guardianService.listByIdGuardian(idGuardian);
		return ResponseEntity.ok(guardian);
	}

	@GetMapping(path = "/{idGuardian}/children", produces = "application/json")
	public ResponseEntity<?> listChildrenByIdGuardian(@PathVariable int idGuardian) {
		List<ChildDto> children = childService.findByGuardianIdGuardian(idGuardian);
		return ResponseEntity.ok(children);
	}

	@PostMapping(path = "", consumes = "application/json", produces = "application/json")
	public ResponseEntity<?> create(@RequestBody GuardianCreateDto guardian) {
		int result = guardianService.save(guardian);
		ResponseDto response = new ResponseDto();
		response.setIdResponse(result);
		if (result == Constants.ERROR_DUPLICATE) {
			response.setMessage("Nombre de usuario duplicado");
			return ResponseEntity.ok(response);
		} else if (result == Constants.ERROR_BD) {
			response.setMessage("Error al registrar");
			return ResponseEntity.ok(response);
		} else {
			GuardianDto guardianCreate = guardianService.listByIdGuardian(result);
			return ResponseEntity.ok(guardianCreate);
		}
	}

	@PutMapping(path = "/{idGuardian}", consumes = "application/json", produces = "application/json")
	public ResponseEntity<?> update(@PathVariable int idGuardian, @RequestBody GuardianUpdateDto guardian) {
		guardian.setIdGuardian(idGuardian);
		int result = guardianService.update(guardian);
		ResponseDto response = new ResponseDto();
		response.setIdResponse(result);
		if (result == Constants.ERROR_BD) {
			response.setMessage("Error al actualizar");
			return ResponseEntity.ok(response);
		} else if (result == Constants.ERROR_PASSWORD) {
			response.setMessage("Contraseña incorrecta");
			return ResponseEntity.ok(response);
		} else {
			GuardianDto guardianUpdate = guardianService.listByIdGuardian(result);
			return ResponseEntity.ok(guardianUpdate);
		}
	}

}
