package pe.edu.upc.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import pe.edu.upc.model.LevelRecord;

@Repository
public interface ILevelRecordRepository extends JpaRepository<LevelRecord, Integer> {
	List<LevelRecord> findByChildIdChild(int idChild);
	List<LevelRecord> findByChildIdChildAndLevelIdLevel(int idChild, int idLevel);
	List<LevelRecord> findByChildIdChildAndLevelTopicIdTopic(int idChild, int idTopic);
	List<LevelRecord> findByChildIdChildAndLevelTopicCategoryIdCategory(int idChild, int idLevel);
	
	int countByIsSuccessfulAndChildIdChild(boolean isSuccessful, int idChild);
	int countByIsSuccessfulAndChildIdChildAndLevelIdLevel(boolean isSuccessful,int idChild, int idLevel);
	int countByIsSuccessfulAndChildIdChildAndLevelTopicIdTopic(boolean isSuccessful,int idChild, int idTopic);
	int countByIsSuccessfulAndChildIdChildAndLevelTopicCategoryIdCategory(boolean isSuccessful,int idChild, int idLevel);
}
