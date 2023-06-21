package pe.edu.upc.controller;

import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pe.edu.upc.dto.ObservationDto;
import pe.edu.upc.dto.ObservationUpdateDto;
import pe.edu.upc.dto.ResponseDto;
import pe.edu.upc.model.Observation;
import pe.edu.upc.service.IObservationService;
import pe.edu.upc.util.Constants;

import java.util.List;

@CrossOrigin
@Api(tags="Observation")
@RestController
@RequestMapping(path = "/observations")
public class ObservationController {

    @Autowired
    private IObservationService observationService;

    @GetMapping(path = "/{idObservation}", produces = "application/json")
    public ResponseEntity<?> listByIdGuardian(@PathVariable int idObservation) {
        Observation observation = observationService.listById(idObservation);
        return ResponseEntity.ok(observation);
    }

    @PostMapping(path = "", consumes = "application/json", produces = "application/json")
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

    @PutMapping(path = "/{idObservation}", consumes = "application/json", produces = "application/json")
    public ResponseEntity<?> update(@PathVariable int idObservation, @RequestBody ObservationUpdateDto observation) {
        observation.setIdObservation(idObservation);
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

    @DeleteMapping(path = "/{idObservation}", produces = "application/json")
    public ResponseEntity<?> delete(@PathVariable int idObservation) {
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
