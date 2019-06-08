package pro.quiz.services.impl;

import java.util.List;

import org.springframework.stereotype.Service;

import pro.quiz.models.Question;
import pro.quiz.models.Subject;
import pro.quiz.models.User;
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
	
	public Result checkAnswersForDemo(List<Question> questions) {
		
		Long subjectId = questions.get(0).getSubject().getId();
		List<Question> correctQuestions=this.subjectRepository.getSubjectById(subjectId).getQuestions();
		int total=questions.size();
		int correct=0;
		System.out.println(total);
		Boolean qValue;
		Float result;
		for(int i=0; i<total; i++) {
			qValue=true;
			for (int j=0; j<questions.get(0).getAnswers().size();j++) {
				if(correctQuestions.get(i).getAnswers().get(j).getStatus()==
						questions.get(i).getAnswers().get(j).getStatus()) 
				{} else { qValue=false;}
				
			}if(qValue) correct++;
		}
		Result result2 = new Result();
		result=(float)correct/total;
		
		result2.correct=correct;
		result2.total=total;
		result2.result=result;
		return result2;
		
	}
	
	public List<Subject> getSubjectsByCourse(String course){
		List<Subject> subjects=this.subjectRepository.getSubjectsByCourse(course);
		return subjects;
	}
	
	public List<Subject> getSubjectsByUser(User user){
		return this.subjectRepository.getSubjectsByUser(user);
	}
	
	public String deleteSubject(Long id) {
		this.subjectRepository.deleteById(id);
		return "deleted";
	}
	
	public Subject createSubject(Subject subject) {
		return this.subjectRepository.save(subject);
	}
	
	public class Result{
		public int total;
		public int correct;
		public Float result;
		
	}
}
