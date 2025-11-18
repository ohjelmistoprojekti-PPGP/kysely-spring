package ppg.spring.springrepository;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import ppg.spring.springrepository.domain.Survey;
import ppg.spring.springrepository.domain.SurveyRepository;
import ppg.spring.springrepository.domain.Question;
import ppg.spring.springrepository.domain.QuestionRepository;

@SpringBootApplication
public class SpringrepositoryApplication {

	private static final Logger log = LoggerFactory.getLogger(SpringrepositoryApplication.class);

	public static void main(String[] args) {
		SpringApplication.run(SpringrepositoryApplication.class, args);
	}

	@Bean
	public CommandLineRunner SurveyTestData(SurveyRepository SurveyRepository, QuestionRepository QuestionRepository) {
		// Default survey
		return (args) -> {
			log.info("Save survey");
			Survey survey1 = new Survey("Eläintesti", "Selvitä mikä eläin olet", "12.12.2025", "12.12.2025");
			SurveyRepository.save(survey1);

			survey1.getQuestions().add(new Question("Oletko viekas", survey1, "text", List.of()));
			survey1.getQuestions().add(new Question("Oletko älykäs", survey1, "text", List.of()));
			survey1.getQuestions().add(new Question("Oletko lempeä", survey1, "text", List.of()));

			SurveyRepository.save(survey1);

			// seuraava kysely

			Survey hhkysely = new Survey("HH-kysely",
					"Kerro, mitä mieltä olet HH IT-Tradenomin koulutusohjelman opetuksen laadusta!", "13.11.2025",
					"22.12.2025");
			SurveyRepository.save(hhkysely);

			hhkysely.getQuestions()
					.add(new Question("Monennenko vuoden opiskelija olet?", hhkysely, "text", List.of()));
			hhkysely.getQuestions().add(new Question("Opintojesi suuntaus?", hhkysely, "text", List.of()));
			hhkysely.getQuestions().add(new Question("Opintojesi toteutusmuoto?", hhkysely, "text", List.of()));
			hhkysely.getQuestions().add(new Question("Ikäsi:", hhkysely, "text", List.of()));
			hhkysely.getQuestions().add(new Question("Sukupuolesi:", hhkysely, "text", List.of()));
			hhkysely.getQuestions()
					.add(new Question("Arviosi kurssitarjonnasta suuntautumisessasi:", hhkysely, "text",
							List.of()));
			hhkysely.getQuestions()
					.add(new Question("Arviosi opetuksen laadusta:", hhkysely, "text", List.of()));
			// (kommenttirivi: voit arvioida opetuksenlaatua keskimääräisesti tai
			// yksittäisten kurssien perusteella.
			// Mainitsethan kurssit nimeltä)
			hhkysely.getQuestions()
					.add(new Question("Kuinka hyvin koet opintojen valmistavan sinua työelämään?", hhkysely, "text",
							List.of()));
			// (kommenttirivi: voit arvioida opetuksenlaatua keskimääräisesti tai
			// yksittäisten kurssien perusteella.
			// Mainitsethan kurssit nimeltä)
			hhkysely.getQuestions()
					.add(new Question("Kuinka hyvin koet kurssien vastaavan kuvauksia?", hhkysely, "text",
							List.of()));
			// (kommenttirivi: voit arvioida opetuksenlaatua keskimääräisesti tai
			// yksittäisten kurssien perusteella.
			// Mainitsethan kurssit nimeltä)
			hhkysely.getQuestions().add(new Question(
					"Kuinka todennäköisesti suosittelisit omaa suuntautumistasi muille? (1-5)", hhkysely, "text",
					List.of()));
			hhkysely.getQuestions()
					.add(new Question(
							"Kuinka todennäköisesti suosittelisit HH IT-Tradenomin koulutusohjelmaa muille? (1-5)",
							hhkysely, "text", List.of()));
			hhkysely.getQuestions()
					.add(new Question("Vapaata palautetta/kehitysideoita:", hhkysely, "text", List.of()));

			SurveyRepository.save(hhkysely);

			// seuraava kysely
			Survey elementtikysely = new Survey("Elementtikysely", "1 monivalinta kysymys ja 1 tekstikysymys",
					"18.11.2025", "20.1.2.2025");
			SurveyRepository.save(elementtikysely);

			elementtikysely.getQuestions()
					.add(new Question("Minkä näistä valitsisit?", elementtikysely, "radioButton",
							List.of("tuli", "vesi", "ilma", "maa"))); // monivalinta
			elementtikysely.getQuestions()
					.add(new Question("Miksi valitsit juuri kyseisen elementin?", elementtikysely, "text",
							List.of()));

			SurveyRepository.save(elementtikysely);

		};

	}

}
