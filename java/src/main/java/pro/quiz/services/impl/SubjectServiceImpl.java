package pro.quiz.services.impl;

import org.springframework.stereotype.Service;

import pro.quiz.models.Subject;
import pro.quiz.repositories.SubjectRepository;
import pro.quiz.repositories.UserRepository;
import pro.quiz.services.SubjectService;
import pro.quiz.services.UserService;

@Service
public class SubjectServiceImpl implements SubjectService{

	private final SubjectRepository subjectRepository;
	public SubjectServiceImpl (SubjectRepository subjectRepository)
	{
		this.subjectRepository=subjectRepository;
	}
	
	public Subject getDemoSubjectByCourse(String course) {
		
		return this.subjectRepository.getSubjectByCourse(course);
	}
	
	public Subject getSubjectById(Long id) {
		
		return this.subjectRepository.getSubjectById(id);
	}
}
