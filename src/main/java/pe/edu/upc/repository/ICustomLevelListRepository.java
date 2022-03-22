package pe.edu.upc.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import pe.edu.upc.model.CustomLevelList;

@Repository
public interface ICustomLevelListRepository extends JpaRepository<CustomLevelList, Integer> {

}
