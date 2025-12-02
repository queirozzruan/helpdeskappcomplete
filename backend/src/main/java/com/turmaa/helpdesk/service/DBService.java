package com.turmaa.helpdesk.service;

import com.turmaa.helpdesk.domain.Chamado;
import com.turmaa.helpdesk.domain.Cliente;
import com.turmaa.helpdesk.domain.Tecnico;
import com.turmaa.helpdesk.domain.enums.Perfil;
import com.turmaa.helpdesk.domain.enums.Prioridade;
import com.turmaa.helpdesk.domain.enums.Status;
import com.turmaa.helpdesk.repositories.ChamadoRepository;
import com.turmaa.helpdesk.repositories.ClienteRepository;
import com.turmaa.helpdesk.repositories.TecnicoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Serviço responsável por inicializar o banco de dados com dados de exemplo.
 * Cria e salva instâncias de técnicos, clientes e chamados no banco de dados.
 */

@Service
public class DBService {


    @Autowired private TecnicoRepository tecnicoRepository;
    @Autowired private ClienteRepository clienteRepository;
    @Autowired private ChamadoRepository chamadoRepository;

    @Autowired
    private BCryptPasswordEncoder encoder; // Injeção do encoder de senhas

    /**
     * Inicializa o banco de dados com dados de exemplo.
     * Cria um técnico, um cliente e um chamado, e os salva nos respectivos repositórios.
     */

    public void instanciaDB() {
        Tecnico tec1 = new Tecnico(null, "Bill Gates", "76288389942", "bill@mail.com", encoder.encode("123"));
        tec1.addPerfil(Perfil.ADMIN);

        Cliente cli1 = new Cliente(null, "Linus Torvalds", "78394847593", "linus@mail.com", encoder.encode("123"));

        Chamado cha1 = new Chamado(null, Prioridade.MEDIA, Status.ANDAMENTO,
                "Chamado 01", "primeiro chamado", tec1, cli1);

        tecnicoRepository.saveAll(List.of(tec1));
        clienteRepository.saveAll(List.of(cli1));
        chamadoRepository.saveAll(List.of(cha1));
    }
}
