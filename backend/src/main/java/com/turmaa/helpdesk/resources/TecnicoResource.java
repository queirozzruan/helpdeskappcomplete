package com.turmaa.helpdesk.resources;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.turmaa.helpdesk.domain.Tecnico;
import com.turmaa.helpdesk.domain.dtos.TecnicoDTO;
import com.turmaa.helpdesk.service.TecnicoService;

/**
 * Controlador REST para a entidade {@link com.turmaa.helpdesk.domain.Tecnico}.
 * <p>
 * Esta classe define os endpoints da API para operações relacionadas aos técnicos.
 * Anotada com {@code @RestController} para indicar que é um controlador Spring que
 * lida com requisições REST, e com {@code @RequestMapping} para definir o caminho base
 * da URL para todos os seus endpoints.
 * </p>
 */
@RestController
@RequestMapping(value = "/tecnicos")
public class TecnicoResource {

    /**
     * Injeção de dependência da camada de serviço {@link TecnicoService}.
     * O Spring cria e gerencia a instância do serviço, que contém a lógica de negócio.
     */
    @Autowired
    private TecnicoService service;

    /**
     * Endpoint que busca um técnico por seu ID.
     * <p>
     * Responde a requisições GET para a URL {@code /tecnicos/{id}}. O valor do ID
     * é extraído da URL usando a anotação {@code @PathVariable}.
     * </p>
     *
     * @param id O ID do técnico a ser encontrado, extraído do caminho da URL.
     * @return Um {@link org.springframework.http.ResponseEntity} contendo o {@link TecnicoDTO}
     * correspondente ao ID. Retorna um status HTTP 200 (OK) se a busca for bem-sucedida.
     */
    @GetMapping(value = "/{id}")
    public ResponseEntity<TecnicoDTO> findById(@PathVariable Integer id) {

        Tecnico obj = service.findById(id);

        return ResponseEntity.ok().body(new TecnicoDTO(obj));
    }
}