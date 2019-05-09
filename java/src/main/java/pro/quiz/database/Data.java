package pro.quiz.database;

import java.util.Optional;

import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import pro.quiz.models.Role;
import pro.quiz.models.RoleName;
import pro.quiz.models.Subject;
import pro.quiz.models.User;
import pro.quiz.repositories.RoleRepository;
import pro.quiz.repositories.SubjectRepository;

@Component
public class Data implements CommandLineRunner{

	private final RoleRepository roleRepository;
	private final SubjectRepository subjectRepository;
	
	public Data(RoleRepository roleRepository,SubjectRepository subjectRepository ) {
		this.roleRepository=roleRepository;
		this.subjectRepository=subjectRepository;
	}
	
	@Override
	public void run (String... args) throws Exception{
		
		Role r1=new Role();
		r1.setName(RoleName.ROLE_USER);
		roleRepository.save(r1);
		
		Role r2=new Role();
		r2.setName(RoleName.ROLE_ADMIN);
		roleRepository.save(r2);
		/*
		Subject s1=new Subject();
		s1.setName("Java");
		s1.setCanBack(true);
		s1.setCourse("demojava");
		s1.setDescription("opis");
		s1.setLimitedTime(false);
		s1.setMultipleChoice(true);
		s1.setNoQuestions((long) 5);
		s1.setRandomize(true);
		s1.setSeparatePage(false);
		s1.setSubject("j");
		subjectRepository.save(s1);
		*/
		/*
		Subject s2=new Subject();
		s2.setName("Web");
		s2.setCanBack(true);
		s2.setCourse("demoweb");
		s2.setDescription("opis");
		s2.setLimitedTime(false);
		s2.setMultipleChoice(true);
		s2.setNoQuestions((long) 5);
		s2.setRandomize(true);
		s2.setSeparatePage(false);
		s2.setSubject("w");
		subjectRepository.save(s2);
		*/
		
		User u1=new User();
		u1.setEmail("user111@gmail.com");
		u1.setUsername("user111");
		u1.setPassword("user111");
		
		Role userR = this.roleRepository.findByName(RoleName.ROLE_USER).orElse(null);
		u1.setRole(userR);
		
		
		
		
	}
}
