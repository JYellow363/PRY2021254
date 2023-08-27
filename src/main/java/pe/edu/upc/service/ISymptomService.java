package pe.edu.upc.service;

import java.util.List;

import pe.edu.upc.model.Symptom;

public interface ISymptomService {
	public List<Symptom> list();
	public Symptom listBySymptomId(int id);
}
