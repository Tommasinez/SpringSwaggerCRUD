package it.advancia.crud.controller;

import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import it.advancia.crud.model.Tutorial;
import it.advancia.crud.service.TutorialService;

@CrossOrigin(origins = "http://localhost:8080")
@RestController
@RequestMapping("/api")
public class TutorialController {
	@Autowired
	TutorialService tutorialService;

	@GetMapping("/tutorials/retrieve")
	public ResponseEntity<List<Tutorial>> getAllTutorials(@RequestParam(required = false) String title) {
		List<Tutorial> tutorials = tutorialService.getAllTutorials(title);
		if (tutorials.isEmpty()) {
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		}
		return new ResponseEntity<>(tutorials, HttpStatus.OK);
	}

	@GetMapping("/tutorials/retrieve/{id}")
	public ResponseEntity<Tutorial> getTutorialById(@PathVariable("id") long id) {
		Optional<Tutorial> tutorialData = tutorialService.getTutorialById(id);
		if (tutorialData.isEmpty()) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		} else {
			return new ResponseEntity<>(tutorialData.get(), HttpStatus.OK);
		}
	}

	@PostMapping("/tutorials/create")
	public ResponseEntity<Tutorial> createTutorial(@RequestBody Tutorial tutorial) {
		Tutorial newTutorial = tutorialService.createTutorial(tutorial);
		return new ResponseEntity<>(newTutorial, HttpStatus.CREATED);
	}

	@PutMapping("/tutorials/update/{id}")
	public ResponseEntity<Tutorial> updateTutorial(@PathVariable("id") long id, @RequestBody Tutorial tutorial) {
		Tutorial tutorialData = tutorialService.updateTutorial(id, tutorial);
		if (tutorialData != null) {
			return new ResponseEntity<>(tutorialData, HttpStatus.OK);
		}
		return new ResponseEntity<>(HttpStatus.NOT_FOUND);
	}

	@DeleteMapping("/tutorials/delete/{id}")
	public ResponseEntity<HttpStatus> deleteTutorial(@PathVariable("id") long id) {
		tutorialService.deleteTutorial(id);
		return new ResponseEntity<>(HttpStatus.NO_CONTENT);
	}

	@DeleteMapping("/tutorials/delete/all")
	public ResponseEntity<HttpStatus> deleteAllTutorials() {
		tutorialService.deleteAllTutorials();
		return new ResponseEntity<>(HttpStatus.NO_CONTENT);

	}

	@GetMapping("/tutorials/retrieve/published")
	public ResponseEntity<List<Tutorial>> findByPublished() {
		List<Tutorial> tutorials = tutorialService.findByPublished();
		if (tutorials.isEmpty()) {
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		}
		return new ResponseEntity<>(tutorials, HttpStatus.OK);

	}
}