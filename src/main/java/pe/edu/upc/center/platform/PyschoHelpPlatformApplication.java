package pe.edu.upc.center.platform;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;


@SpringBootApplication
@EnableJpaAuditing


public class PyschoHelpPlatformApplication {

	public static void main(String[] args) {
		SpringApplication.run(PyschoHelpPlatformApplication.class, args);
		//inicilizar el perfil de administrador
	}

}
