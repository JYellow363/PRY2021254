package pe.edu.upc.service;

import java.util.List;

import pe.edu.upc.dto.ObservationDto;
import pe.edu.upc.dto.ObservationUpdateDto;
import pe.edu.upc.model.Observation;

public interface IObservationService {
	List<Observation> listByIdChild(int id);
	Observation listById(int id);
	int save(ObservationDto observationDto);
	int delete(int id);
	int update(ObservationUpdateDto observationUpdateDto);
}
