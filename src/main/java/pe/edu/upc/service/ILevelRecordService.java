package pe.edu.upc.service;

import java.util.List;

import pe.edu.upc.dto.LevelHistoricalRecordDto;
import pe.edu.upc.dto.LevelRecordCreateDto;
import pe.edu.upc.dto.LevelRecordDto;
import pe.edu.upc.model.LevelRecord;

public interface ILevelRecordService {
	List<LevelRecordDto> listByChildrenForLevel(int idChild, int idTopic);
	List<LevelRecordDto> listByChildrenForTopic(int idChild, int idCategory);
	List<LevelRecordDto> listByChildrenForCategory(int idChild);
	int save(LevelRecordCreateDto levelRecord);
	LevelRecord listById(int idLevelRecord);
	List<LevelHistoricalRecordDto> listByIdChild(int idChild);
}