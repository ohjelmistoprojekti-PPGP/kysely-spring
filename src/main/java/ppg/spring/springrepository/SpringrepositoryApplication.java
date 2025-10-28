package ppg.spring.springrepository;

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
	public CommandLineRunner testiKyselyData(SurveyRepository SurveyRepository, QuestionRepository QuestionRepository) {
		return (args) -> {
			log.info("Save kysely");
			survey survey1 = new Survey("Eläintesti", "Selvitä mikä eläin olet", "28.10.2025", "29.10.2025",
					"30.10.2025");
			SurveyRepository.save(survey1);

			survey1.getKysymykset().add(new Question("Oletko viekas", survey1));
			survey1.getKysymykset().add(new Question("Oletko älykäs", survey1));
			survey1.getKysymykset().add(new Question("Oletko lempeä", survey1));

			SurveyRepository.save(survey1);

		};
	}

}
