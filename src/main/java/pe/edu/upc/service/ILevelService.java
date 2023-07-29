package pe.edu.upc.service;

import java.util.List;

import pe.edu.upc.model.Level;

public interface ILevelService {
	public List<Level> findAll();
	public Level findById(int id);
	public List<Level> findByIdTopic(int id);
}
