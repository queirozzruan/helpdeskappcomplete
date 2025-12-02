package com.turmaa.helpdesk.config;

import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

@Component
public class StartupBanner {

    /**
     * Método executado após a inicialização completa da aplicação.
     * Exibe uma mensagem de sucesso no console.
     */

    @EventListener(ApplicationReadyEvent.class)
    public void afterStartup() {
        System.out.println("****************************************");
        System.out.println("Aplicação HelpDesk Iniciada com sucesso!");
        System.out.println("****************************************");
    }
}
