package pro.quiz.services.impl;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import pro.quiz.models.Role;
import pro.quiz.models.RoleName;
import pro.quiz.models.User;
import pro.quiz.repositories.RoleRepository;
import pro.quiz.repositories.UserRepository;
import pro.quiz.services.UserService;


@Service
public class UserServiceImpl implements UserService{
	
	private final UserRepository userRepository;
	private final RoleRepository roleRepository;
	public UserServiceImpl (UserRepository userRepository, RoleRepository roleRepository)
	{
		this.userRepository=userRepository;
		this.roleRepository=roleRepository;
	}
	
	@Autowired
    PasswordEncoder encoder;
	
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
	
	@Override
	public User updateUser(User user) {
		User myUser=this.userRepository.findById(user.getId()).get();
		
		User partUser= new User(encoder.encode(user.getPassword()), user.getName(), user.getSurname(), user.getCourse());
	
		Role userRole = roleRepository.findByName(RoleName.ROLE_USER)
				   .orElseThrow(() -> new RuntimeException("Fail! -> Cause: User Role not find."));
		
		partUser.setRole(userRole);
		partUser.setEmail(user.getEmail());
		partUser.setId(user.getId());
		partUser.setUsername(user.getUsername());
		partUser.setUserResults(user.getUserResults());
		if(user.getPassword().isEmpty()) { partUser.setPassword(encoder.encode(myUser.getPassword()));}
		
		this.userRepository.save(partUser);
		partUser.setPassword("");
		return partUser; 
	}
	
}

