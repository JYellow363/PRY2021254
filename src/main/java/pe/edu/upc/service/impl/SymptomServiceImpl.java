package pe.edu.upc.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import pe.edu.upc.model.Symptom;
import pe.edu.upc.repository.ISymptomRepository;
import pe.edu.upc.service.ISymptomService;

@Service
public class SymptomServiceImpl implements ISymptomService {

	@Autowired
	private ISymptomRepository symptomRepository;

	@Override
	public List<Symptom> list() {
		return symptomRepository.findAll();
	}

	@Override
	public Symptom listByIdSymptom(int idSymptom) {
		return symptomRepository.findById(idSymptom).get();
	}
	
	
}
