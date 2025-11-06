package ppg.spring.springrepository.web;

import java.util.ArrayList;

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

        // Update the fields
        existingSurvey.setSurveyName(updatedSurvey.getSurveyName());
        existingSurvey.setSurveyDesc(updatedSurvey.getSurveyDesc());
        existingSurvey.setStartingDate(updatedSurvey.getStartingDate());
        existingSurvey.setEndingDate(updatedSurvey.getEndingDate());

        if (updatedSurvey.getQuestions() != null) {
            for (Question updatedQuestion : updatedSurvey.getQuestions()) {
                if (updatedQuestion.getQuestionText() == null || updatedQuestion.getQuestionText().isBlank()) {
                    continue;
                }

                if (updatedQuestion.getQuestionId() != null) {

                    // Update existing question
                    Question existingQuestion = existingSurvey.getQuestions().stream()
                            .filter(q -> q.getQuestionId().equals(updatedQuestion.getQuestionId()))
                            .findFirst()
                            .orElse(null);

                    if (existingQuestion != null) {
                        existingQuestion.setQuestionText(updatedQuestion.getQuestionText());

                    } else {
                        // ID not found => add as new
                        updatedQuestion.setSurvey(existingSurvey);
                        existingSurvey.getQuestions().add(updatedQuestion);
                    }
                } else {
                    // New question
                    updatedQuestion.setSurvey(existingSurvey);
                    existingSurvey.getQuestions().add(updatedQuestion);
                }
            }

            // Remove nonexistent questions from the form
            existingSurvey.getQuestions().removeIf(existing -> updatedSurvey.getQuestions().stream()
                    .noneMatch(updated -> existing.getQuestionId() != null &&
                            existing.getQuestionId().equals(updated.getQuestionId())));
        }

        surveyRepository.save(existingSurvey);
        return "redirect:/viewsurvey/" + id;
    }
}
