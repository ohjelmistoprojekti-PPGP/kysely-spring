package ppg.spring.springrepository.web;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import ppg.spring.springrepository.domain.Question;
import ppg.spring.springrepository.domain.QuestionRepository;
import ppg.spring.springrepository.domain.Response;
import ppg.spring.springrepository.domain.ResponseRepository;
import ppg.spring.springrepository.domain.Survey;
import ppg.spring.springrepository.domain.SurveyRepository;

@CrossOrigin(origins = "http://localhost:5173")
@RestController
@RequestMapping("/api")
public class SurveyRestController {

    private SurveyRepository surveyRepository;
    private QuestionRepository questionRepository;
    private ResponseRepository responseRepository;

    public SurveyRestController(SurveyRepository surveyRepository, QuestionRepository questionRepository,
            ResponseRepository responseRepository) {
        this.surveyRepository = surveyRepository;
        this.questionRepository = questionRepository;
        this.responseRepository = responseRepository;
    }

    // Kaikki kyselyt
    @GetMapping("/surveys")
    public List<Survey> getAllSurveys() {
        return (List<Survey>) surveyRepository.findAll();
    }

    // Yksittäinen kysely
    @GetMapping("/surveys/{id}")
    public Survey getSurveyById(@PathVariable Long id) {
        return surveyRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Survey not found: " + id));
    }

    // Näyttää tietyn kyselyn kysymykset (V7- "vastaaja voi katsoa kyselyn")
    @GetMapping("/surveys/{id}/questions")
    public List<Question> getQuestionsBySurvey(@PathVariable Long id) {
        Survey survey = surveyRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Survey not found"));
        return survey.getQuestions();
    }

    // Tallentaa vastaukset tiettyyn kyselyyn tiettyyn kysymykseen
    @PostMapping("/surveys/{id}/responses")
    public @ResponseBody List<Response> saveResponses(@PathVariable Long id, @RequestBody List<Response> responses) {
    responses.forEach(r -> {
        Long questionId = r.getQuestion().getQuestionId();  // frontend lähettää kysymysolion ja vastaustekstin
        Question question = questionRepository.findById(questionId)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Question not found"));
        r.setQuestion(question);
    });
    return (List<Response>) responseRepository.saveAll(responses);
}
    // Hakee kaikki vastaukset tiettyyn kyselyyn
    @GetMapping("/surveys/{id}/responses")
    public List<Response> getResponsesBySurvey(@PathVariable Long id) {
    Survey survey = surveyRepository.findById(id)
            .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Survey not found"));
    List<Question> questions = survey.getQuestions();

    return responseRepository.findByQuestionIn(questions);
}


}