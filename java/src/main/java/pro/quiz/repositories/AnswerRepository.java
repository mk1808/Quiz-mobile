package pro.quiz.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import pro.quiz.models.Answer;

public interface AnswerRepository extends JpaRepository<Answer, Long>{
	}

