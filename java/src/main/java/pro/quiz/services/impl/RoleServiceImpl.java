package pro.quiz.services.impl;

import java.util.Optional;

import org.springframework.stereotype.Service;

import pro.quiz.models.Role;
import pro.quiz.models.RoleName;
import pro.quiz.repositories.QuestionRepository;
import pro.quiz.repositories.RoleRepository;
import pro.quiz.repositories.SubjectRepository;
import pro.quiz.services.QuestionService;
import pro.quiz.services.RoleService;


@Service
public class RoleServiceImpl implements RoleService{

	private final RoleRepository  roleRepository;
	
	public RoleServiceImpl ( RoleRepository roleRepository)
	{
		this.roleRepository= roleRepository;
	}
	
	//Optional<Role> findByName(RoleName roleName){}
	public Role findByNameNotOptional(RoleName roleName) {
		Role role=this.roleRepository.findByName(roleName).orElse(null);
		return role;
		
	}

}
