package pe.edu.upc.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pe.edu.upc.dto.*;
import pe.edu.upc.model.Category;
import pe.edu.upc.service.IChildService;
import pe.edu.upc.service.IGuardianService;
import pe.edu.upc.util.Constants;

import java.util.List;

@CrossOrigin
@Api(tags="Guardians")
@RestController
@RequestMapping(path = "/guardians")
public class GuardianController {

	@Autowired
	private IGuardianService guardianService;

	@Autowired
	private IChildService childService;

	@GetMapping(path = "/{id}", produces = "application/json")
	@ApiResponses({
			@ApiResponse(code = 200, message="Ok", response = GuardianDto.class)
	})
	public ResponseEntity<?> listByIdGuardian(@PathVariable int id) {
		GuardianDto guardian = guardianService.listByIdGuardian(id);
		return ResponseEntity.ok(guardian);
	}

	@GetMapping(path = "/{id}/children", produces = "application/json")
	@ApiResponses({
			@ApiResponse(code = 200, message="Ok", responseContainer = "list", response = ChildDto.class)
	})
	public ResponseEntity<?> listChildrenByIdGuardian(@PathVariable int id) {
		List<ChildDto> children = childService.findByGuardianIdGuardian(id);
		return ResponseEntity.ok(children);
	}

	@PostMapping(consumes = "application/json", produces = "application/json")
	@ApiResponses({
			@ApiResponse(code = 200, message="Ok", response = GuardianDto.class)
	})
	public ResponseEntity<?> create(@RequestBody GuardianCreateDto guardian) {
		int result = guardianService.save(guardian);
		ResponseDto response = new ResponseDto();
		response.setId(result);
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

	@PutMapping(path = "/{id}", consumes = "application/json", produces = "application/json")
	@ApiResponses({
			@ApiResponse(code = 200, message="Ok", response = GuardianDto.class)
	})
	public ResponseEntity<?> update(@PathVariable int id, @RequestBody GuardianUpdateDto guardian) {
		guardian.setId(id);
		int result = guardianService.update(guardian);
		ResponseDto response = new ResponseDto();
		response.setId(result);
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
