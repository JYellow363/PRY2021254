package pe.edu.upc.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import pe.edu.upc.model.LevelRecord;

@Repository
public interface ILevelRecordRepository extends JpaRepository<LevelRecord, Integer> {
	
	void deleteByChildId(int id);
	
	List<LevelRecord> findByChildIdOrderByDate(int id);
	List<LevelRecord> findByChildIdAndLevelIdOrderByDate(int id, int idLevel);
	List<LevelRecord> findByChildIdAndLevelTopicIdOrderByDate(int id, int idTopic);
	List<LevelRecord> findByChildIdAndLevelTopicCategoryIdOrderByDate(int id, int idCategory);
	
	int countByIsSuccessfulAndChildId(boolean isSuccessful, int id);
	int countByIsSuccessfulAndChildIdAndLevelId(boolean isSuccessful,int idChild, int idLevel);
	int countByIsSuccessfulAndChildIdAndLevelTopicId(boolean isSuccessful,int id, int idTopic);
	int countByIsSuccessfulAndChildIdAndLevelTopicCategoryId(boolean isSuccessful,int id, int idCategory);
}
