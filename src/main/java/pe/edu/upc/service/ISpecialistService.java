package pe.edu.upc.service;

import pe.edu.upc.dto.SpecialistDto;

public interface ISpecialistService {
	public SpecialistDto listByIdSpecialist(int idSpecialist);
	public SpecialistDto listByIdChild(int idChild);
}
