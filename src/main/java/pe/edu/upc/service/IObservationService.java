package pe.edu.upc.service;

import java.util.List;

import pe.edu.upc.dto.ObservationDto;
import pe.edu.upc.dto.ObservationUpdateDto;
import pe.edu.upc.model.Observation;

public interface IObservationService {
	List<Observation> listByIdChild(int idChild);
	Observation listById(int idObservation);
	int save(ObservationDto observationDto);
	int delete(int idObservation);
	int update(ObservationUpdateDto observationUpdateDto);
}
