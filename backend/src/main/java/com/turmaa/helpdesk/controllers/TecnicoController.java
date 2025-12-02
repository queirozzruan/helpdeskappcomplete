package com.turmaa.helpdesk.controllers;

import com.turmaa.helpdesk.domain.Tecnico;
import com.turmaa.helpdesk.domain.dtos.TecnicoDTO;
import com.turmaa.helpdesk.service.TecnicoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Controlador REST para operações sobre técnicos.
 *
 * <p>Exponibiliza endpoints para listar, buscar por id, criar, atualizar e excluir
 * recursos do tipo {@link Tecnico} representados por {@link TecnicoDTO}.</p>
 *
 * <p>Mapeamento base: <code>/tecnicos</code></p>
 */

@RestController
@RequestMapping(value = "/tecnicos")
public class TecnicoController {

    @Autowired
    private TecnicoService service;

    /**
     * Endpoint para buscar todos os técnicos cadastrados.
     * Mapeado para a requisição GET /tecnicos
     * @return Uma lista de TecnicoDTO com os dados de todos os técnicos.
     */
    @GetMapping
    public ResponseEntity<List<TecnicoDTO>> findAll() {
        List<Tecnico> list = service.findAll();
        // Converte a lista de entidades Tecnico para uma lista de TecnicoDTO
        List<TecnicoDTO> listDTO = list.stream().map(TecnicoDTO::new).collect(Collectors.toList());
        return ResponseEntity.ok().body(listDTO);
    }

    /**
     * Endpoint para criar um novo técnico.
     * Mapeado para a requisição POST /tecnicos
     * @param dto O TecnicoDTO enviado no corpo da requisição.
     * @return Uma resposta com status 201 Created e a URL do novo recurso no cabeçalho.
     */
    @PostMapping
    public ResponseEntity<TecnicoDTO> create(@Valid @RequestBody TecnicoDTO dto) {
        Tecnico newObj = service.create(dto);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(newObj.getId()).toUri();
        return ResponseEntity.created(uri).build();
    }

    /**
     * Endpoint para buscar um técnico por ID.
     * Mapeado para a requisição GET /tecnicos/{id}
     * @param id O ID do técnico a ser buscado.
     * @return O TecnicoDTO correspondente ao ID fornecido.
     */


    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Integer id) {
        service.delete(id);
        return ResponseEntity.noContent().build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<TecnicoDTO> update(@PathVariable Integer id,
                                             @Valid @RequestBody TecnicoDTO dto) {
        TecnicoDTO newObj = new TecnicoDTO(service.update(id, dto));
        return ResponseEntity.ok().body(newObj);
    }


}