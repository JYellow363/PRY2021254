package pe.edu.upc.controller;

import java.util.List;

import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import pe.edu.upc.dto.*;
import pe.edu.upc.model.CustomLevelList;
import pe.edu.upc.model.Level;
import pe.edu.upc.model.Observation;
import pe.edu.upc.service.IChildService;
import pe.edu.upc.service.ILevelRecordService;
import pe.edu.upc.service.IObservationService;
import pe.edu.upc.service.ISpecialistService;
import pe.edu.upc.util.Constants;

@CrossOrigin
@Api(tags="Child")
@RestController
@RequestMapping(path = "/children")
public class ChildController {

	@Autowired
	private IChildService childService;

	@Autowired
	private ISpecialistService specialistService;

	@Autowired
	private IObservationService observationService;

	@Autowired
	private ILevelRecordService levelRecordService;

	@GetMapping(path = "/{idChild}", produces = "application/json")
	public ResponseEntity<?> listByIdChild(@PathVariable int idChild) {
		ChildDto child = childService.findById(idChild);
		return ResponseEntity.ok(child);
	}

	@PostMapping(path = "", consumes = "application/json", produces = "application/json")
	public ResponseEntity<?> create(@RequestBody ChildCreateDto child) {
		int result = childService.save(child);
		ResponseDto response = new ResponseDto();
		response.setIdResponse(result);
		if (result == Constants.ERROR_BD) {
			response.setMessage("Error al registrar");
			return ResponseEntity.ok(response);
		} else {
			ChildDto childCreate = childService.findById(result);
			return ResponseEntity.ok(childCreate);
		}
	}

	@PutMapping(path = "/{idChild}", consumes = "application/json", produces = "application/json")
	public ResponseEntity<?> update(@PathVariable int idChild, @RequestBody ChildUpdateDto child) {
		child.setIdChild(idChild);
		int result = childService.update(child);
		ResponseDto response = new ResponseDto();
		response.setIdResponse(result);
		if (result == Constants.ERROR_BD) {
			response.setMessage("Error al actualizar");
			return ResponseEntity.ok(response);
		} else {
			ChildDto childUpdate = childService.findById(result);
			return ResponseEntity.ok(childUpdate);
		}
	}
	
	@PatchMapping(path = "/{idChild}", consumes = "application/json", produces = "application/json")
	public ResponseEntity<?> updateSpecialCategoryName(@PathVariable int idChild, @RequestBody String name) {
		SpecialCategoryDto specialCategoryDto = new SpecialCategoryDto(name, idChild);
		int result = childService.updateSpecialCategoryName(specialCategoryDto);
		ResponseDto response = new ResponseDto();
		response.setIdResponse(result);
		if (result == Constants.ERROR_BD) {
			response.setMessage("Error al actualizar");
			return ResponseEntity.ok(response);
		} else {
			ChildDto childUpdate = childService.findById(result);
			return ResponseEntity.ok(childUpdate);
		}
	}

	@DeleteMapping(path = "/{idChild}", produces = "application/json")
	public ResponseEntity<?> delete(@PathVariable int idChild) {
		ResponseDto response = new ResponseDto();
		int result = childService.delete(idChild);
		response.setIdResponse(result);
		if (result == Constants.SUCCESSFULLY)
			response.setMessage("Eliminación exitosa");
		else
			response.setMessage("Error al eliminar");
		return ResponseEntity.ok(response);
	}

	@PostMapping(path = "/{idChild}/specialists", produces = "application/json")
	public ResponseEntity<?> createSpecialist(@PathVariable int idChild) {
		ResponseDto response = new ResponseDto();
		int result = childService.activateSpecialist(idChild);
		response.setIdResponse(result);
		if (result == Constants.ERROR_BD) {
			response.setMessage("Error al activar especialista");
			return ResponseEntity.ok(response);
		} else if (result == Constants.ERROR_EMAIL) {
			response.setMessage("Error en el envío de credenciales");
			return ResponseEntity.ok(response);
		} else if (result == Constants.ERROR_DUPLICATE) {
			response.setMessage("El niño ya tiene especialista creado");
			return ResponseEntity.ok(response);
		} else {
			SpecialistDto specialist = specialistService.listByIdSpecialist(result);
			return ResponseEntity.ok(specialist);
		}
	}

	@PostMapping(path = "/{idChild}/favorite-levels", consumes = "application/json", produces = "application/json")
	public ResponseEntity<?> addFavoriteLevel(@PathVariable int idChild, @RequestBody int idLevel) {
		try {
			ResponseDto response = new ResponseDto();
			AddLevelDto addLevelDto = new AddLevelDto(idChild, idLevel);
			int result = childService.addFavoriteLevel(addLevelDto.getIdChild(), addLevelDto.getIdLevel());
			response.setIdResponse(result);
			if (result == Constants.ERROR_BD) {
				response.setMessage("Error al agregar nivel");
			} else if (result == Constants.ERROR_DUPLICATE) {
				response.setMessage("El nivel ya está agregado");
			} else {
				response.setMessage("Nivel agregado correctamente");
			}
			return ResponseEntity.ok(response);
		} catch(Exception e) {
			System.out.println(e.getMessage());
			return ResponseEntity.ok(new ResponseDto());
		}
		
	}

