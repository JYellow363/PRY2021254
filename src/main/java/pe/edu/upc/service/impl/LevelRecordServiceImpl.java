package pe.edu.upc.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import pe.edu.upc.dto.LevelHistoricalRecordDto;
import pe.edu.upc.dto.LevelRecordCreateDto;
import pe.edu.upc.dto.LevelRecordDto;
import pe.edu.upc.model.Category;
import pe.edu.upc.model.Child;
import pe.edu.upc.model.Level;
import pe.edu.upc.model.LevelRecord;
import pe.edu.upc.model.Topic;
import pe.edu.upc.repository.ICategoryRepository;
import pe.edu.upc.repository.ILevelRecordRepository;
import pe.edu.upc.repository.ILevelRepository;
import pe.edu.upc.repository.ITopicRepository;
import pe.edu.upc.service.ILevelRecordService;
import pe.edu.upc.util.Constants;

@Service
public class LevelRecordServiceImpl implements ILevelRecordService {

	@Autowired
	private ILevelRecordRepository levelRecordRepository;

	@Autowired
	private ICategoryRepository categoryRepository;

	@Autowired
	private ITopicRepository topicRepository;

	@Autowired
	private ILevelRepository levelRepository;

	@Override
	public LevelRecord listById(int id) {
		return levelRecordRepository.findById(id).get();
	}

	@Override
	public int save(LevelRecordCreateDto levelRecord) {
		LevelRecord levelRecordSave = levelRecordRepository.save(convert(levelRecord));
		if (levelRecordSave == null)
			return Constants.ERROR_BD;
		return levelRecordSave.getId();
	}

	@Override
	public List<LevelRecordDto> listByChildrenForLevel(int id, int idTopic) {
		List<Level> levels = levelRepository.findByTopicId(idTopic);
		List<LevelRecordDto> levelRecordsDto = new ArrayList<LevelRecordDto>();
		for (int i = 0; i < levels.size(); i++) {
			levelRecordsDto.add(listByChildrenAndLevel(id, levels.get(i).getId()));
		}
		return levelRecordsDto;
	}

	@Override
	public List<LevelRecordDto> listByChildrenForTopic(int idChild, int idCategory) {
		List<Topic> topics = topicRepository.findByCategoryId(idCategory);
		List<LevelRecordDto> levelRecordsDto = new ArrayList<LevelRecordDto>();
		for (int i = 0; i < topics.size(); i++) {
			levelRecordsDto.add(listByChildrenAndTopic(idChild, topics.get(i).getId()));
		}
		return levelRecordsDto;
	}

	@Override
	public List<LevelRecordDto> listByChildrenForCategory(int idChild) {
		List<Category> categories = categoryRepository.findAll();
		List<LevelRecordDto> levelRecordsDto = new ArrayList<LevelRecordDto>();
		for (int i = 0; i < categories.size(); i++) {
			levelRecordsDto.add(listByChildrenAndCategory(idChild, categories.get(i).getId()));
		}
		return levelRecordsDto;
	}

	private LevelRecordDto listByChildrenAndLevel(int id, int idLevel) {
		Level level = levelRepository.findById(idLevel).get();
		List<LevelRecord> levelRecords = levelRecordRepository.findByChildIdAndLevelIdOrderByDate(id, idLevel);
		for (int i = 0; i < levelRecords.size(); i++) {
			levelRecords.get(i).getChild().setGuardian(null);
		}
		int positiveResults = levelRecordRepository.countByIsSuccessfulAndChildIdAndLevelId(true, id,
				idLevel);
		int negativeResults = levelRecordRepository.countByIsSuccessfulAndChildIdAndLevelId(false, id,
				idLevel);
		return new LevelRecordDto(idLevel, level.getDescription(), positiveResults, negativeResults, levelRecords);
	}

	private LevelRecordDto listByChildrenAndTopic(int id, int idTopic) {
		Topic topic = topicRepository.findById(idTopic).get();
		List<LevelRecord> levelRecords = levelRecordRepository.findByChildIdAndLevelTopicIdOrderByDate(id, idTopic);
		for (int i = 0; i < levelRecords.size(); i++) {
			levelRecords.get(i).getChild().setGuardian(null);
		}
		int positiveResults = levelRecordRepository.countByIsSuccessfulAndChildIdAndLevelTopicId(true,
				id, idTopic);
		int negativeResults = levelRecordRepository.countByIsSuccessfulAndChildIdAndLevelTopicId(false,
				id, idTopic);
		return new LevelRecordDto(idTopic, topic.getDescription(), positiveResults, negativeResults, levelRecords);
	}

	private LevelRecordDto listByChildrenAndCategory(int id, int idCategory) {
		Category category = categoryRepository.findById(idCategory).get();
		List<LevelRecord> levelRecords = levelRecordRepository
				.findByChildIdAndLevelTopicCategoryIdOrderByDate(id, idCategory);
		for (int i = 0; i < levelRecords.size(); i++) {
			levelRecords.get(i).getChild().setGuardian(null);
		}
		int positiveResults = levelRecordRepository
				.countByIsSuccessfulAndChildIdAndLevelTopicCategoryId(true, id, idCategory);
		int negativeResults = levelRecordRepository
				.countByIsSuccessfulAndChildIdAndLevelTopicCategoryId(false, id, idCategory);
		return new LevelRecordDto(idCategory, category.getDescription(), positiveResults, negativeResults,
				levelRecords);
	}
	
	@Override
	public List<LevelHistoricalRecordDto> listByIdChild(int id) {
		List<LevelRecord> levelRecords = levelRecordRepository.findByChildIdOrderByDate(id);
		List<LevelHistoricalRecordDto> levelHistoricalRecordsDto = new ArrayList<LevelHistoricalRecordDto>();
		for (LevelRecord levelRecord: levelRecords) {
			levelHistoricalRecordsDto.add(convert(levelRecord));
		}
		return levelHistoricalRecordsDto;
	}
	
	private LevelRecord convert(LevelRecordCreateDto levelRecordCreateDto) {
		LevelRecord levelRecord = new LevelRecord();
		Level level = new Level();
		level.setId(levelRecordCreateDto.getIdLevel());
		Child child = new Child();
		child.setId(levelRecordCreateDto.getIdChild());
		levelRecord.setChild(child);
		levelRecord.setLevel(level);
		levelRecord.setDate(new Date());
		levelRecord.setSuccessful(levelRecordCreateDto.isSuccessful());
		return levelRecord;
	}
	
	private LevelHistoricalRecordDto convert(LevelRecord levelRecord) {
		LevelHistoricalRecordDto levelHistoricalRecordDto = new LevelHistoricalRecordDto();
		levelHistoricalRecordDto.setId(levelRecord.getId());
		levelHistoricalRecordDto.setDate(levelRecord.getDate());
		levelHistoricalRecordDto.setSuccessful(levelRecord.isSuccessful());
		levelHistoricalRecordDto.setLevel(levelRecord.getLevel());
		return levelHistoricalRecordDto;
	};
}
