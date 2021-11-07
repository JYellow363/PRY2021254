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

import pe.edu.upc.dto.GuardianCreateDto;
import pe.edu.upc.dto.GuardianDto;
import pe.edu.upc.dto.ResponseDto;
import pe.edu.upc.dto.UserLoginDto;
import pe.edu.upc.service.IGuardianService;
import pe.edu.upc.service.IUserLoginService;
import pe.edu.upc.util.Constants;

@CrossOrigin
@RestController
@RequestMapping(path = "/guardians")
public class GuardianController {

	@Autowired
	private IGuardianService guardianService;

	@Autowired
	private IUserLoginService userLoginService;

	@GetMapping(path = "/listByIdGuardian", produces = "application/json")
	public ResponseEntity<?> listByIdGuardian(@RequestParam int idGuardian) {
		GuardianDto guardian = guardianService.listByIdGuardian(idGuardian);
		return ResponseEntity.ok(guardian);
	}

	@PostMapping(path = "/create", consumes = "application/json", produces = "application/json")
	public ResponseEntity<?> create(@RequestBody GuardianCreateDto guardian) {
		int result = guardianService.save(guardian);
		ResponseDto response = new ResponseDto();
		response.setIdResponse(result);
		if (result == Constants.SUCCESSFULLY)
			response.setMessage("Registro exitoso");
		else
			response.setMessage("Nombre de usuario duplicado");
		return ResponseEntity.ok(response);
	}

	@PostMapping(path = "/login", consumes = "application/json", produces = "application/json")
	public ResponseEntity<?> login(@RequestBody UserLoginDto userLogin) {
		int result = userLoginService.login(userLogin);
		ResponseDto response = new ResponseDto();
		response.setIdResponse(result);
		if (result == Constants.ERROR_PASSWORD)
			response.setMessage("Contraseña incorrecta");
		else if (result == Constants.ERROR_EXIST)
			response.setMessage("El nombre de usuario no existe");
		else
			response.setMessage("Login exitoso");
		return ResponseEntity.ok(response);
	}
}
