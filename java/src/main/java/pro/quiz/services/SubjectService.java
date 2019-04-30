package pro.quiz.services;

import pro.quiz.models.Subject;

public interface SubjectService {
	
	Subject getDemoSubjectByCourse(String course);
	Subject getSubjectById(Long id);
	/*
	Place getPlaceById(Long id);
	List<Place> getPlaces();
	Place createPlace(Place place);
	Place updatePlace(Long id, Place place);
void deletePlace(Long id);*/
}
