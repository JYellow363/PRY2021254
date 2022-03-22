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

import pe.edu.upc.dto.AddCustomLevelListDto;
import pe.edu.upc.dto.AddLevelCustomDto;
import pe.edu.upc.dto.AddLevelDto;
import pe.edu.upc.dto.ChildCreateDto;
import pe.edu.upc.dto.ChildDto;
import pe.edu.upc.dto.ChildUpdateDto;
import pe.edu.upc.dto.ResponseDto;
import pe.edu.upc.dto.SpecialistDto;
import pe.edu.upc.model.CustomLevelList;
import pe.edu.upc.model.Level;
import pe.edu.upc.service.IChildService;
import pe.edu.upc.service.ISpecialistService;
import pe.edu.upc.util.Constants;

@CrossOrigin
@RestController
@RequestMapping(path = "/children")
public class ChildController {

	@Autowired
	private IChildService childService;

	@Autowired
	private ISpecialistService specialistService;

	@GetMapping(path = "/listByIdChild", produces = "application/json")
	public ResponseEntity<?> listByIdChild(@RequestParam int idChild) {
		ChildDto child = childService.findById(idChild);
		return ResponseEntity.ok(child);
	}

	@GetMapping(path = "/listByIdGuardian", produces = "application/json")
	public ResponseEntity<?> listByIdGuardian(@RequestParam int idGuardian) {
		List<ChildDto> children = childService.findByGuardianIdGuardian(idGuardian);
		return ResponseEntity.ok(children);
	}

	@PostMapping(path = "/create", consumes = "application/json", produces = "application/json")
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

	@PutMapping(path = "/update", consumes = "application/json", produces = "application/json")
	public ResponseEntity<?> update(@RequestBody ChildUpdateDto child) {
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

	@DeleteMapping(path = "/delete", produces = "application/json")
	public ResponseEntity<?> delete(@RequestParam int idChild) {
		ResponseDto response = new ResponseDto();
		int result = childService.delete(idChild);
		response.setIdResponse(result);
		if (result == Constants.SUCCESSFULLY)
			response.setMessage("Eliminación exitosa");
		else
			response.setMessage("Error al eliminar");
		return ResponseEntity.ok(response);
	}

	@GetMapping(path = "/activateSpecialist", produces = "application/json")
	public ResponseEntity<?> activateSpecialist(@RequestParam int idChild) {
		ResponseDto response = new ResponseDto();
		int result = childService.activateSpecialist(idChild);
		response.setIdResponse(result);
		if (result == Constants.ERROR_BD) {
			response.setMessage("Error al activar especialista");
			return ResponseEntity.ok(response);
		} else if (result == Constants.ERROR_EMAIL) {
			response.setMessage("Error en el envío de credenciales");
			return ResponseEntity.ok(response);
		} else {
			SpecialistDto specialist = specialistService.listByIdSpecialist(result);
			return ResponseEntity.ok(specialist);
		}

	}

	@PostMapping(path = "/addFavoriteLevel", consumes = "application/json", produces = "application/json")
	public ResponseEntity<?> addFavoriteLevel(@RequestBody AddLevelDto addLevelDto) {
		ResponseDto response = new ResponseDto();
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
	}

	@DeleteMapping(path = "/deleteFavoriteLevel", produces = "application/json")
	public ResponseEntity<?> deleteFavoriteLevel(@RequestBody AddLevelDto addLevelDto) {
		ResponseDto response = new ResponseDto();
		int result = childService.deleteFavoriteLevel(addLevelDto);
		response.setIdResponse(result);
		if (result == Constants.SUCCESSFULLY)
			response.setMessage("Eliminación exitosa");
		else
			response.setMessage("Error al eliminar");
		return ResponseEntity.ok(response);
	}

	@GetMapping(path = "/listFavoriteLevels", produces = "application/json")
	public ResponseEntity<?> listFavoriteLevels(@RequestParam int idChild) {
		List<Level> levels = childService.listFavoriteLevels(idChild);
		return ResponseEntity.ok(levels);
	}

	@PostMapping(path = "/addCustomLevelList", consumes = "application/json", produces = "application/json")
	public ResponseEntity<?> addFavoriteLevel(@RequestBody AddCustomLevelListDto addCustomLevelDto) {
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

	@GetMapping(path = "/listCustomLevelLists", produces = "application/json")
	public ResponseEntity<?> listCustomLevelLists(@RequestParam int idChild) {
		List<CustomLevelList> customLevelLists = childService.listCustomLevelLists(idChild);
		return ResponseEntity.ok(customLevelLists);
	}

	@GetMapping(path = "/listCustomLevelListById", produces = "application/json")
	public ResponseEntity<?> listCustomLevelListById(@RequestParam int idCustomLevelList) {

		CustomLevelList customLevelList = childService.listCustomLevelListById(idCustomLevelList);
		return ResponseEntity.ok(customLevelList);
	}

	@DeleteMapping(path = "/deleteCustomLevelList", produces = "application/json")
	public ResponseEntity<?> deleteCustomLevelList(@RequestParam int idChild, @RequestParam int idCustomLevelList) {
		ResponseDto response = new ResponseDto();
		int result = childService.deleteCustomLevelList(idChild, idCustomLevelList);
		response.setIdResponse(result);
		if (result == Constants.SUCCESSFULLY)
			response.setMessage("Eliminación exitosa");
		else
			response.setMessage("Error al eliminar");
		return ResponseEntity.ok(response);
	}

	@PostMapping(path = "/addLevelToCustomLevelList", consumes = "application/json", produces = "application/json")
	public ResponseEntity<?> addLevelToCustomLevelList(@RequestBody AddLevelCustomDto addLevelCustomDto) {
		ResponseDto response = new ResponseDto();
		int result = childService.addLevelToCustomLevelList(addLevelCustomDto);
		response.setIdResponse(result);
		if (result == Constants.ERROR_BD) {
			response.setMessage("Error al agregar nivel");
		} else if (result == Constants.ERROR_DUPLICATE) {
			response.setMessage("El nivel ya está agregado");
		} else {
			response.setMessage("Nivel agregado correctamente");
		}
		return ResponseEntity.ok(response);
	}

	@DeleteMapping(path = "/deleteLevelinCustomLevelList", produces = "application/json")
	public ResponseEntity<?> deleteFavodeleteLevelinCustomLevelListriteLevel(@RequestBody AddLevelCustomDto addLevelCustomDto) {
		ResponseDto response = new ResponseDto();
		int result = childService.deleteLevelinCustomLevelList(addLevelCustomDto);
		response.setIdResponse(result);
		if (result == Constants.SUCCESSFULLY)
			response.setMessage("Eliminación exitosa");
		else
			response.setMessage("Error al eliminar");
		return ResponseEntity.ok(response);
	}

}
