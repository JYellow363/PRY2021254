package pe.edu.upc.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import pe.edu.upc.model.Topic;
import pe.edu.upc.service.ITopicService;

@CrossOrigin
@RestController
@RequestMapping(path = "/topics")
public class TopicController {
	@Autowired
	private ITopicService topicService;
	
	@GetMapping(path = "/list", produces = "application/json")
	public ResponseEntity<?> list() {
		List<Topic> topics = topicService.findAll();
		return ResponseEntity.ok(topics);
	}

	@GetMapping(path = "/listByIdTopic", produces = "application/json")
	public ResponseEntity<?> listByIdTopic(@RequestParam int idTopic) {
		Topic topic = topicService.findById(idTopic);
		return ResponseEntity.ok(topic);
	}
	
	@GetMapping(path = "/listByIdCategory", produces = "application/json")
	public ResponseEntity<?> listByIdCategory(@RequestParam int idCategory) {
		List<Topic> topics = topicService.findByIdCategory(idCategory);
		return ResponseEntity.ok(topics);
	}
}
