package pe.edu.upc.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import pe.edu.upc.model.Guardian;

@Repository
public interface IGuardianRepository extends JpaRepository<Guardian, Integer>{

}
