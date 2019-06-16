package pro.quiz.services.impl;

import org.springframework.stereotype.Service;

import pro.quiz.repositories.UserResultRepository;
import pro.quiz.services.UserResultService;

@Service
public class UserResultServiceImpl implements UserResultService{
	
	private final UserResultRepository userResultRepository;
	public UserResultServiceImpl (UserResultRepository userResultRepository)
	{
		this.userResultRepository=userResultRepository;
	}
	
	public String deleteUserResult(Long id) 
	{
		this.userResultRepository.deleteById(id);
		return "deleted";
	}
}
