package pe.edu.upc.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import pe.edu.upc.model.Specialist;

@Repository
public interface ISpecialistRepository extends JpaRepository<Specialist, Integer> {
	Specialist findByChildId(int id);
}
