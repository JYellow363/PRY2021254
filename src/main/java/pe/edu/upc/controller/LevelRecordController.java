package pe.edu.upc.controller;

import java.util.List;

import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import pe.edu.upc.dto.LevelHistoricalRecordDto;
import pe.edu.upc.dto.LevelRecordCreateDto;
import pe.edu.upc.dto.ResponseDto;
import pe.edu.upc.model.LevelRecord;
import pe.edu.upc.service.ILevelRecordService;
import pe.edu.upc.util.Constants;

@CrossOrigin
@Api(tags="LevelRecord")
@RestController
@RequestMapping(path = "/level-records")
public class LevelRecordController {
	@Autowired
	private ILevelRecordService levelRecordService;

	@PostMapping(path = "", consumes = "application/json", produces = "application/json")
	public ResponseEntity<?> create(@RequestBody LevelRecordCreateDto levelRecord) {
		int result = levelRecordService.save(levelRecord);
		ResponseDto response = new ResponseDto();
		response.setIdResponse(result);
		if (result == Constants.ERROR_BD) {
			response.setMessage("Error al registrar");
			return ResponseEntity.ok(response);
		} else {
			LevelRecord levelRecordCreate = levelRecordService.listById(result);
			return ResponseEntity.ok(levelRecordCreate);
		}
	}
}
