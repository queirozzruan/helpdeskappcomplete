package com.turmaa.helpdesk;


import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * Classe principal da aplicação Spring Boot para o sistema de helpdesk.
 * Inicializa e configura o contexto da aplicação.
 * Autor: Ruan Queiroz - Alex Silva
 */

@SpringBootApplication
public class HelpdeskturmaaApplication {

    // Método principal que inicia a aplicação Spring Boot.

    public static void main(String[] args) {
        SpringApplication.run(HelpdeskturmaaApplication.class, args);
    }

}