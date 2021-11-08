package pe.edu.upc.service;

import pe.edu.upc.dto.GuardianCreateDto;
import pe.edu.upc.dto.GuardianDto;
import pe.edu.upc.dto.GuardianUpdateDto;

public interface IGuardianService {
	public int save(GuardianCreateDto guardianCreateDto);
	public GuardianDto listByIdGuardian(int idGuardian);
	int update(GuardianUpdateDto guardianUpdateDto);
}
