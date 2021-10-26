package pe.edu.upc.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import pe.edu.upc.model.Symptom;

@Repository
public interface ISymptomRepository extends JpaRepository<Symptom, Integer>{

}
