package fr.dopse.maildump;

import com.fasterxml.jackson.databind.Module;
import com.fasterxml.jackson.datatype.hibernate5.Hibernate5Module;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class MaildumpApplication {

	public static void main(String[] args) {
		SpringApplication.run(MaildumpApplication.class, args);
	}

    @Bean
    public Module hibernate5Module() {
        return new Hibernate5Module();
    }
}
