package pe.edu.upc.controller;

import java.util.List;

import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import pe.edu.upc.model.Category;
import pe.edu.upc.model.Topic;
import pe.edu.upc.service.ICategoryService;
import pe.edu.upc.service.ITopicService;

@CrossOrigin
@Api(tags="Category")
@RestController
@RequestMapping(path = "/categories")
public class CategoryController {

	@Autowired
	private ICategoryService categoryService;

	@Autowired
	private ITopicService topicService;
	
	@GetMapping(path = "", produces = "application/json")
	public ResponseEntity<?> list() {
		List<Category> categories = categoryService.findAll();
		return ResponseEntity.ok(categories);
	}

	@GetMapping(path = "/{idCategory}", produces = "application/json")
	public ResponseEntity<?> listByIdCategory(@PathVariable int idCategory) {
		Category category = categoryService.findById(idCategory);
		return ResponseEntity.ok(category);
	}

	@GetMapping(path = "/{idCategory}/topics", produces = "application/json")
	public ResponseEntity<?> listTopicsByIdCategory(@PathVariable int idCategory) {
		List<Topic> topics = topicService.findByIdCategory(idCategory);
		return ResponseEntity.ok(topics);
	}
}

