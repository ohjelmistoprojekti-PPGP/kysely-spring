package ppg.spring.springrepository.web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import ppg.spring.springrepository.domain.QuestionRepository;

@Controller
public class QuestionController {

    private QuestionRepository questionRepository;

    public QuestionController(QuestionRepository questionRepository) {
        this.questionRepository = questionRepository;
    }

    @GetMapping("/deletequestion/{id}")
    public String deleteQuestion(@PathVariable Long id) {
        questionRepository.deleteById(id);
        return "redirect:/addnewsurvey";
    }

}
