package pro.quiz.repositories;

import java.util.List;
import java.util.Optional;


import org.springframework.data.jpa.repository.JpaRepository;

import pro.quiz.models.User;

public interface UserRepository extends JpaRepository<User, Long>  {
	public Optional<User> getUserByUsername(String login);
	Boolean existsByUsername(String login);
	Boolean existsByEmail(String mail);
/*	List<User> findByEmailContainingAndUsernameContainingAndNameContainingAndSurnameContainingAndCourseContaining(
			String emali, String username, String name, String surname, String course);
*/
	List<User> findByEmailContainingAndUsernameContainingAndNameContainingAndSurnameContaining
	(
			String email, String username, String name, String surname);

}
