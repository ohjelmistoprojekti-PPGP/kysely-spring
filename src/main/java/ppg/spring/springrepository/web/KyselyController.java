package ppg.spring.springrepository.web;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import ppg.spring.springrepository.domain.Kysely;
import ppg.spring.springrepository.domain.KyselyRepository;
import ppg.spring.springrepository.domain.KysymysRepository;

@Controller
public class KyselyController {

    private KyselyRepository kyselyRepository;
    private KysymysRepository kysymysRepository;

    public KyselyController(KyselyRepository kyselyRepository, KysymysRepository kysymysRepository) {
        this.kyselyRepository = kyselyRepository;
        this.kysymysRepository = kysymysRepository;
    }

    // Get request index
    @GetMapping("/index")
    public String getKyselyt(Model model) {
        return "index"; // index.html
    }

    @RequestMapping("/addkysely")
    public String addBook(Model model) {
        model.addAttribute("kysely", new Kysely());
        // model.addAttribute("kysymykset", kysymysRepository.findAll());
        return "addnewkysely"; // addnewkysely.html
    }

    @PostMapping("/save")
    public String save(Kysely kysely) {
        kyselyRepository.save(kysely);
        return "redirect:index";
    }

}
