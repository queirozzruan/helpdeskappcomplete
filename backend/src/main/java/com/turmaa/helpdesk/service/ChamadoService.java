package com.turmaa.helpdesk.service;

import com.turmaa.helpdesk.domain.Chamado;
import com.turmaa.helpdesk.domain.Cliente;
import com.turmaa.helpdesk.domain.Tecnico;
import com.turmaa.helpdesk.domain.dtos.ChamadoDTO;
import com.turmaa.helpdesk.domain.enums.Prioridade;
import com.turmaa.helpdesk.domain.enums.Status;
import com.turmaa.helpdesk.repositories.ChamadoRepository;
import com.turmaa.helpdesk.service.exceptions.ObjectNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

/**
 * Serviço para gerenciar operações relacionadas a Chamados.
 * Fornece métodos para criar, atualizar, buscar e deletar chamados.
 */

@Service
public class ChamadoService {

    @Autowired
    private ChamadoRepository repository;
    @Autowired
    private TecnicoService tecnicoService;
    @Autowired
    private ClienteService clienteService;

    /**
     * Busca um chamado pelo seu ID.
     * @param id O ID do chamado a ser buscado.
     * @return O chamado encontrado.
     * @throws ObjectNotFoundException se o chamado com o ID fornecido não existir.
     */

    public Chamado findById(Integer id) {
        Optional<Chamado> obj = repository.findById(id);
        return obj.orElseThrow(() -> new ObjectNotFoundException("Objeto não encontrado! ID: " + id));
    }

    /**
     * Retorna uma lista de todos os chamados.
     * @return Lista de todos os chamados.
     */

    public List<Chamado> findAll() {
        return repository.findAll();
    }

    /**
     * Cria um novo chamado a partir de um DTO.
     * @param dto O DTO contendo os dados do novo chamado.
     * @return O chamado criado.
     */

    public Chamado create(ChamadoDTO dto) {
        return repository.save(newChamado(dto));
    }

    /**
     * Atualiza um chamado existente com base no ID e nos dados do DTO.
     * @param id O ID do chamado a ser atualizado.
     * @param dto O DTO contendo os novos dados do chamado.
     * @return O chamado atualizado.
     * @throws ObjectNotFoundException se o chamado com o ID fornecido não existir.
     */

    public Chamado update(Integer id, ChamadoDTO dto) {
        dto.setId(id);
        Chamado oldObj = findById(id);
        oldObj = newChamado(dto);
        return repository.save(oldObj);
    }

    /**
     * Constrói um objeto Chamado a partir de um DTO.
     * Busca as entidades Tecnico e Cliente relacionadas usando os seus IDs.
     * @param dto O DTO de origem.
     * @return um novo objeto Chamado pronto para ser salvo no banco.
     */
    private Chamado newChamado(ChamadoDTO dto) {
        // Busca as entidades completas a partir dos IDs fornecidos no DTO
        Tecnico tecnico = tecnicoService.findById(dto.getTecnico());
        Cliente cliente = clienteService.findById(dto.getCliente());

        Chamado chamado = new Chamado();
        if(dto.getId() != null) {
            chamado.setId(dto.getId());
        }

        if(dto.getStatus().equals(2)) {
            chamado.setDataFechamento(LocalDate.now());
        }

        chamado.setTecnico(tecnico);
        chamado.setCliente(cliente);
        chamado.setPrioridade(Prioridade.toEnum(dto.getPrioridade()));
        chamado.setStatus(Status.toEnum(dto.getStatus()));
        chamado.setTitulo(dto.getTitulo());
        chamado.setObservacoes(dto.getObservacoes());
        return chamado;
    }

    /**
     * Deleta um chamado pelo seu ID.
     * @param id O ID do chamado a ser deletado.
     * @throws ObjectNotFoundException se o chamado com o ID fornecido não existir.
     */

    public void delete(Integer id) {
        Chamado obj = findById(id);
        repository.delete(obj);
    }

}