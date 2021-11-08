package pe.edu.upc.service;

import java.util.List;

import pe.edu.upc.dto.ChildCreateDto;
import pe.edu.upc.dto.ChildDto;
import pe.edu.upc.dto.ChildUpdateDto;

public interface IChildService {
	public List<ChildDto> findByGuardianIdGuardian(int idGuardian);
	public ChildDto findById(int idChild);
	public int save(ChildCreateDto childCreateDto);
	public int update(ChildUpdateDto childupdateDto);
	public int delete(int idChild);
}
