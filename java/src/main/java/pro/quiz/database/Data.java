package pro.quiz.database;

import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import pro.quiz.models.Role;
import pro.quiz.models.RoleName;
import pro.quiz.repositories.RoleRepository;

@Component
public class Data implements CommandLineRunner{

	private final RoleRepository roleRepository;
	
	public Data(RoleRepository roleRepository) {
		this.roleRepository=roleRepository;
	}
	
	@Override
	public void run (String... args) throws Exception{
		Role r1=new Role();
		r1.setName(RoleName.ROLE_USER);
		roleRepository.save(r1);
		
		Role r2=new Role();
		r2.setName(RoleName.ROLE_ADMIN);
		roleRepository.save(r2);
	}
}
