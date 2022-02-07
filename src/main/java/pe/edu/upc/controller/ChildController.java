package pe.edu.upc.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import pe.edu.upc.dto.ChildCreateDto;
import pe.edu.upc.dto.ChildDto;
import pe.edu.upc.dto.ChildUpdateDto;
import pe.edu.upc.dto.ResponseDto;
import pe.edu.upc.dto.SpecialistDto;
import pe.edu.upc.service.IChildService;
import pe.edu.upc.service.ISpecialistService;
import pe.edu.upc.util.Constants;

@CrossOrigin
@RestController
@RequestMapping(path = "/children")
public class ChildController {

	@Autowired
	private IChildService childService;
	
	@Autowired
	private ISpecialistService specialistService;

	@GetMapping(path = "/listByIdChild", produces = "application/json")
	public ResponseEntity<?> listByIdChild(@RequestParam int idChild) {
		ChildDto child = childService.findById(idChild);
		return ResponseEntity.ok(child);
	}

	@GetMapping(path = "/listByIdGuardian", produces = "application/json")
	public ResponseEntity<?> listByIdGuardian(@RequestParam int idGuardian) {
		List<ChildDto> children = childService.findByGuardianIdGuardian(idGuardian);
		return ResponseEntity.ok(children);
	}

	@PostMapping(path = "/create", consumes = "application/json", produces = "application/json")
	public ResponseEntity<?> create(@RequestBody ChildCreateDto child) {
		int result = childService.save(child);
		ResponseDto response = new ResponseDto();
		response.setIdResponse(result);
		if (result == Constants.ERROR_BD) {
			response.setMessage("Error al registrar");
			return ResponseEntity.ok(response);
		} else {
			ChildDto childCreate = childService.findById(result);
			return ResponseEntity.ok(childCreate);
		}
	}

	@PutMapping(path = "/update", consumes = "application/json", produces = "application/json")
	public ResponseEntity<?> update(@RequestBody ChildUpdateDto child) {
		int result = childService.update(child);
		ResponseDto response = new ResponseDto();
		response.setIdResponse(result);
		if (result == Constants.ERROR_BD) {
			response.setMessage("Error al actualizar");
			return ResponseEntity.ok(response);
		} else {
			ChildDto childUpdate = childService.findById(result);
			return ResponseEntity.ok(childUpdate);
		}
	}

	@GetMapping(path = "/delete", produces = "application/json")
	public ResponseEntity<?> delete(@RequestParam int idChild) {
		ResponseDto response = new ResponseDto();
		int result = childService.delete(idChild);
		response.setIdResponse(result);
		if (result == Constants.SUCCESSFULLY)
			response.setMessage("Eliminación exitosa");
		else
			response.setMessage("Error al eliminar");
		return ResponseEntity.ok(response);
	}

	@GetMapping(path = "/activateSpecialist", produces = "application/json")
	public ResponseEntity<?> activateSpecialist(@RequestParam int idChild) {
		ResponseDto response = new ResponseDto();
		int result = childService.activateSpecialist(idChild);
		response.setIdResponse(result);
		if (result == Constants.ERROR_BD) {
			response.setMessage("Error al activar especialista");
			return ResponseEntity.ok(response);
		} else if(result == Constants.ERROR_EMAIL) {
			response.setMessage("Error en el envío de credenciales");
			return ResponseEntity.ok(response);
		}else {
			SpecialistDto specialist = specialistService.listByIdSpecialist(result);
			return ResponseEntity.ok(specialist);
		}
		
	}

}
