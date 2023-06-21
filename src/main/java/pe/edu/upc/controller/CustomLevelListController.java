package pe.edu.upc.controller;

import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pe.edu.upc.dto.AddLevelCustomDto;
import pe.edu.upc.dto.CustomLevelListUpdateDto;
import pe.edu.upc.dto.ResponseDto;
import pe.edu.upc.service.ICustomLevelListService;
import pe.edu.upc.util.Constants;

@CrossOrigin
@Api(tags="CustomLevelList")
@RestController
@RequestMapping(path = "/custom-level-lists")
public class CustomLevelListController {

    @Autowired
    private ICustomLevelListService customLevelListService;

    @PostMapping(path = "/{idCustomLevelList}/levels", consumes = "application/json", produces = "application/json")
    public ResponseEntity<?> addLevelToCustomLevelList(@PathVariable int idCustomLevelList, @RequestBody int idLevel) {
        ResponseDto response = new ResponseDto();
        AddLevelCustomDto addLevelCustomDto = new AddLevelCustomDto(idLevel, idCustomLevelList);
        int result = customLevelListService.addLevelToCustomLevelList(addLevelCustomDto);
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

    @DeleteMapping(path = "/{idCustomLevelList}/levels/{idLevel}", produces = "application/json")
    public ResponseEntity<?> deleteLevelInCustomLevelList(@PathVariable int idCustomLevelList, @PathVariable int idLevel) {
        ResponseDto response = new ResponseDto();
        AddLevelCustomDto addLevelCustomDto = new AddLevelCustomDto(idLevel, idCustomLevelList);
        int result = customLevelListService.deleteLevelinCustomLevelList(addLevelCustomDto);
        response.setIdResponse(result);
        if (result == Constants.SUCCESSFULLY)
            response.setMessage("Eliminación exitosa");
        else
            response.setMessage("Error al eliminar");
        return ResponseEntity.ok(response);
    }

    @PutMapping(path = "/{idCustomLevelList}", consumes = "application/json", produces = "application/json")
    public ResponseEntity<?> updateNameCustomLevelList(@PathVariable int idCustomLevelList, @RequestBody String name) {
        ResponseDto response = new ResponseDto();
        CustomLevelListUpdateDto customLevelListUpdateDto = new CustomLevelListUpdateDto(idCustomLevelList, name);
        int result = customLevelListService.updateCustomLevelList(customLevelListUpdateDto);
        response.setIdResponse(result);
        if (result == Constants.ERROR_BD) {
            response.setMessage("Error al actualizar lista personalizada");
        } else {
            response.setMessage("Lista actualizada correctamente");
        }
        return ResponseEntity.ok(response);
    }
}
