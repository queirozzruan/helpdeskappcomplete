package com.turmaa.helpdesk.repositories;

import com.turmaa.helpdesk.domain.Pessoa;
import org.springframework.data.jpa.repository.JpaRepository;

import com.turmaa.helpdesk.domain.Cliente;
import org.springframework.stereotype.Repository;

import java.util.Optional;

/**
 * Repositório JPA para a entidade Cliente.
 * Fornece operações CRUD e consultas personalizadas para gerenciar clientes no sistema de helpdesk.
 */

@Repository
public interface ClienteRepository extends JpaRepository<Cliente, Integer> {

    Optional<Pessoa> findByCpf(String cpf);
    Optional<Pessoa> findByEmail(String email);

}
