package pro.quiz.services.impl;
import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import pro.quiz.models.User;
import pro.quiz.repositories.UserRepository;
import pro.quiz.services.UserService;


@Service
public class UserServiceImpl implements UserService{
	
	private final UserRepository userRepository;
	public UserServiceImpl (UserRepository userRepository)
	{
		this.userRepository=userRepository;
	}
	
	@Override
	public User createUser(User user) {
		return this.userRepository.save(user);
	}
	
	@Override
	public List<User> getUsers(){
		return this.userRepository.findAll();
	}
	
	@Override
	public User getUserById(Long id) {
		return this.userRepository.findById(id).get();
	}
	
	@Override
	public  Optional<User> getUserByUsername(String login) {
		return this.userRepository.getUserByUsername(login);
	}
	@Override
	public Boolean existsByUsername(String login) {
		return this.userRepository.existsByUsername(login);
	}
	
	@Override
	public Boolean existsByEmail(String mail) {
		return this.userRepository.existsByEmail(mail);
	};
}

