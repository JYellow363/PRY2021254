package pe.edu.upc.controller;

import java.util.List;

import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import pe.edu.upc.model.Symptom;
import pe.edu.upc.service.ISymptomService;

@CrossOrigin
@Api(tags="Symptom")
@RestController
@RequestMapping(path = "/symptoms")
public class SymptomController {

	@Autowired
	private ISymptomService symptomService;

	@GetMapping(path = "", produces = "application/json")
	public ResponseEntity<?> list() {
		List<Symptom> symptoms = symptomService.list();
		return ResponseEntity.ok(symptoms);
	}

	@GetMapping(path = "/{idSymptom}", produces = "application/json")
	public ResponseEntity<?> listByIdSymptom(@PathVariable int idSymptom) {
		Symptom symptom = symptomService.listByIdSymptom(idSymptom);
		return ResponseEntity.ok(symptom);
	}
}
