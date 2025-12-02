package com.turmaa.helpdesk.repositories;

import com.turmaa.helpdesk.domain.Pessoa;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

/**
 * Repositório JPA para a entidade Pessoa.
 * Fornece operações CRUD e consultas personalizadas para gerenciar pessoas no sistema de helpdesk.
 */

public interface PessoaRepository extends JpaRepository<Pessoa, Integer> {

    Optional<Pessoa> findByEmail(String email);

    Optional<Pessoa> findByCpf(String cpf);
}