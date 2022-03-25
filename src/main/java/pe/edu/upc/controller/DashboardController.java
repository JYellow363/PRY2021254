package pe.edu.upc.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import pe.edu.upc.dto.LevelRecordDto;
import pe.edu.upc.dto.ObservationDto;
import pe.edu.upc.dto.ObservationUpdateDto;
import pe.edu.upc.dto.ResponseDto;
import pe.edu.upc.model.Observation;
import pe.edu.upc.service.ILevelRecordService;
import pe.edu.upc.service.IObservationService;
import pe.edu.upc.util.Constants;

@CrossOrigin
@RestController
@RequestMapping(path = "/dashboard")
public class DashboardController {
	@Autowired
	private ILevelRecordService levelRecordService;
	
	@Autowired
	private IObservationService observationService;

	// Monitoring by category
	@GetMapping(path = "/getDashboardCategory", produces = "application/json")
	public ResponseEntity<?> getDashboardCategory(@RequestParam int idChild) {
		List<LevelRecordDto> levelRecords = levelRecordService.listByChildrenForCategory(idChild);
		return ResponseEntity.ok(levelRecords);
	}

	// Monitoring by topic
	@GetMapping(path = "/getDashboardTopic", produces = "application/json")
	public ResponseEntity<?> getDashboardTopic(@RequestParam int idChild, @RequestParam int idCategory) {
		List<LevelRecordDto> levelRecords = levelRecordService.listByChildrenForTopic(idChild, idCategory);
		return ResponseEntity.ok(levelRecords);
	}

	// Monitoring by level
	@GetMapping(path = "/getDashboardLevel", produces = "application/json")
	public ResponseEntity<?> getDashboardLevel(@RequestParam int idChild, @RequestParam int idTopic) {
		List<LevelRecordDto> levelRecords = levelRecordService.listByChildrenForLevel(idChild, idTopic);
		return ResponseEntity.ok(levelRecords);
	}
	
	@GetMapping(path = "/observations/listByIdChild", produces = "application/json")
	public ResponseEntity<?> listByIdChild(@RequestParam int idChild) {
		List<Observation> observations = observationService.listByIdChild(idChild);
		return ResponseEntity.ok(observations);
	}

	@GetMapping(path = "/observations/listById", produces = "application/json")
	public ResponseEntity<?> listByIdGuardian(@RequestParam int idObservation) {
		Observation observation = observationService.listById(idObservation);
		return ResponseEntity.ok(observation);
	}
	
	@PostMapping(path = "/observations/create", consumes = "application/json", produces = "application/json")
	public ResponseEntity<?> create(@RequestBody ObservationDto observation) {
		int result = observationService.save(observation);
		ResponseDto response = new ResponseDto();
		response.setIdResponse(result);
		if (result == Constants.ERROR_BD) {
			response.setMessage("Error al registrar");
			return ResponseEntity.ok(response);
		} else {
			Observation observationCreate = observationService.listById(result);
			return ResponseEntity.ok(observationCreate);
		}
	}

	@PutMapping(path = "/observations/update", consumes = "application/json", produces = "application/json")
	public ResponseEntity<?> update(@RequestBody ObservationUpdateDto observation) {
		int result = observationService.update(observation);
		ResponseDto response = new ResponseDto();
		response.setIdResponse(result);
		if (result == Constants.ERROR_BD) {
			response.setMessage("Error al actualizar");
			return ResponseEntity.ok(response);
		} else {
			Observation observationUpdate = observationService.listById(result);
			return ResponseEntity.ok(observationUpdate);
		}
	}
	
	@DeleteMapping(path = "/observations/delete", produces = "application/json")
	public ResponseEntity<?> delete(@RequestParam int idObservation) {
		ResponseDto response = new ResponseDto();
		int result = observationService.delete(idObservation);
		response.setIdResponse(result);
		if (result == Constants.SUCCESSFULLY)
			response.setMessage("Eliminación exitosa");
		else
			response.setMessage("Error al eliminar");
		return ResponseEntity.ok(response);
	}

}
