package pe.edu.upc.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import pe.edu.upc.model.Level;
import pe.edu.upc.service.ILevelService;

@CrossOrigin
@RestController
@RequestMapping(path = "/levels")
public class LevelController {
	@Autowired
	private ILevelService levelService;
	
	@GetMapping(path = "/list", produces = "application/json")
	public ResponseEntity<?> list() {
		List<Level> levels = levelService.findAll();
		return ResponseEntity.ok(levels);
	}

	@GetMapping(path = "/listByIdLevel", produces = "application/json")
	public ResponseEntity<?> listByIdLevel(@RequestParam int idLevel) {
		Level level = levelService.findById(idLevel);
		return ResponseEntity.ok(level);
	}
	
	@GetMapping(path = "/listByIdTopic", produces = "application/json")
	public ResponseEntity<?> listByIdTopic(@RequestParam int idTopic) {
		List<Level> levels = levelService.findByIdTopic(idTopic);
		return ResponseEntity.ok(levels);
	}
}
