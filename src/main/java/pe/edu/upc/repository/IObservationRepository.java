package pe.edu.upc.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import pe.edu.upc.model.Observation;

@Repository
public interface IObservationRepository extends JpaRepository<Observation, Integer> {
	List<Observation> findByChildId(int id);
}
