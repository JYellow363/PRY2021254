package pe.edu.upc.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pe.edu.upc.dto.LevelRecordCreateDto;
import pe.edu.upc.dto.ResponseDto;
import pe.edu.upc.model.Level;
import pe.edu.upc.model.LevelRecord;
import pe.edu.upc.service.ILevelRecordService;
import pe.edu.upc.util.Constants;

@CrossOrigin
@Api(tags="Level Records")
@RestController
@RequestMapping(path = "/level-records")
public class LevelRecordController {
	@Autowired
	private ILevelRecordService levelRecordService;

	@PostMapping(consumes = "application/json", produces = "application/json")
	@ApiResponses({
			@ApiResponse(code = 200, message="Ok", response = LevelRecord.class)
	})
	public ResponseEntity<?> create(@RequestBody LevelRecordCreateDto levelRecord) {
		int result = levelRecordService.save(levelRecord);
		ResponseDto response = new ResponseDto();
		response.setId(result);
		if (result == Constants.ERROR_BD) {
			response.setMessage("Error al registrar");
			return ResponseEntity.ok(response);
		} else {
			LevelRecord levelRecordCreate = levelRecordService.listById(result);
			return ResponseEntity.ok(levelRecordCreate);
		}
	}
}
