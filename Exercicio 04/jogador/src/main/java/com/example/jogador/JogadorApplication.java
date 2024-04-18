package com.example.jogador;

import com.example.jogador.services.RequisicaoService;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.io.IOException;

@SpringBootApplication
public class JogadorApplication {

	public static void main(String[] args) {
		SpringApplication.run(JogadorApplication.class, args);
	}

}
