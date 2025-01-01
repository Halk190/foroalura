package com.challenge.foroalura;

import com.challenge.foroalura.domain.usuario.Perfil;
import com.challenge.foroalura.domain.usuario.UsuarioService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication(scanBasePackages = "com.challenge.foroalura")
//@ComponentScan(basePackages = "com.challenge.foroalura")
public class ForoaluraApplication {

	public static void main(String[] args) {
		SpringApplication.run(ForoaluraApplication.class, args);
	}
}
