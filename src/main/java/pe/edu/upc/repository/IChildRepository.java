package pe.edu.upc.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import pe.edu.upc.model.Child;

@Repository
public interface IChildRepository extends JpaRepository<Child, Integer>{
	public List<Child> findByGuardianId(int id);
}
