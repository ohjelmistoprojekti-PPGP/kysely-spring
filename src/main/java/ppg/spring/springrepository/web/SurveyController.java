package ppg.spring.springrepository.web;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

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
    // Shows all surveys
    @GetMapping("/index")
    public String getKyselyt(Model model) {
        model.addAttribute("surveys", surveyRepository.findAll());
        return "index"; // index.html
    }

    // Add a new survey
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

        model.addAttribute("survey", survey);
        return "addnewsurvey"; // addnewsurvey.html
    }

    @PostMapping("/addsurvey")
    public String saveSurvey(Survey survey) {
        if (survey.getQuestions() != null) {
            for (Question q : survey.getQuestions()) {
                q.setSurvey(survey);
            }
        }
        surveyRepository.save(survey);
        return "redirect:/index";
    }

    // Show a specific survey
    @GetMapping("/viewsurvey/{id}")
    public String viewSurvey(@PathVariable("id") Long id, Model model) {
        Survey survey = surveyRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Invalid survey ID: " + id));

        model.addAttribute("survey", survey);
        model.addAttribute("questions", survey.getQuestions());
        return "viewsurvey"; // viewsurvey.html
    }

    // Edit survey
    @GetMapping("/editsurvey/{id}")
    public String editSurvey(@PathVariable("id") Long id, Model model) {
        Survey survey = surveyRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Invalid survey ID: " + id));
        survey.getQuestions().size();
        model.addAttribute("survey", survey);
        return "editsurvey"; // TO-DO: editsurvey.html
    }

    @PostMapping("/editsurvey/{id}")
    public String saveEditedSurvey(@PathVariable("id") Long id, @ModelAttribute Survey updatedSurvey) {
        Survey existingSurvey = surveyRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Invalid survey ID: " + id));

        // Päivitä peruskentät
        existingSurvey.setSurveyName(updatedSurvey.getSurveyName());
        existingSurvey.setSurveyDesc(updatedSurvey.getSurveyDesc());
        existingSurvey.setStartingDate(updatedSurvey.getStartingDate());
        existingSurvey.setEndingDate(updatedSurvey.getEndingDate());

        // Varmistetaan, että kysymyslista ei ole null
        if (existingSurvey.getQuestions() == null) {
            existingSurvey.setQuestions(new ArrayList<>());
        }

        // Luo uusi lista päivitettyjä kysymyksiä varten
        List<Question> newQuestions = new ArrayList<>();

        if (updatedSurvey.getQuestions() != null) {
            for (Question q : updatedSurvey.getQuestions()) {
                if (q.getQuestionText() != null && !q.getQuestionText().trim().isEmpty()) {

                    // Jos olemassa oleva ID, päivitetään olemassa oleva kysymys
                    if (q.getQuestionId() != null) {
                        Question existing = existingSurvey.getQuestions().stream()
                                .filter(x -> x.getQuestionId().equals(q.getQuestionId()))
                                .findFirst()
                                .orElse(null);

                        if (existing != null) {
                            existing.setQuestionText(q.getQuestionText());
                            newQuestions.add(existing);
                        } else {
                            // Jos ei löydy, lisätään uutena
                            q.setSurvey(existingSurvey);
                            newQuestions.add(q);
                        }
                    } else {
                        // Uusi kysymys
                        q.setSurvey(existingSurvey);
                        newQuestions.add(q);
                    }
                }
            }
        }

        // Korvataan vanha lista uudella (turvallisesti)
        existingSurvey.setQuestions(newQuestions);

        surveyRepository.save(existingSurvey);

        return "redirect:/viewsurvey/" + id;
    }
}