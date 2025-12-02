package com.turmaa.helpdesk.controllers;

import com.turmaa.helpdesk.domain.Chamado;
import com.turmaa.helpdesk.domain.dtos.ChamadoDTO;
import com.turmaa.helpdesk.service.ChamadoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Controlador REST para operações sobre chamados.
 *
 * <p>Exponibiliza endpoints para listar, buscar por id, criar, atualizar e excluir
 * recursos do tipo {@link Chamado} representados por {@link ChamadoDTO}.</p>
 *
 * <p>Mapeamento base: <code>/chamados</code></p>
 */

@RestController
@RequestMapping("/chamados")
public class ChamadoController {

    /**
     * Serviço de chamados injetado pelo Spring.
     * Usado para delegar a lógica de negócio relacionada a chamados.
     */

    @Autowired
    private ChamadoService service;

    /**
     * Endpoint para listar todos os chamados.
     *
     * @return Lista de {@link ChamadoDTO} representando todos os chamados.
     */

    @GetMapping
    public ResponseEntity<List<ChamadoDTO>> findAll() {
        List<ChamadoDTO> listDTO = service.findAll().stream()
                .map(ChamadoDTO::new)
                .collect(Collectors.toList());
        return ResponseEntity.ok(listDTO);
    }

    /**
     * Endpoint para buscar um chamado por ID.
     *
     * @param id ID do chamado a ser buscado.
     * @return {@link ChamadoDTO} representando o chamado encontrado.
     */

    @GetMapping("/{id}")
    public ResponseEntity<ChamadoDTO> findById(@PathVariable Integer id) {
        Chamado obj = service.findById(id);
        return ResponseEntity.ok(new ChamadoDTO(obj));
    }

    /**
     * Endpoint para criar um novo chamado.
     *
     * @param dto Dados do chamado a ser criado.
     * @return Resposta HTTP com status 201 (Created) e o URI do novo recurso no header Location.
     */


    @PostMapping
    public ResponseEntity<ChamadoDTO> create(@Valid @RequestBody ChamadoDTO dto) {
        Chamado newObj = service.create(dto);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
                .buildAndExpand(newObj.getId()).toUri();
        return ResponseEntity.created(uri).build();
    }

    /**
     * Endpoint para atualizar um chamado existente.
     *
     * @param id  ID do chamado a ser atualizado.
     * @param dto Dados atualizados do chamado.
     * @return {@link ChamadoDTO} representando o chamado atualizado.
     */


    @PutMapping("/{id}")
    public ResponseEntity<ChamadoDTO> update(@PathVariable Integer id,
                                             @Valid @RequestBody ChamadoDTO dto) {
        Chamado updated = service.update(id, dto);
        return ResponseEntity.ok(new ChamadoDTO(updated));
    }

    /**
     * Endpoint para excluir um chamado por ID.
     *
     * @param id ID do chamado a ser excluído.
     * @return Resposta HTTP com status 204 (No Content) se a exclusão for bem-sucedida.
     */

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Integer id) {
        service.delete(id);
        return ResponseEntity.noContent().build();
    }
}
