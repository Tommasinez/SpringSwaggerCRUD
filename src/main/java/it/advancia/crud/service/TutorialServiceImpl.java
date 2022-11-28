package it.advancia.crud.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import it.advancia.crud.model.Tutorial;
import it.advancia.crud.repository.TutorialRepository;

@Service
public class TutorialServiceImpl implements TutorialService {
	@Autowired
	TutorialRepository tutorialRepository;

	@Override
	public List<Tutorial> getAllTutorials(String title) {
		List<Tutorial> tutorials = new ArrayList<>();
		if (title == null)
			tutorials.addAll(tutorialRepository.findAll());
		else
			tutorials.addAll(tutorialRepository.findByTitleContaining(title));
		return tutorials;
	}

	@Override
	public Optional<Tutorial> getTutorialById(long id) {
		return tutorialRepository.findById(id);
	}

	@Override
	public Tutorial createTutorial(Tutorial tutorial) {
		return tutorialRepository.save(new Tutorial(tutorial.getTitle(), tutorial.getDescription(), false));
	}

	@Override
	public Tutorial updateTutorial(long id, Tutorial tutorial) {
		Optional<Tutorial> tutorialData = tutorialRepository.findById(id);
		Tutorial currentTutorial = tutorialData.get();
		currentTutorial.setTitle(tutorial.getTitle());
		currentTutorial.setDescription(tutorial.getDescription());
		currentTutorial.setPublished(tutorial.isPublished());
		tutorialRepository.save(currentTutorial);
		return currentTutorial;
	}

	@Override
	public void deleteTutorial(long id) {
		tutorialRepository.deleteById(id);
	}

	@Override
	public void deleteAllTutorials() {
		tutorialRepository.deleteAll();
	}

	@Override
	public List<Tutorial> findByPublished() {
		return tutorialRepository.findByPublished(true);
	}
}
