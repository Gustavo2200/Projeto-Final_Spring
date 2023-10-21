package dio.me;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(scanBasePackages = "dio.me.domain.model")
public class SantanderDevWeek2023Application {

	public static void main(String[] args) {
		SpringApplication.run(SantanderDevWeek2023Application.class, args);
	}

}
