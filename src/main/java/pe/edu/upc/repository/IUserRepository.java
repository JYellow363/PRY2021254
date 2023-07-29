package pe.edu.upc.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pe.edu.upc.model.Users;

@Repository
public interface IUserRepository extends JpaRepository<Users, Integer> {
	Users findByUsername(String username);
	Users findByGuardianEmail(String email);
}
