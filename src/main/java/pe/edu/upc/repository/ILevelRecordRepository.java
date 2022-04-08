package pe.edu.upc.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import pe.edu.upc.model.LevelRecord;

@Repository
public interface ILevelRecordRepository extends JpaRepository<LevelRecord, Integer> {
	
	List<LevelRecord> findByChildIdChildOrderByDate(int idChild);
	
	List<LevelRecord> findByChildIdChildAndLevelIdLevelOrderByDate(int idChild, int idLevel);
	
	List<LevelRecord> findByChildIdChildAndLevelTopicIdTopicOrderByDate(int idChild, int idTopic);
	
	List<LevelRecord> findByChildIdChildAndLevelTopicCategoryIdCategoryOrderByDate(int idChild, int idLevel);
	
	
	int countByIsSuccessfulAndChildIdChild(boolean isSuccessful, int idChild);
	int countByIsSuccessfulAndChildIdChildAndLevelIdLevel(boolean isSuccessful,int idChild, int idLevel);
	int countByIsSuccessfulAndChildIdChildAndLevelTopicIdTopic(boolean isSuccessful,int idChild, int idTopic);
	int countByIsSuccessfulAndChildIdChildAndLevelTopicCategoryIdCategory(boolean isSuccessful,int idChild, int idLevel);
}
