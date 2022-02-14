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

import pe.edu.upc.dto.ResponseDto;
import pe.edu.upc.dto.SpecialistDto;
import pe.edu.upc.dto.UserLoginDto;
import pe.edu.upc.service.ISpecialistService;
import pe.edu.upc.service.IUserLoginService;
import pe.edu.upc.util.Constants;
import pe.edu.upc.util.JWTGenerator;

@CrossOrigin
@RestController
@RequestMapping(path = "/specialists")
public class SpecialistController {
	
	@Autowired
	private ISpecialistService specialistService;
	
	@Autowired
	private IUserLoginService userLoginService;
	
	@GetMapping(path = "/listByIdSpecialist", produces = "application/json")
	public ResponseEntity<?> listByIdSpecialist(@RequestParam int idSpecialist) {
		SpecialistDto specialist = specialistService.listByIdSpecialist(idSpecialist);
		return ResponseEntity.ok(specialist);
	}
	
	@GetMapping(path = "/listByIdChild", produces = "application/json")
	public ResponseEntity<?> listByIdChild(@RequestParam int idChild) {
		SpecialistDto specialist = specialistService.listByIdChild(idChild);
		return ResponseEntity.ok(specialist);
	}
	
	@PostMapping(path = "/login", consumes = "application/json", produces = "application/json")
	public ResponseEntity<?> login(@RequestBody UserLoginDto userLogin) {
		int result = userLoginService.loginSpecialist(userLogin);
		ResponseDto response = new ResponseDto();
		response.setIdResponse(result);
		if (result == Constants.ERROR_PASSWORD) {
			response.setMessage("Contraseña incorrecta");
			return ResponseEntity.ok(response);
		} else if (result == Constants.ERROR_EXIST) {
			response.setMessage("El nombre de usuario no existe");
			return ResponseEntity.ok(response);
		} else {
			String token = JWTGenerator.getJWTToken(userLogin.getUsername(), userLogin.getPassword());
			response.setToken(token);
			response.setSpecialist(specialistService.listByIdSpecialist(result));
			return ResponseEntity.ok(response);
		}
	}
}
