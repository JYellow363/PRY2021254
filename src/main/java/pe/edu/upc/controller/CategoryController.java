package pe.edu.upc.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import pe.edu.upc.model.Category;
import pe.edu.upc.service.ICategoryService;

@CrossOrigin
@RestController
@RequestMapping(path = "/categories")
public class CategoryController {

	@Autowired
	private ICategoryService categoryService;
	
	@GetMapping(path = "/list", produces = "application/json")
	public ResponseEntity<?> list() {
		List<Category> categories = categoryService.findAll();
		return ResponseEntity.ok(categories);
	}

	@GetMapping(path = "/listByIdCategory", produces = "application/json")
	public ResponseEntity<?> listByIdCategory(@RequestParam int idCategory) {
		Category category = categoryService.findById(idCategory);
		return ResponseEntity.ok(category);
	}
}

