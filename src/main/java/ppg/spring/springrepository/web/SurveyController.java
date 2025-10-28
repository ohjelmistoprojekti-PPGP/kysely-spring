package ppg.spring.springrepository.web;

import java.util.ArrayList;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import ppg.spring.springrepository.domain.Survey;
import ppg.spring.springrepository.domain.SurveyRepository;
import ppg.spring.springrepository.domain.Question;
import ppg.spring.springrepository.domain.QuestionRepository;

@Controller
public class SurveyController {

    private SurveyRepository surveyRepository;
    private QuestionRepository questionRepository;

    public SurveyController(SurveyRepository surveyRepository, QuestionRepository questionRepository) {
        this.surveyRepository = surveyRepository;
        this.questionRepository = questionRepository;
    }

    // Get request index
    @GetMapping("/index")
    public String getKyselyt(Model model) {
        return "index"; // index.html
    }

    @GetMapping("/addsurvey")
    public String addSurveyForm(Model model) {
        Survey survey = new Survey();
        survey.setSurveyName("");
        survey.setSurveyDesc("");
        survey.setCreatedDate("");
        survey.setStartingDate("");
        survey.setEndingDate("");
        survey.setQuestions(new ArrayList<>());
        survey.getQuestions().add(new Question());
        survey.getQuestions().add(new Question());

        model.addAttribute("survey", survey);
        return "addnewsurvey";
    }

    // @PostMapping("/savekysely")
    // public String save(Kysely kysely) {
    // surveyRepository.save(kysely);
    // return "redirect:index";
    // }

}
