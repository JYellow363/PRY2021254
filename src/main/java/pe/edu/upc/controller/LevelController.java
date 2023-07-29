package pe.edu.upc.controller;

import java.util.List;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import pe.edu.upc.dto.GuardianDto;
import pe.edu.upc.model.Category;
import pe.edu.upc.model.Level;
import pe.edu.upc.service.ILevelService;

@CrossOrigin
@Api(tags="Levels")
@RestController
@RequestMapping(path = "/levels")
public class LevelController {
	@Autowired
	private ILevelService levelService;
	
	@GetMapping(produces = "application/json")
	@ApiResponses({
			@ApiResponse(code = 200, message="Ok", responseContainer = "list", response = Level.class)
	})
	public ResponseEntity<?> list() {
		List<Level> levels = levelService.findAll();
		return ResponseEntity.ok(levels);
	}

	@GetMapping(path = "/{id}", produces = "application/json")
	@ApiResponses({
			@ApiResponse(code = 200, message="Ok", response = Level.class)
	})
	public ResponseEntity<?> listByIdLevel(@PathVariable int id) {
		Level level = levelService.findById(id);
		return ResponseEntity.ok(level);
	}

}
