package belleza.com.co.proyecto.belleza;
import io.github.cdimascio.dotenv.Dotenv;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.Objects;

@SpringBootApplication
public class ProyectoBellezaApplication {

	public static void main(String[] args) {

		Dotenv dotenv = Dotenv.load();

		// Establecer las variables de entorno
		System.setProperty("DB_URL", Objects.requireNonNull(dotenv.get("DB_URL")));
		System.setProperty("DB_USERNAME", Objects.requireNonNull(dotenv.get("DB_USERNAME")));
		System.setProperty("DB_PASSWORD", Objects.requireNonNull(dotenv.get("DB_PASSWORD")));
		System.setProperty("DB_DRIVER", Objects.requireNonNull(dotenv.get("DB_DRIVER")));
		System.setProperty("API_BASE_PATH", Objects.requireNonNull(dotenv.get("API_BASE_PATH")));

		SpringApplication.run(ProyectoBellezaApplication.class, args);
	}

}
