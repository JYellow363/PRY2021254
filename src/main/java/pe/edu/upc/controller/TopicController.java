package pe.edu.upc.controller;

import java.util.List;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import pe.edu.upc.model.Category;
import pe.edu.upc.model.Level;
import pe.edu.upc.model.Topic;
import pe.edu.upc.service.ILevelService;
import pe.edu.upc.service.ITopicService;

@CrossOrigin
@Api(tags="Topics")
@RestController
@RequestMapping(path = "/topics")
public class TopicController {
	@Autowired
	private ITopicService topicService;

	@Autowired
	private ILevelService levelService;
	
	@GetMapping(produces = "application/json")
	@ApiResponses({
			@ApiResponse(code = 200, message="Ok", responseContainer = "list", response = Topic.class)
	})
	public ResponseEntity<?> list() {
		List<Topic> topics = topicService.findAll();
		return ResponseEntity.ok(topics);
	}

	@GetMapping(path = "/{id}", produces = "application/json")
	@ApiResponses({
			@ApiResponse(code = 200, message="Ok", response = Topic.class)
	})
	public ResponseEntity<?> listByIdTopic(@PathVariable int id) {
		Topic topic = topicService.findById(id);
		return ResponseEntity.ok(topic);
	}

	@GetMapping(path = "/{id}/levels", produces = "application/json")
	@ApiResponses({
			@ApiResponse(code = 200, message="Ok", responseContainer = "list", response = Level.class)
	})
	public ResponseEntity<?> listLevelsByIdTopic(@PathVariable int id) {
		List<Level> levels = levelService.findByIdTopic(id);
		return ResponseEntity.ok(levels);
	}
}
