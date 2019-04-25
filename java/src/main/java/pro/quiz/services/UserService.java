package pro.quiz.services;



import java.util.List;
import java.util.Optional;

import pro.quiz.models.User;

public interface UserService {

	User createUser(User user);
	List<User> getUsers();
	User getUserById(Long id);
	Optional<User> getUserByUsername(String login);
	Boolean existsByUsername(String login);
	Boolean existsByEmail(String mail);
}
