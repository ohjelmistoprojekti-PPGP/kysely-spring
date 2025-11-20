package ppg.spring.springrepository.web;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import ppg.spring.springrepository.domain.Question;
import ppg.spring.springrepository.domain.QuestionRepository;
import ppg.spring.springrepository.domain.Survey;
import ppg.spring.springrepository.domain.SurveyRepository;

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

    @GetMapping("/")
    public String redirectToKyselyt() {
        return "redirect:/index";
    }

    // Add a new survey
    @GetMapping("/addsurvey")
    public String addSurveyForm(Model model) {
        Survey survey = new Survey();
        survey.setSurveyName("");
        survey.setSurveyDesc("");
        survey.setCreatedDate(LocalDateTime.now());
        survey.setStartingDate("");
        survey.setEndingDate("");
        survey.setQuestions(new ArrayList<>());

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
        survey.setCreatedDate(LocalDateTime.now());
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

        // Updates the basic fields
        existingSurvey.setSurveyName(updatedSurvey.getSurveyName());
        existingSurvey.setSurveyDesc(updatedSurvey.getSurveyDesc());
        existingSurvey.setStartingDate(updatedSurvey.getStartingDate());
        existingSurvey.setEndingDate(updatedSurvey.getEndingDate());

        // Confirms, that questionlist != null
        if (existingSurvey.getQuestions() == null) {
            existingSurvey.setQuestions(new ArrayList<>());
        }

        // Creates a new list for the updated questions
        List<Question> newQuestions = new ArrayList<>();

        if (updatedSurvey.getQuestions() != null) {
            for (Question q : updatedSurvey.getQuestions()) {
                if (q.getQuestionText() != null && !q.getQuestionText().trim().isEmpty()) {

                    // If the ID exists, the exising question is updated
                    if (q.getQuestionId() != null) {
                        Question existing = existingSurvey.getQuestions().stream()
                                .filter(x -> x.getQuestionId().equals(q.getQuestionId()))
                                .findFirst()
                                .orElse(null);

                        if (existing != null) {
                            existing.setQuestionText(q.getQuestionText());
                            newQuestions.add(existing);
                        } else {
                            // if not, the question is added as new
                            q.setSurvey(existingSurvey);
                            newQuestions.add(q);
                        }
                    } else {
                        // New question
                        q.setSurvey(existingSurvey);
                        newQuestions.add(q);
                    }
                }
            }
        }

        // Modifies the managed collection
        existingSurvey.getQuestions().clear();
        for (Question q : newQuestions) {
            // ensure bidirectional link
            q.setSurvey(existingSurvey);
            existingSurvey.getQuestions().add(q);
        }

        surveyRepository.save(existingSurvey);
        return "redirect:/viewsurvey/" + id;
    }

    // Delete survey (with confirmation)
    @GetMapping("/deletesurvey/{id}")
    public String confirmDeleteSurvey(@PathVariable Long id, Model model) {
        model.addAttribute("survey", surveyRepository.findById(id).orElse(null));
        return "deletesurvey";
    }

    @PostMapping("/deletesurvey/{id}")
    public String deleteSurvey(@PathVariable Long id) {
        surveyRepository.deleteById(id);
        return "redirect:/index";

    }
}