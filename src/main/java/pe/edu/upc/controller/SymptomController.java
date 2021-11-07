package pe.edu.upc.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import pe.edu.upc.model.Symptom;
import pe.edu.upc.service.ISymptomService;

@CrossOrigin
@RestController
@RequestMapping(path = "/symptoms")
public class SymptomController {

	@Autowired
	private ISymptomService symptomService;

	@GetMapping(path = "/list", produces = "application/json")
	public ResponseEntity<?> list() {
		List<Symptom> symptoms = symptomService.list();
		return ResponseEntity.ok(symptoms);
	}

	@GetMapping(path = "/listByIdSymptom", produces = "application/json")
	public ResponseEntity<?> listByIdSymptom(@RequestParam int idSymptom) {
		Symptom symptom = symptomService.listByIdSymptom(idSymptom);
		return ResponseEntity.ok(symptom);
	}
}
