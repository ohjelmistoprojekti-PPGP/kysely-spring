package ppg.spring.springrepository.web;

import java.util.ArrayList;
import java.util.stream.Collectors;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
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

        model.addAttribute("survey", survey);
        return "addnewsurvey"; // addnewsurvey.html
    }

    // @PostMapping("/addartist")
    // public String saveArtist(@ModelAttribute Artist artist) {
    // if (artist.getAlbums() != null) {
    // for (Album album : artist.getAlbums()) {
    // album.setArtist(artist);
    // if (album.getSongs() != null) {
    // List<Song> cleaned = album.getSongs().stream()
    // .filter(s -> s.getTitle() != null && !s.getTitle().isBlank())
    // .peek(s -> s.setAlbum(album))
    // .collect(Collectors.toList());
    // album.setSongs(cleaned);
    // }
    // }
    // }
    // artistRepository.save(artist);
    // return "redirect:/musiclist";
    // }

    // @PostMapping("/savekysely")
    // public String save(Kysely kysely) {
    // surveyRepository.save(kysely);
    // return "redirect:index";
    // }

}
