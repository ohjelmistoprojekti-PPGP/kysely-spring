package ppg.spring.springrepository;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import ppg.spring.springrepository.domain.Kysely;
import ppg.spring.springrepository.domain.KyselyRepository;
import ppg.spring.springrepository.domain.Kysymys;
import ppg.spring.springrepository.domain.KysymysRepository;

@SpringBootApplication
public class SpringrepositoryApplication {

	private static final Logger log = LoggerFactory.getLogger(SpringrepositoryApplication.class);

	public static void main(String[] args) {
		SpringApplication.run(SpringrepositoryApplication.class, args);
	}

	@Bean
	public CommandLineRunner testiKyselyData(KyselyRepository kyselyRepository, KysymysRepository kysymysRepository) {
		return (args) -> {
			log.info("Save kysely");
			Kysely kysely1 = new Kysely("Eläintesti", "Selvitä mikä eläin olet", "28.10.2025", "29.10.2025", "30.10.2025");
			kyselyRepository.save(kysely1);

			kysely1.getKysymykset().add(new Kysymys("Oletko viekas", kysely1));
			kysely1.getKysymykset().add(new Kysymys("Oletko älykäs", kysely1));
			kysely1.getKysymykset().add(new Kysymys("Oletko lempeä", kysely1));

			kyselyRepository.save(kysely1);


		};
	}

}
