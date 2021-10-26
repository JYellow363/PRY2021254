package pe.edu.upc.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import pe.edu.upc.model.LevelRecord;

@Repository
public interface ILevelRecordRepository extends JpaRepository<LevelRecord, Integer> {

}
