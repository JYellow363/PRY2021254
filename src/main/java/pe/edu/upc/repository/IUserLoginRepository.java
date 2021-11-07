package pe.edu.upc.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import pe.edu.upc.model.UserLogin;

@Repository
public interface IUserLoginRepository extends JpaRepository<UserLogin, Integer> {
	Optional<UserLogin> findByUsername(String username);
}
