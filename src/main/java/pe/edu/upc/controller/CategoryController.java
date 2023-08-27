package pe.edu.upc.controller;

import java.util.List;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import pe.edu.upc.dto.ChildDto;
import pe.edu.upc.model.Category;
import pe.edu.upc.model.Topic;
import pe.edu.upc.service.ICategoryService;
import pe.edu.upc.service.ITopicService;

@CrossOrigin
@Api(tags="Categories")
@RestController
@RequestMapping(path = "/categories")
public class CategoryController {

	@Autowired
	private ICategoryService categoryService;

	@Autowired
	private ITopicService topicService;
	
	@GetMapping(path = "", produces = "application/json")
	@ApiResponses({
			@ApiResponse(code = 200, message="Ok", responseContainer = "list", response = Category.class)
	})
	public ResponseEntity<?> list() {
		List<Category> categories = categoryService.findAll();
		return ResponseEntity.ok(categories);
	}

	@GetMapping(path = "/{id}", produces = "application/json")
	@ApiResponses({
			@ApiResponse(code = 200, message="Ok", response = Category.class)
	})
	public ResponseEntity<?> listByIdCategory(@PathVariable int id) {
		Category category = categoryService.findById(id);
		return ResponseEntity.ok(category);
	}

	@GetMapping(path = "/{id}/topics", produces = "application/json")
	@ApiResponses({
			@ApiResponse(code = 200, message="Ok", responseContainer = "list", response = Topic.class)
	})
	public ResponseEntity<?> listTopicsByIdCategory(@PathVariable int id) {
		List<Topic> topics = topicService.findByCategoryId(id);
		return ResponseEntity.ok(topics);
	}
}

