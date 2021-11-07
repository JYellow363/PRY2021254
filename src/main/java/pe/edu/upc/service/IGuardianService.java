package pe.edu.upc.service;

import pe.edu.upc.dto.GuardianCreateDto;
import pe.edu.upc.dto.GuardianDto;

public interface IGuardianService {
	public int save(GuardianCreateDto guardianCreateDto);
	public GuardianDto listByIdGuardian(int idGuardian);
}
