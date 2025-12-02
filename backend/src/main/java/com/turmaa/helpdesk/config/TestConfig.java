package com.turmaa.helpdesk.config;

import com.turmaa.helpdesk.service.DBService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

@Configuration
@Profile("test")

public class TestConfig {

    /**
     * Serviço responsável por popular/instanciar a base de dados com dados iniciais
     * para ambiente de teste.
     */

    @Autowired
    private DBService dbService;

    /**
     * Bean void criado apenas para executar a inicialização da base de dados
     * quando aplicável.
     *
     */

    @Bean
    public void instanciaDB() {
        this.dbService.instanciaDB();
    }

}
