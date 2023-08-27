package pe.edu.upc.service;

import pe.edu.upc.dto.SpecialistDto;

public interface ISpecialistService {
	public SpecialistDto listById(int id);
	public SpecialistDto listByChildId(int id);
}
