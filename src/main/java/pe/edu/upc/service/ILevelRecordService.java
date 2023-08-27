package pe.edu.upc.service;

import java.util.List;

import pe.edu.upc.dto.LevelHistoricalRecordDto;
import pe.edu.upc.dto.LevelRecordCreateDto;
import pe.edu.upc.dto.LevelRecordDto;
import pe.edu.upc.model.LevelRecord;

public interface ILevelRecordService {
	List<LevelRecordDto> listByChildrenForLevel(int id, int idTopic);
	List<LevelRecordDto> listByChildrenForTopic(int id, int idCategory);
	List<LevelRecordDto> listByChildrenForCategory(int id);
	int save(LevelRecordCreateDto levelRecord);
	LevelRecord listById(int id);
	List<LevelHistoricalRecordDto> listByChildId(int id);
}