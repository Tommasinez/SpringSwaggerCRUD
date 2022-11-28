package it.advancia.crud.service;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentMatchers;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import it.advancia.crud.model.Tutorial;
import it.advancia.crud.repository.TutorialRepository;


@ExtendWith(SpringExtension.class)
class TutorialServiceImplTest {
	
    @Mock
	TutorialRepository tutorialRepository;
	
    @InjectMocks
    TutorialServiceImpl tutorialServiceImpl;

	@Test
	void testGetAllTutorials() {
		String noTitle = null;
		String title = "title";
		
        List<Tutorial> tutorials = new ArrayList<>();
        List<Tutorial> tutorialsByTitle = new ArrayList<>();

        tutorials.add(new Tutorial());
        tutorialsByTitle.add(new Tutorial());
		
        when(tutorialRepository.findAll()).thenReturn(tutorials);
        when(tutorialRepository.findByTitleContaining(title)).thenReturn(tutorials);

        List<Tutorial> expected = tutorialServiceImpl.getAllTutorials(noTitle);
        List<Tutorial> expectedByTitle = tutorialServiceImpl.getAllTutorials(title);
        
        assertThat(expected).usingRecursiveComparison().isEqualTo(tutorials);
        assertThat(expectedByTitle).usingRecursiveComparison().isEqualTo(tutorialsByTitle);

        verify(tutorialRepository).findAll();
	}

	@Test
	void testGetTutorialById() {
		Tutorial tutorial = new Tutorial();
		
		when(tutorialRepository.findById(tutorial.getId())).thenReturn(Optional.of(tutorial));
		
		Optional<Tutorial> expected = tutorialServiceImpl.getTutorialById(tutorial.getId());
		
        assertThat(expected).isEqualTo(Optional.of(tutorial));

        verify(tutorialRepository).findById(tutorial.getId());

	}

	@Test
	void testCreateTutorial() {
		Tutorial tutorial = new Tutorial();
		tutorial.setTitle("Test Title");
		tutorial.setDescription("Test Description");
		tutorial.setPublished(false);

		when(tutorialRepository.save(ArgumentMatchers.any(Tutorial.class))).thenReturn(tutorial);

		Tutorial created = tutorialServiceImpl.createTutorial(tutorial);

		assertThat(created.getTitle()).isSameAs(tutorial.getTitle());
		assertThat(created.getDescription()).isSameAs(tutorial.getDescription());
		assertThat(created.isPublished()).isSameAs(tutorial.isPublished());
		
		verify(tutorialRepository).save(ArgumentMatchers.any(Tutorial.class));
	}

	@Test
	void testUpdateTutorial() {
        Tutorial tutorial = new Tutorial();
        tutorial.setId(1);
		tutorial.setTitle("Test Title");
		tutorial.setDescription("Test Description");
		tutorial.setPublished(false);

        Tutorial newTutorial = new Tutorial();
        newTutorial.setTitle("New Test Title");
        newTutorial.setDescription("New Test Description");
        newTutorial.setPublished(true);

		when(tutorialRepository.findById(tutorial.getId())).thenReturn(Optional.of(tutorial));
		when(tutorialRepository.save(newTutorial)).thenReturn(newTutorial);

		Tutorial updated = tutorialServiceImpl.updateTutorial(tutorial.getId(), newTutorial);
		
		assertThat(updated.getTitle()).isSameAs(newTutorial.getTitle());
		assertThat(updated.getDescription()).isSameAs(newTutorial.getDescription());
		assertThat(updated.isPublished()).isSameAs(newTutorial.isPublished());
		
        verify(tutorialRepository).findById(tutorial.getId());
        //verify(tutorialRepository).save(newTutorial); 
	}

	@Test
	void testDeleteTutorial() {
        Tutorial tutorial = new Tutorial();
        tutorial.setId(1);
        tutorial.setTitle("Test Title");

        when(tutorialRepository.findById(tutorial.getId())).thenReturn(Optional.of(tutorial));

        tutorialServiceImpl.deleteTutorial(tutorial.getId());
        verify(tutorialRepository).deleteById(tutorial.getId());
	}

	@Test
	void testFindByPublished() {
        List<Tutorial> tutorials = new ArrayList<>();
        
		Tutorial tutorial = new Tutorial();
		tutorial.setPublished(true);

        tutorials.add(tutorial);
		
        when(tutorialRepository.findByPublished(true)).thenReturn(tutorials);

        List<Tutorial> expected = tutorialServiceImpl.findByPublished();
        
        assertThat(expected).usingRecursiveComparison().isEqualTo(tutorials);

        verify(tutorialRepository).findByPublished(tutorial.isPublished());    
	}
}
