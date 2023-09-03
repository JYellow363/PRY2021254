package pe.edu.upc.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import pe.edu.upc.model.Topic;
import pe.edu.upc.repository.ITopicRepository;
import pe.edu.upc.service.ITopicService;

@Service
public class TopicServiceImpl implements ITopicService {
	@Autowired
	private ITopicRepository topicRepository;

	@Override
	public List<Topic> findAll() {
		return topicRepository.findAll();
	}

	@Override
	public Topic findById(int idTopic) {
		return topicRepository.findById(idTopic).get();
	}

	@Override
	public List<Topic> findByIdCategory(int idCategory) {
		return topicRepository.findByCategoryIdCategory(idCategory);
	}

	@Override
	public Topic save(Topic topic) {
		return topicRepository.save(topic);
	}

}
