package it.advancia.crud.service;

import java.util.List;
import java.util.Optional;

import it.advancia.crud.model.Tutorial;

public interface TutorialService {
	List<Tutorial> getAllTutorials(String title);

	Optional<Tutorial> getTutorialById(long id);

	Tutorial createTutorial(Tutorial tutorial);

	Tutorial updateTutorial(long id, Tutorial tutorial);

	void deleteTutorial(long id);

	 void deleteAllTutorials();

	List<Tutorial> findByPublished();

}
