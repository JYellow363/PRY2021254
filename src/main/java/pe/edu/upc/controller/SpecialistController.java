package pe.edu.upc.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pe.edu.upc.dto.SpecialistDto;
import pe.edu.upc.model.Category;
import pe.edu.upc.service.ISpecialistService;

@CrossOrigin
@Api(tags="Specialists")
@RestController
@RequestMapping(path = "/specialists")
public class SpecialistController {
	
	@Autowired
	private ISpecialistService specialistService;
	
	@GetMapping(path = "/{id}", produces = "application/json")
	@ApiResponses({
			@ApiResponse(code = 200, message="Ok", response = SpecialistDto.class)
	})
	public ResponseEntity<?> listByIdSpecialist(@PathVariable int id) {
		SpecialistDto specialist = specialistService.listByIdSpecialist(id);
		return ResponseEntity.ok(specialist);
	}

}
