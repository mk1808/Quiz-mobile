package pro.quiz.services;

import java.util.List;

import pro.quiz.models.Question;
import pro.quiz.models.Subject;
import pro.quiz.services.impl.SubjectServiceImpl.Result;

public interface SubjectService {
	
	Subject getDemoSubjectByCourse(String course);
	Subject getSubjectById(Long id);
	Result checkAnswersForDemo(List<Question> questions);
	/*
	Place getPlaceById(Long id);
	List<Place> getPlaces();
	Place createPlace(Place place);
	Place updatePlace(Long id, Place place);
void deletePlace(Long id);*/
}
