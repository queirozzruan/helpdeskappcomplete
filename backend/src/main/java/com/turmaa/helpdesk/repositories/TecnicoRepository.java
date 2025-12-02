package com.turmaa.helpdesk.repositories;

import com.turmaa.helpdesk.domain.Pessoa;
import org.springframework.data.jpa.repository.JpaRepository;

import com.turmaa.helpdesk.domain.Tecnico;

import java.util.Optional;

/**
 * Repositório JPA para a entidade Técnico.
 * Fornece operações CRUD e consultas personalizadas para gerenciar técnicos no sistema de helpdesk.
 */

public interface TecnicoRepository extends JpaRepository<Tecnico, Integer> {

    Optional<Pessoa> findByCpf(String cpf);
    Optional<Pessoa> findByEmail(String email);

}
