package pe.edu.upc.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pe.edu.upc.dto.ObservationDto;
import pe.edu.upc.dto.ObservationUpdateDto;
import pe.edu.upc.dto.ResponseDto;
import pe.edu.upc.model.Category;
import pe.edu.upc.model.Observation;
import pe.edu.upc.service.IObservationService;
import pe.edu.upc.util.Constants;

@CrossOrigin
@Api(tags="Observations")
@RestController
@RequestMapping(path = "/observations")
public class ObservationController {

    @Autowired
    private IObservationService observationService;

    @GetMapping(path = "/{id}", produces = "application/json")
    @ApiResponses({
            @ApiResponse(code = 200, message="Ok", response = Observation.class)
    })
    public ResponseEntity<?> listByIdGuardian(@PathVariable int id) {
        Observation observation = observationService.listById(id);
        return ResponseEntity.ok(observation);
    }

    @PostMapping(consumes = "application/json", produces = "application/json")
    @ApiResponses({
            @ApiResponse(code = 200, message="Ok", response = Observation.class)
    })
    public ResponseEntity<?> create(@RequestBody ObservationDto observation) {
        int result = observationService.save(observation);
        ResponseDto response = new ResponseDto();
        response.setId(result);
        if (result == Constants.ERROR_BD) {
            response.setMessage("Error al registrar");
            return ResponseEntity.ok(response);
        } else {
            Observation observationCreate = observationService.listById(result);
            return ResponseEntity.ok(observationCreate);
        }
    }

    @PutMapping(path = "/{id}", consumes = "application/json", produces = "application/json")
    @ApiResponses({
            @ApiResponse(code = 200, message="Ok", response = Observation.class)
    })
    public ResponseEntity<?> update(@PathVariable int id, @RequestBody ObservationUpdateDto observation) {
        observation.setId(id);
        int result = observationService.update(observation);
        ResponseDto response = new ResponseDto();
        response.setId(result);
        if (result == Constants.ERROR_BD) {
            response.setMessage("Error al actualizar");
            return ResponseEntity.ok(response);
        } else {
            Observation observationUpdate = observationService.listById(result);
            return ResponseEntity.ok(observationUpdate);
        }
    }

    @DeleteMapping(path = "/{id}", produces = "application/json")
    @ApiResponses({
            @ApiResponse(code = 200, message="Ok", response = ResponseDto.class)
    })
    public ResponseEntity<?> delete(@PathVariable int id) {
        ResponseDto response = new ResponseDto();
        int result = observationService.delete(id);
        response.setId(result);
        if (result == Constants.SUCCESSFULLY)
            response.setMessage("Eliminación exitosa");
        else
            response.setMessage("Error al eliminar");
        return ResponseEntity.ok(response);
    }
}
