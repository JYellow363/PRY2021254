package pe.edu.upc.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import pe.edu.upc.model.Topic;

@Repository
public interface ITopicRepository extends JpaRepository<Topic, Integer> {
	List<Topic> findByCategoryId(int id);
}
