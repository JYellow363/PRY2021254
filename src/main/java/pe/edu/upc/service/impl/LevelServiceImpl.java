package pe.edu.upc.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import pe.edu.upc.model.Level;
import pe.edu.upc.repository.ILevelRepository;
import pe.edu.upc.service.ILevelService;

@Service
public class LevelServiceImpl implements ILevelService {

	@Autowired
	private ILevelRepository levelRepository;

	@Override
	public List<Level> findAll() {
		return levelRepository.findAll();
	}

	@Override
	public Level findById(int id) {
		return levelRepository.findById(id).get();
	}

	@Override
	public List<Level> findByIdTopic(int id) {
		return levelRepository.findByTopicId(id);
	}
	
	
}
