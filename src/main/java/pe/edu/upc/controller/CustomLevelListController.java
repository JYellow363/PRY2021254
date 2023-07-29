package pe.edu.upc.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pe.edu.upc.dto.AddLevelCustomDto;
import pe.edu.upc.dto.CustomLevelListUpdateDto;
import pe.edu.upc.dto.ResponseDto;
import pe.edu.upc.model.Category;
import pe.edu.upc.service.ICustomLevelListService;
import pe.edu.upc.util.Constants;

@CrossOrigin
@Api(tags="Custom Level Lists")
@RestController
@RequestMapping(path = "/custom-level-lists")
public class CustomLevelListController {

    @Autowired
    private ICustomLevelListService customLevelListService;

    @PostMapping(path = "/{id}/levels", consumes = "application/json", produces = "application/json")
    @ApiResponses({
            @ApiResponse(code = 200, message="Ok", response = ResponseDto.class)
    })
    public ResponseEntity<?> addLevelToCustomLevelList(@PathVariable int id, @RequestBody int idLevel) {
        ResponseDto response = new ResponseDto();
        AddLevelCustomDto addLevelCustomDto = new AddLevelCustomDto(idLevel, id);
        int result = customLevelListService.addLevelToCustomLevelList(addLevelCustomDto);
        response.setId(result);
        if (result == Constants.ERROR_BD) {
            response.setMessage("Error al agregar nivel");
        } else if (result == Constants.ERROR_DUPLICATE) {
            response.setMessage("El nivel ya está agregado");
        } else {
            response.setMessage("Nivel agregado correctamente");
        }
        return ResponseEntity.ok(response);
    }

    @DeleteMapping(path = "/{id}/levels/{idLevel}", produces = "application/json")
    @ApiResponses({
            @ApiResponse(code = 200, message="Ok", response = ResponseDto.class)
    })
    public ResponseEntity<?> deleteLevelInCustomLevelList(@PathVariable int id, @PathVariable int idLevel) {
        ResponseDto response = new ResponseDto();
        AddLevelCustomDto addLevelCustomDto = new AddLevelCustomDto(idLevel, id);
        int result = customLevelListService.deleteLevelinCustomLevelList(addLevelCustomDto);
        response.setId(result);
        if (result == Constants.SUCCESSFULLY)
            response.setMessage("Eliminación exitosa");
        else
            response.setMessage("Error al eliminar");
        return ResponseEntity.ok(response);
    }

    @PutMapping(path = "/{id}", consumes = "application/json", produces = "application/json")
    @ApiResponses({
            @ApiResponse(code = 200, message="Ok", response = ResponseDto.class)
    })
    public ResponseEntity<?> updateNameCustomLevelList(@PathVariable int id, @RequestBody String name) {
        ResponseDto response = new ResponseDto();
        CustomLevelListUpdateDto customLevelListUpdateDto = new CustomLevelListUpdateDto(id, name);
        int result = customLevelListService.updateCustomLevelList(customLevelListUpdateDto);
        response.setId(result);
        if (result == Constants.ERROR_BD) {
            response.setMessage("Error al actualizar lista personalizada");
        } else {
            response.setMessage("Lista actualizada correctamente");
        }
        return ResponseEntity.ok(response);
    }
}
