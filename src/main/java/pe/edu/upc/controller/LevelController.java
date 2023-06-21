package pe.edu.upc.controller;

import java.util.List;

import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import pe.edu.upc.model.Level;
import pe.edu.upc.service.ILevelService;

@CrossOrigin
@Api(tags="Level")
@RestController
@RequestMapping(path = "/levels")
public class LevelController {
	@Autowired
	private ILevelService levelService;
	
	@GetMapping(path = "", produces = "application/json")
	public ResponseEntity<?> list() {
		List<Level> levels = levelService.findAll();
		return ResponseEntity.ok(levels);
	}

	@GetMapping(path = "/{idLevel}", produces = "application/json")
	public ResponseEntity<?> listByIdLevel(@PathVariable int idLevel) {
		Level level = levelService.findById(idLevel);
		return ResponseEntity.ok(level);
	}

}
