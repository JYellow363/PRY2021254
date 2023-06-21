package pe.edu.upc.controller;

import java.util.List;

import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import pe.edu.upc.model.Level;
import pe.edu.upc.model.Topic;
import pe.edu.upc.service.ILevelService;
import pe.edu.upc.service.ITopicService;

@CrossOrigin
@Api(tags="Topic")
@RestController
@RequestMapping(path = "/topics")
public class TopicController {
	@Autowired
	private ITopicService topicService;

	@Autowired
	private ILevelService levelService;
	
	@GetMapping(path = "", produces = "application/json")
	public ResponseEntity<?> list() {
		List<Topic> topics = topicService.findAll();
		return ResponseEntity.ok(topics);
	}

	@GetMapping(path = "/{idTopic}", produces = "application/json")
	public ResponseEntity<?> listByIdTopic(@PathVariable int idTopic) {
		Topic topic = topicService.findById(idTopic);
		return ResponseEntity.ok(topic);
	}

	@GetMapping(path = "/{idTopic}/levels", produces = "application/json")
	public ResponseEntity<?> listLevelsByIdTopic(@PathVariable int idTopic) {
		List<Level> levels = levelService.findByIdTopic(idTopic);
		return ResponseEntity.ok(levels);
	}
}
