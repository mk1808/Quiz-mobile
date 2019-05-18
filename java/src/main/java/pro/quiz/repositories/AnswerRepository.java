package pro.quiz.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import pro.quiz.models.Answer;
import pro.quiz.models.Question;
import pro.quiz.models.Subject;

public interface AnswerRepository extends JpaRepository<Answer, Long>{
	
	List<Answer> getAnswersByQuestion(Question question);
	}

