package pe.edu.upc.service;

import pe.edu.upc.model.Guardian;

public interface IGuardianService {
	public int save(Guardian guardian);
	public Guardian findById(int idGuardian);
	
}
