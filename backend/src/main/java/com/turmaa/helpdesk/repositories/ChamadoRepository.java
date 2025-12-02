package com.turmaa.helpdesk.repositories;

import com.turmaa.helpdesk.domain.Chamado;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Repositório JPA para a entidade Chamado.
 * Fornece operações CRUD e consultas personalizadas para gerenciar chamados no sistema de helpdesk.
 */

@Repository
public interface ChamadoRepository extends JpaRepository<Chamado, Integer> {

}
