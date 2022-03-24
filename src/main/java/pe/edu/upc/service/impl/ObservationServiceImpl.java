package pe.edu.upc.service.impl;

import java.util.ArrayList;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import pe.edu.upc.dto.ObservationDto;
import pe.edu.upc.dto.ObservationUpdateDto;
import pe.edu.upc.model.Child;
import pe.edu.upc.model.Observation;
import pe.edu.upc.repository.IChildRepository;
import pe.edu.upc.repository.IObservationRepository;
import pe.edu.upc.service.IObservationService;
import pe.edu.upc.util.Constants;

@Service
public class ObservationServiceImpl implements IObservationService {

	@Autowired
	private IObservationRepository observationRepository;
	@Autowired
	private IChildRepository childRepository;

	@Override
	public List<Observation> listByIdChild(int idChild) {
		List<Observation> observations = observationRepository.findByChildIdChild(idChild);
		return observations == null ? new ArrayList<Observation>() : observations;
	}

	@Override
	public Observation listById(int idObservation) {
		return observationRepository.findById(idObservation).get();
	}

	@Transactional
	@Override
	public int update(ObservationUpdateDto observationUpdateDto) {
		Observation observationSave = observationRepository.save(convert(observationUpdateDto));
		if (observationSave == null)
			return Constants.ERROR_BD;
		return observationSave.getIdObservation();
	}

	@Transactional
	@Override
	public int save(ObservationDto observationDto) {
		Observation observationSave = observationRepository.save(convert(observationDto));
		if (observationSave == null)
			return Constants.ERROR_BD;
		return observationSave.getIdObservation();
	}

	@Transactional
	@Override
	public int delete(int idObservation) {
		try {
			observationRepository.deleteById(idObservation);
		} catch (Exception e) {
			return Constants.ERROR_BD;
		}
		return Constants.SUCCESSFULLY;
	}

	private Observation convert(ObservationDto observationDto) {
		Child child = childRepository.findById(observationDto.getIdChild()).get();
		Observation observation = new Observation();
		observation.setTitle(observationDto.getTitle());
		observation.setDescription(observationDto.getDescription());
		observation.setChild(child);
		return observation;
	}

	private Observation convert(ObservationUpdateDto observationUpdateDto) {
		Observation observation = observationRepository.findById(observationUpdateDto.getIdObservation()).get();
		observation.setTitle(observationUpdateDto.getTitle());
		observation.setDescription(observationUpdateDto.getDescription());
		return observation;
	}

}
