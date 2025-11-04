package ppg.spring.springrepository.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import ppg.spring.springrepository.domain.SurveyRepository;

@Controller
public class SurveyRestController {

    @Autowired
    private SurveyRepository surveyRepository;


}
