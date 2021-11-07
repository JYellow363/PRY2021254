package pe.edu.upc.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import pe.edu.upc.dto.ChildCreateDto;
import pe.edu.upc.dto.ChildDto;
import pe.edu.upc.dto.ResponseDto;
import pe.edu.upc.service.IChildService;
import pe.edu.upc.util.Constants;

@CrossOrigin
@RestController
@RequestMapping(path = "/children")
public class ChildController {
	
	@Autowired
	private IChildService childService;
	
	@GetMapping(path = "/listByIdChild", produces = "application/json")
	public ResponseEntity<?> listByIdChild(@RequestParam int idChild) {
		ChildDto child = childService.findById(idChild);
		return ResponseEntity.ok(child);
	}

	@PostMapping(path = "/create", consumes = "application/json", produces = "application/json")
	public ResponseEntity<?> create(@RequestBody ChildCreateDto child) {
		int result = childService.save(child);
		ResponseDto response = new ResponseDto();
		response.setIdResponse(result);
		if (result == Constants.SUCCESSFULLY)
			response.setMessage("Registro exitoso");
		else
			response.setMessage("Error al registrar");
		return ResponseEntity.ok(response);
	}

}
