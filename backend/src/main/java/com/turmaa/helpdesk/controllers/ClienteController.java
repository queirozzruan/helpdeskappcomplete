package com.turmaa.helpdesk.controllers;

import com.turmaa.helpdesk.domain.Cliente;
import com.turmaa.helpdesk.domain.dtos.ClienteDTO;
import com.turmaa.helpdesk.service.ClienteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Controlador REST para operações sobre clientes.
 *
 * <p>Exponibiliza endpoints para listar, buscar por id, criar, atualizar e excluir
 * recursos do tipo {@link Cliente} representados por {@link ClienteDTO}.</p>
 *
 * <p>Mapeamento base: <code>/clientes</code></p>
 */

@RestController
@RequestMapping("/clientes")
public class ClienteController {

    @Autowired
    private ClienteService service;

    /**
     * Endpoint para listar todos os clientes.
     *
     * @return Lista de {@link ClienteDTO} representando todos os clientes.
     */

    @GetMapping
    public ResponseEntity<List<ClienteDTO>> findAll() {
        List<ClienteDTO> listDTO = service.findAll().stream()
                .map(ClienteDTO::new)
                .collect(Collectors.toList());
        return ResponseEntity.ok(listDTO);
    }

    /**
     * Endpoint para buscar um cliente por ID.
     *
     * @param id ID do cliente a ser buscado.
     * @return {@link ClienteDTO} representando o cliente encontrado.
     */


    @GetMapping("/{id}")
    public ResponseEntity<ClienteDTO> findById(@PathVariable Integer id) {
        Cliente obj = service.findById(id);
        return ResponseEntity.ok(new ClienteDTO(obj));
    }

    /**
     * Endpoint para criar um novo cliente.
     *
     * @param dto Dados do cliente a ser criado.
     * @return {@link ClienteDTO} representando o cliente criado.
     */


    @PostMapping
    public ResponseEntity<ClienteDTO> create(@Valid @RequestBody ClienteDTO dto) {
        Cliente newObj = service.create(dto);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
                .buildAndExpand(newObj.getId()).toUri();
        return ResponseEntity.created(uri).build();
    }

    /**
     * Endpoint para atualizar um cliente existente.
     *
     * @param id  ID do cliente a ser atualizado.
     * @param dto Dados atualizados do cliente.
     * @return {@link ClienteDTO} representando o cliente atualizado.
     */


    @PutMapping("/{id}")
    public ResponseEntity<ClienteDTO> update(@PathVariable Integer id,
                                             @Valid @RequestBody ClienteDTO dto) {
        Cliente updated = service.update(id, dto);
        return ResponseEntity.ok(new ClienteDTO(updated));
    }

    /**
     * Endpoint para excluir um cliente por ID.
     *
     * @param id ID do cliente a ser excluído.
     * @return Resposta HTTP com status 204 (No Content) se a exclusão for bem-sucedida.
     */


    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Integer id) {
        service.delete(id);
        return ResponseEntity.noContent().build();
    }
}
