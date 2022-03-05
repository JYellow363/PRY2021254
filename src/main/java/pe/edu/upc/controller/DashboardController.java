package pe.edu.upc.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import pe.edu.upc.dto.LevelRecordDto;
import pe.edu.upc.service.ILevelRecordService;

@CrossOrigin
@RestController
@RequestMapping(path = "/dashboard")
public class DashboardController {
	@Autowired
	private ILevelRecordService levelRecordService;

	// Monitoring by category
	@GetMapping(path = "/getDashboardCategory", produces = "application/json")
	public ResponseEntity<?> getDashboardCategory(@RequestParam int idChildren) {
		List<LevelRecordDto> levelRecords = levelRecordService.listByChildrenForCategory(idChildren);
		return ResponseEntity.ok(levelRecords);
	}

	// Monitoring by topic
	@GetMapping(path = "/getDashboardTopic", produces = "application/json")
	public ResponseEntity<?> getDashboardTopic(@RequestParam int idChildren, @RequestParam int idCategory) {
		List<LevelRecordDto> levelRecords = levelRecordService.listByChildrenForTopic(idChildren, idCategory);
		return ResponseEntity.ok(levelRecords);
	}

	// Monitoring by level
	@GetMapping(path = "/getDashboardLevel", produces = "application/json")
	public ResponseEntity<?> getDashboardLevel(@RequestParam int idChildren, @RequestParam int idTopic) {
		List<LevelRecordDto> levelRecords = levelRecordService.listByChildrenForLevel(idChildren, idTopic);
		return ResponseEntity.ok(levelRecords);
	}
}
