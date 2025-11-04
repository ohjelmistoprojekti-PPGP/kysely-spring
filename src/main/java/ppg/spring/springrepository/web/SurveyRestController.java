package ppg.spring.springrepository.web;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import ppg.spring.springrepository.domain.QuestionRepository;
import ppg.spring.springrepository.domain.Survey;
import ppg.spring.springrepository.domain.SurveyRepository;

@RestController
@RequestMapping("/api")
public class SurveyRestController {

    @Autowired
    private SurveyRepository surveyRepository;

    @Autowired
    private QuestionRepository questionRepository;

    // RESTful service to get all surveys
    @GetMapping("/surveys")
    public List<Survey> getAllSurveys() {
	return (List<Survey>) surveyRepository.findAll();
    }
    // Yksittäinen kysely
    @GetMapping("/api/surveys/{id}")
    public Survey getSurveyById(@PathVariable Long id) {
    return surveyRepository.findById(id)
            .orElseThrow(() -> new RuntimeException("Survey not found: " + id));
}


}



