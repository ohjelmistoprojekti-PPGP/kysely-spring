package ppg.spring.springrepository.web;

import java.util.ArrayList;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import ppg.spring.springrepository.domain.Survey;
import ppg.spring.springrepository.domain.SurveyRepository;
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

    @GetMapping("/addkysely")
    public String showAddArtistForm(Model model) {
        Artist artist = new Artist();
        Album album = new Album();
        album.setTitle("");
        album.setReleaseYear(0);
        album.setSongs(new ArrayList<>());
        album.getSongs().add(new Song());
        album.getSongs().add(new Song());
        artist.setAlbums(new ArrayList<>());
        artist.getAlbums().add(album);

        model.addAttribute("artist", artist);
        return "addartist";
    }

    @PostMapping("/savekysely")
    public String save(Kysely kysely) {
        surveyRepository.save(kysely);
        return "redirect:index";
    }

}
