package drhiromu.anythingmarketmainservice;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class AnythingMarketMainServiceApplication {
	private static final Logger log = LoggerFactory.getLogger(AnythingMarketMainServiceApplication.class);


	public static void main(String[] args) {
		SpringApplication.run(AnythingMarketMainServiceApplication.class, args);

		log.debug(
				"\n----------------------------------------------------------------------\n\t" +
						"Application '{}' is running!\n" +
						"----------------------------------------------------------------------",
				AnythingMarketMainServiceApplication.class.getSimpleName()
		);
	}

}
