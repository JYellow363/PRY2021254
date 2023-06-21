package pe.edu.upc.controller;

import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import pe.edu.upc.dto.ResponseDto;
import pe.edu.upc.dto.SpecialistDto;
import pe.edu.upc.dto.UserLoginDto;
import pe.edu.upc.service.ISpecialistService;
import pe.edu.upc.service.IUserLoginService;
import pe.edu.upc.util.Constants;
import pe.edu.upc.util.JWTGenerator;

@CrossOrigin
@Api(tags="Specialist")
@RestController
@RequestMapping(path = "/specialists")
public class SpecialistController {
	
	@Autowired
	private ISpecialistService specialistService;
	
	@GetMapping(path = "/{idSpecialist}", produces = "application/json")
	public ResponseEntity<?> listByIdSpecialist(@PathVariable int idSpecialist) {
		SpecialistDto specialist = specialistService.listByIdSpecialist(idSpecialist);
		return ResponseEntity.ok(specialist);
	}

}
