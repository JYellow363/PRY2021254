package pe.edu.upc.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import pe.edu.upc.dto.LevelRecordDto;
import pe.edu.upc.service.ILevelRecordService;

@Controller
@RequestMapping("/dashboard")
public class DashboardController {
	@Autowired
	private ILevelRecordService levelRecordService;
	
	// Monitoring by category
	@GetMapping(path = "/getDashboarCategory", produces = "application/json")
	public ResponseEntity<?> getDashboarCategory(@RequestParam int idChildren) {
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