	@DeleteMapping(path = "/{idChild}/favorite-levels/{idLevel}", produces = "application/json")
	public ResponseEntity<?> deleteFavoriteLevel(@PathVariable int idChild, @PathVariable int idLevel) {
		ResponseDto response = new ResponseDto();
		AddLevelDto addLevelDto = new AddLevelDto(idChild, idLevel);
		int result = childService.deleteFavoriteLevel(addLevelDto);
		response.setIdResponse(result);
		if (result == Constants.SUCCESSFULLY)
			response.setMessage("Eliminación exitosa");
		else
			response.setMessage("Error al eliminar");
		return ResponseEntity.ok(response);
	}

	@GetMapping(path = "/{idChild}/favorite-levels", produces = "application/json")
	public ResponseEntity<?> listFavoriteLevels(@PathVariable int idChild) {
		List<Level> levels = childService.listFavoriteLevels(idChild);
		return ResponseEntity.ok(levels);
	}

	@PostMapping(path = "/{idChild}/custom-level-lists", consumes = "application/json", produces = "application/json")
	public ResponseEntity<?> addCustomLevelList(@PathVariable int idChild, @RequestBody String name) {
		AddCustomLevelListDto addCustomLevelDto = new AddCustomLevelListDto(idChild, name);
		ResponseDto response = new ResponseDto();
		int result = childService.addCustomLevelList(addCustomLevelDto);
		response.setIdResponse(result);
		if (result == Constants.ERROR_BD) {
			response.setMessage("Error al agregar lista personalizada");
		} else {
			response.setMessage("Lista agregada correctamente");
		}
		return ResponseEntity.ok(response);
	}

	@GetMapping(path = "/{idChild}/custom-level-lists", produces = "application/json")
	public ResponseEntity<?> listCustomLevels(@PathVariable int idChild) {
		List<CustomLevelList> customLevelLists = childService.listCustomLevelLists(idChild);
		return ResponseEntity.ok(customLevelLists);
	}

	@GetMapping(path = "/{idChild}/custom-level-lists/{idCustomLevelList}", produces = "application/json")
	public ResponseEntity<?> listCustomLevelListById(@PathVariable int idCustomLevelList) {

		CustomLevelList customLevelList = childService.listCustomLevelListById(idCustomLevelList);
		return ResponseEntity.ok(customLevelList);
	}

	@DeleteMapping(path = "/{idChild}/custom-level-lists/{idCustomLevelList}", produces = "application/json")
	public ResponseEntity<?> deleteCustomLevelList(@PathVariable int idChild, @PathVariable int idCustomLevelList) {
		ResponseDto response = new ResponseDto();
		int result = childService.deleteCustomLevelList(idChild, idCustomLevelList);
		response.setIdResponse(result);
		if (result == Constants.SUCCESSFULLY)
			response.setMessage("Eliminación exitosa");
		else
			response.setMessage("Error al eliminar");
		return ResponseEntity.ok(response);
	}

	@GetMapping(path = "/{idChild}/observations", produces = "application/json")
	public ResponseEntity<?> listObservationsByIdChild(@PathVariable int idChild) {
		List<Observation> observations = observationService.listByIdChild(idChild);
		return ResponseEntity.ok(observations);
	}

	@GetMapping(path = "/{idChild}/level-records", produces = "application/json")
	public ResponseEntity<?> listLevelRecordsByIdChild(@PathVariable int idChild) {
		List<LevelHistoricalRecordDto> levelRecords = levelRecordService.listByIdChild(idChild);
		return ResponseEntity.ok(levelRecords);
	}

	@GetMapping(path = "/{idChild}/dashboard/level-records", produces = "application/json")
	public ResponseEntity<?> getDashboardCategory(@PathVariable int idChild) {
		List<LevelRecordDto> levelRecords = levelRecordService.listByChildrenForCategory(idChild);
		return ResponseEntity.ok(levelRecords);
	}

	@GetMapping(path = "/{idChild}/dashboard/level-records/categories/{idCategory}", produces = "application/json")
	public ResponseEntity<?> getDashboardTopic(@PathVariable int idChild, @PathVariable int idCategory) {
		List<LevelRecordDto> levelRecords = levelRecordService.listByChildrenForTopic(idChild, idCategory);
		return ResponseEntity.ok(levelRecords);
	}

	@GetMapping(path = "/{idChild}/dashboard/level-records/topics/{idTopic}", produces = "application/json")
	public ResponseEntity<?> getDashboardLevel(@PathVariable int idChild, @PathVariable int idTopic) {
		List<LevelRecordDto> levelRecords = levelRecordService.listByChildrenForLevel(idChild, idTopic);
		return ResponseEntity.ok(levelRecords);
	}

	@GetMapping(path = "/{idChild}/specialists", produces = "application/json")
	public ResponseEntity<?> listSpecialistByIdChild(@PathVariable int idChild) {
		SpecialistDto specialist = specialistService.listByIdChild(idChild);
		return ResponseEntity.ok(specialist);
	}
}
