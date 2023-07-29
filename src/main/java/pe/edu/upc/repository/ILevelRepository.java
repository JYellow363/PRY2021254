package pe.edu.upc.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import pe.edu.upc.model.Level;

@Repository
public interface ILevelRepository extends JpaRepository<Level, Integer> {
	List<Level> findByTopicId(int id);
}
