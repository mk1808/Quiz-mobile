package pro.quiz.repositories;

import java.util.Optional;


import org.springframework.data.jpa.repository.JpaRepository;

import pro.quiz.models.User;

public interface UserRepository extends JpaRepository<User, Long>  {
	public Optional<User> getUserByUsername(String login);
	Boolean existsByUsername(String login);
	Boolean existsByEmail(String mail);
	
}
