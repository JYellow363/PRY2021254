package pe.edu.upc.controller;

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

import pe.edu.upc.dto.GuardianCreateDto;
import pe.edu.upc.dto.GuardianDto;
import pe.edu.upc.dto.GuardianUpdateDto;
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

	@PostMapping(path = "/login", consumes = "application/json", produces = "application/json")
	public ResponseEntity<?> login(@RequestBody UserLoginDto userLogin) {
		int result = userLoginService.loginGuardian(userLogin);
		ResponseDto response = new ResponseDto();
		response.setIdResponse(result);
		if (result == Constants.ERROR_PASSWORD) {
			response.setMessage("Contraseña incorrecta");
			return ResponseEntity.ok(response);
		} else if (result == Constants.ERROR_EXIST) {
			response.setMessage("El nombre de usuario no existe");
			return ResponseEntity.ok(response);
		} else {
			GuardianDto guardian = guardianService.listByIdGuardian(result);
			return ResponseEntity.ok(guardian);
		}
	}

	@PutMapping(path = "/update", consumes = "application/json", produces = "application/json")
	public ResponseEntity<?> update(@RequestBody GuardianUpdateDto guardian) {
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

	@GetMapping(path = "/restorePassword", produces = "application/json")
	public ResponseEntity<?> restorePassword(@RequestParam String email) {
		int result = userLoginService.restorePassword(email);
		ResponseDto response = new ResponseDto();
		response.setIdResponse(result);
		if (result == Constants.ERROR_EXIST) {
			response.setMessage("Email no registrado");
		} else if (result == Constants.ERROR_BD) {
			response.setMessage("Error al restaurar contraseña");
		} else if (result == Constants.ERROR_EMAIL) {
			response.setMessage("Error al enviar correo");
		} else {
			response.setMessage("Correo enviado");
		}
		return ResponseEntity.ok(response);
	}

}
