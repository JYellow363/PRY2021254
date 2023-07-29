package pe.edu.upc.service;

import java.util.List;

import pe.edu.upc.model.Category;

public interface ICategoryService {
	public List<Category> findAll();
	public Category findById(int id);
}
