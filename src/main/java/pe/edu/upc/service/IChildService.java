package pe.edu.upc.service;

import java.util.List;

import pe.edu.upc.model.Child;

public interface IChildService {
	public List<Child> findByGuardianIdGuardian(int idGuardian);
	public Child findById(int idChild);
	public int save(Child child);
}
