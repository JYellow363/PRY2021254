package pe.edu.upc.controller;

import java.util.List;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiModelProperty;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import pe.edu.upc.dto.*;
import pe.edu.upc.model.Category;
import pe.edu.upc.model.CustomLevelList;
import pe.edu.upc.model.Level;
import pe.edu.upc.model.Observation;
import pe.edu.upc.service.IChildService;
import pe.edu.upc.service.ILevelRecordService;
import pe.edu.upc.service.IObservationService;
import pe.edu.upc.service.ISpecialistService;
import pe.edu.upc.util.Constants;

@CrossOrigin
@Api(tags="Children")
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

	@GetMapping(path = "/{id}", produces = "application/json")
	@ApiResponses({
			@ApiResponse(code = 200, message="Ok", response = ChildDto.class)
	})
	public ResponseEntity<?> listByIdChild(@PathVariable int id) {
		ChildDto child = childService.findById(id);
		return ResponseEntity.ok(child);
	}

	@PostMapping(consumes = "application/json", produces = "application/json")
	@ApiResponses({
			@ApiResponse(code = 200, message="Ok" ,response = ChildDto.class)
	})
	public ResponseEntity<?> create(@RequestBody ChildCreateDto child) {
		int result = childService.save(child);
		ResponseDto response = new ResponseDto();
		response.setId(result);
		if (result == Constants.ERROR_BD) {
			response.setMessage("Error al registrar");
			return ResponseEntity.ok(response);
		} else {
			ChildDto childCreate = childService.findById(result);
			return ResponseEntity.ok(childCreate);
		}
	}

	@PutMapping(path = "/{id}", consumes = "application/json", produces = "application/json")
	@ApiResponses({
			@ApiResponse(code = 200, message="Ok", response = ChildDto.class)
	})
	public ResponseEntity<?> update(@PathVariable int id, @RequestBody ChildUpdateDto child) {
		child.setId(id);
		int result = childService.update(child);
		ResponseDto response = new ResponseDto();
		response.setId(result);
		if (result == Constants.ERROR_BD) {
			response.setMessage("Error al actualizar");
			return ResponseEntity.ok(response);
		} else {
			ChildDto childUpdate = childService.findById(result);
			return ResponseEntity.ok(childUpdate);
		}
	}
	
	@PatchMapping(path = "/{id}", consumes = "application/json", produces = "application/json")
	@ApiResponses({
			@ApiResponse(code = 200, message="Ok", response = ChildDto.class)
	})
	public ResponseEntity<?> updateSpecialCategoryName(@PathVariable int id, @RequestBody String name) {
		SpecialCategoryDto specialCategoryDto = new SpecialCategoryDto(name, id);
		int result = childService.updateSpecialCategoryName(specialCategoryDto);
		ResponseDto response = new ResponseDto();
		response.setId(result);
		if (result == Constants.ERROR_BD) {
			response.setMessage("Error al actualizar");
			return ResponseEntity.ok(response);
		} else {
			ChildDto childUpdate = childService.findById(result);
			return ResponseEntity.ok(childUpdate);
		}
	}

	@DeleteMapping(path = "/{id}", produces = "application/json")
	@ApiResponses({
			@ApiResponse(code = 200, message="Ok", response = ResponseDto.class)
	})
	public ResponseEntity<?> delete(@PathVariable int id) {
		ResponseDto response = new ResponseDto();
		int result = childService.delete(id);
		response.setId(result);
		if (result == Constants.SUCCESSFULLY)
			response.setMessage("Eliminación exitosa");
		else
			response.setMessage("Error al eliminar");
		return ResponseEntity.ok(response);
	}

	@PostMapping(path = "/{id}/specialists", produces = "application/json")
	@ApiResponses({
			@ApiResponse(code = 200, message="Ok", response = SpecialistDto.class)
	})
	public ResponseEntity<?> createSpecialist(@PathVariable int id) {
		ResponseDto response = new ResponseDto();
		int result = childService.activateSpecialist(id);
		response.setId(result);
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

	@PostMapping(path = "/{id}/favorite-levels", consumes = "application/json", produces = "application/json")
	@ApiResponses({
			@ApiResponse(code = 200, message="Ok", response = ResponseDto.class)
	})
	public ResponseEntity<?> addFavoriteLevel(@PathVariable int id, @RequestBody int idLevel) {
		try {
			ResponseDto response = new ResponseDto();
			AddLevelDto addLevelDto = new AddLevelDto(id, idLevel);
			int result = childService.addFavoriteLevel(addLevelDto.getIdChild(), addLevelDto.getIdLevel());
			response.setId(result);
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

	@DeleteMapping(path = "/{id}/favorite-levels/{idLevel}", produces = "application/json")
	@ApiResponses({
			@ApiResponse(code = 200, message="Ok", response = ResponseDto.class)
	})
	public ResponseEntity<?> deleteFavoriteLevel(@PathVariable int id, @PathVariable int idLevel) {
		ResponseDto response = new ResponseDto();
		AddLevelDto addLevelDto = new AddLevelDto(id, idLevel);
		int result = childService.deleteFavoriteLevel(addLevelDto);
		response.setId(result);
		if (result == Constants.SUCCESSFULLY)
			response.setMessage("Eliminación exitosa");
		else
			response.setMessage("Error al eliminar");
		return ResponseEntity.ok(response);
	}

	@GetMapping(path = "/{id}/favorite-levels", produces = "application/json")
	@ApiResponses({
			@ApiResponse(code = 200, message="Ok", responseContainer = "list", response = Level.class)
	})
	public ResponseEntity<?> listFavoriteLevels(@PathVariable int id) {
		List<Level> levels = childService.listFavoriteLevels(id);
		return ResponseEntity.ok(levels);
	}

	@PostMapping(path = "/{id}/custom-level-lists", consumes = "application/json", produces = "application/json")
	@ApiResponses({
			@ApiResponse(code = 200, message="Ok", response = ResponseDto.class)
	})
	public ResponseEntity<?> addCustomLevelList(@PathVariable int id, @RequestBody String name) {
		AddCustomLevelListDto addCustomLevelDto = new AddCustomLevelListDto(id, name);
		ResponseDto response = new ResponseDto();
		int result = childService.addCustomLevelList(addCustomLevelDto);
		response.setId(result);
		if (result == Constants.ERROR_BD) {
			response.setMessage("Error al agregar lista personalizada");
		} else {
			response.setMessage("Lista agregada correctamente");
		}
		return ResponseEntity.ok(response);
	}

	@GetMapping(path = "/{id}/custom-level-lists", produces = "application/json")
	@ApiResponses({
			@ApiResponse(code = 200, message="Ok", responseContainer = "list", response = CustomLevelList.class)
	})
	public ResponseEntity<?> listCustomLevels(@PathVariable int id) {
		List<CustomLevelList> customLevelLists = childService.listCustomLevelLists(id);
		return ResponseEntity.ok(customLevelLists);
	}

	@GetMapping(path = "/{id}/custom-level-lists/{idCustomLevelList}", produces = "application/json")
	@ApiResponses({
			@ApiResponse(code = 200, message="Ok", response = CustomLevelList.class)
	})
	public ResponseEntity<?> listCustomLevelListById(@PathVariable int id, @PathVariable int idCustomLevelList) {
		CustomLevelList customLevelList = childService.listCustomLevelListById(idCustomLevelList);
		return ResponseEntity.ok(customLevelList);
	}

	@DeleteMapping(path = "/{id}/custom-level-lists/{idCustomLevelList}", produces = "application/json")
	@ApiResponses({
			@ApiResponse(code = 200, message="Ok", response = ResponseDto.class)
	})
	public ResponseEntity<?> deleteCustomLevelList(@PathVariable int id, @PathVariable int idCustomLevelList) {
		ResponseDto response = new ResponseDto();
		int result = childService.deleteCustomLevelList(id, idCustomLevelList);
		response.setId(result);
		if (result == Constants.SUCCESSFULLY)
			response.setMessage("Eliminación exitosa");
		else
			response.setMessage("Error al eliminar");
		return ResponseEntity.ok(response);
	}

	@GetMapping(path = "/{id}/observations", produces = "application/json")
	@ApiResponses({
			@ApiResponse(code = 200, message="Ok", responseContainer = "list", response = Observation.class)
	})
	public ResponseEntity<?> listObservationsByIdChild(@PathVariable int id) {
		List<Observation> observations = observationService.listByIdChild(id);
		return ResponseEntity.ok(observations);
	}

	@GetMapping(path = "/{id}/level-records", produces = "application/json")
	@ApiResponses({
			@ApiResponse(code = 200, message="Ok", responseContainer = "list", response = LevelHistoricalRecordDto.class)
	})
	public ResponseEntity<?> listLevelRecordsByIdChild(@PathVariable int id) {
		List<LevelHistoricalRecordDto> levelRecords = levelRecordService.listByIdChild(id);
		return ResponseEntity.ok(levelRecords);
	}

	@GetMapping(path = "/{id}/dashboard/level-records", produces = "application/json")
	@ApiResponses({
			@ApiResponse(code = 200, message="Ok", responseContainer = "list", response = LevelRecordDto.class)
	})
	public ResponseEntity<?> getDashboardCategory(@PathVariable int id) {
		List<LevelRecordDto> levelRecords = levelRecordService.listByChildrenForCategory(id);
		return ResponseEntity.ok(levelRecords);
	}

	@GetMapping(path = "/{id}/dashboard/level-records/categories/{idCategory}", produces = "application/json")
	@ApiResponses({
			@ApiResponse(code = 200, message="Ok", responseContainer = "list", response = LevelRecordDto.class)
	})
	public ResponseEntity<?> getDashboardTopic(@PathVariable int id, @PathVariable int idCategory) {
		List<LevelRecordDto> levelRecords = levelRecordService.listByChildrenForTopic(id, idCategory);
		return ResponseEntity.ok(levelRecords);
	}

	@GetMapping(path = "/{id}/dashboard/level-records/topics/{idTopic}", produces = "application/json")
	@ApiResponses({
			@ApiResponse(code = 200, message="Ok", responseContainer = "list", response = LevelRecordDto.class)
	})
	public ResponseEntity<?> getDashboardLevel(@PathVariable int id, @PathVariable int idTopic) {
		List<LevelRecordDto> levelRecords = levelRecordService.listByChildrenForLevel(id, idTopic);
		return ResponseEntity.ok(levelRecords);
	}

	@GetMapping(path = "/{id}/specialists", produces = "application/json")
	@ApiResponses({
			@ApiResponse(code = 200, message="Ok", response = SpecialistDto.class)
	})
	public ResponseEntity<?> listSpecialistByIdChild(@PathVariable int id) {
		SpecialistDto specialist = specialistService.listByIdChild(id);
		return ResponseEntity.ok(specialist);
	}
}
