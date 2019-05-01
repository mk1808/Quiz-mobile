package pro.quiz.repositories;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import pro.quiz.models.Question;
import pro.quiz.models.Role;
import pro.quiz.models.RoleName;
import pro.quiz.models.Subject;
import pro.quiz.services.impl.SubjectServiceImpl.Result;

public interface SubjectRepository extends JpaRepository<Subject, Long>{
	Subject getSubjectByCourse(String course);
	Subject getSubjectById(Long id);
	//Result checkAnswersForDemo(List<Question> questions);
	}

