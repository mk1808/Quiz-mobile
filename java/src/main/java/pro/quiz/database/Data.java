package pro.quiz.database;

import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import pro.quiz.models.Role;
import pro.quiz.models.RoleName;
import pro.quiz.models.Subject;
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
		
		
	}
}
