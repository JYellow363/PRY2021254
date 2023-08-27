package pe.edu.upc.controller;

import java.util.List;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import pe.edu.upc.dto.SpecialistDto;
import pe.edu.upc.model.Category;
import pe.edu.upc.model.Symptom;
import pe.edu.upc.service.ISymptomService;

@CrossOrigin
@Api(tags="Symptoms")
@RestController
@RequestMapping(path = "/symptoms")
public class SymptomController {

	@Autowired
	private ISymptomService symptomService;

	@GetMapping(produces = "application/json")
	@ApiResponses({
			@ApiResponse(code = 200, message="Ok", responseContainer = "list", response = Symptom.class)
	})
	public ResponseEntity<?> list() {
		List<Symptom> symptoms = symptomService.list();
		return ResponseEntity.ok(symptoms);
	}

	@GetMapping(path = "/{id}", produces = "application/json")
	@ApiResponses({
			@ApiResponse(code = 200, message="Ok", response = Symptom.class)
	})
	public ResponseEntity<?> listByIdSymptom(@PathVariable int id) {
		Symptom symptom = symptomService.listBySymptomId(id);
		return ResponseEntity.ok(symptom);
	}
}
