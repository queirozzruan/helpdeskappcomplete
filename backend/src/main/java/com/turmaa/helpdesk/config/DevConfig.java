package com.turmaa.helpdesk.config;

import com.turmaa.helpdesk.service.DBService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

/**
 * Classe de configuração para o ambiente de desenvolvimento (\@Profile("dev")).
 * <p>
 * Esta configuração expõe um bean booleano que, durante a inicialização do contexto
 * Spring, verifica o valor da propriedade `spring.jpa.hibernate.ddl-auto`. Se o valor
 * for igual a {@code "create"}, a base de dados é populada chamando
 * {@link DBService#instanciaDB()}.
 * </p>
 */

@Configuration
@Profile("dev")
public class DevConfig {

    /**
     * Serviço responsável por popular/instanciar a base de dados com dados iniciais
     * para ambiente de desenvolvimento.
     */

    @Autowired
    private DBService dbService;

    /**
     * Valor da propriedade `spring.jpa.hibernate.ddl-auto` definido nas propriedades
     * da aplicação. Usado para decidir se a instânciação da base de dados deve ser
     * executada (por exemplo, quando o valor for "create").
     */

    @Value("${spring.jpa.hibernate.ddl-auto}")
    private String value;

    /**
     * Bean booleano criado apenas para executar a inicialização da base de dados
     * quando aplicável.
     *
     * @return {@code true} se {@link DBService#instanciaDB()} foi executado (quando
     *         a propriedade {@code spring.jpa.hibernate.ddl-auto} for igual a
     *         {@code "create"}); {@code false} caso contrário.
     */

    @Bean
    public boolean instanciaDB() {
        if(value.equals("create")) {
            this.dbService.instanciaDB();
            return true;
        }
        return false;
    }
}
