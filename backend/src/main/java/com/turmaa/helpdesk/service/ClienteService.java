package com.turmaa.helpdesk.service;

import com.turmaa.helpdesk.domain.Cliente;
import com.turmaa.helpdesk.domain.Pessoa;
import com.turmaa.helpdesk.domain.dtos.ClienteDTO;
import com.turmaa.helpdesk.repositories.ClienteRepository;
import com.turmaa.helpdesk.repositories.TecnicoRepository;
import com.turmaa.helpdesk.service.exceptions.DataIntegrityViolationException;
import com.turmaa.helpdesk.service.exceptions.ObjectNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

// Serviço para gerenciar operações relacionadas a Clientes.
@Service
public class ClienteService {

    @Autowired
    private ClienteRepository repository;

    @Autowired
    private TecnicoRepository tecnicoRepository;

    @Autowired
    private PasswordEncoder encoder;

    public Cliente findById(Integer id) {
        Optional<Cliente> obj = repository.findById(id);
        return obj.orElseThrow(() -> new ObjectNotFoundException("Objeto não encontrado! Id: " + id));
    }

    public List<Cliente> findAll() {
        return repository.findAll();
    }

    // Cria um novo cliente a partir de um DTO.

    public Cliente create(ClienteDTO dto) {
        dto.setId(null);
        validaPorCpfEEmail(dto);
        dto.setSenha(encoder.encode(dto.getSenha()));
        Cliente newObj = new Cliente(dto);
        return repository.save(newObj);
    }

    public Cliente update(Integer id, ClienteDTO dto) {
        dto.setId(id);
        Cliente oldObj = findById(id);
        validaPorCpfEEmail(dto);

        if (dto.getSenha() != null && !dto.getSenha().isEmpty()) {
            dto.setSenha(encoder.encode(dto.getSenha()));
        } else {
            dto.setSenha(oldObj.getSenha());
        }

        Cliente newObj = new Cliente(dto);
        return repository.save(newObj);
    }

    // Deleta um cliente pelo ID, verificando se possui chamados associados.
    public void delete(Integer id) {
        Cliente obj = findById(id);
        if (!obj.getChamados().isEmpty()) {
            throw new DataIntegrityViolationException("Cliente possui chamados e não pode ser deletado!");
        }
        repository.deleteById(id);
    }

    // Valida se o CPF e o e-mail já estão cadastrados no sistema.

    private void validaPorCpfEEmail(ClienteDTO dto) {
        Optional<Pessoa> obj = repository.findByCpf(dto.getCpf());
        if (obj.isPresent() && obj.get().getId() != dto.getId()) {
            throw new DataIntegrityViolationException("CPF já cadastrado no sistema!");
        }

        obj = tecnicoRepository.findByCpf(dto.getCpf());
        if (obj.isPresent() && obj.get().getId() != dto.getId()) {
            throw new DataIntegrityViolationException("CPF já cadastrado no sistema para um técnico!");
        }

        obj = repository.findByEmail(dto.getEmail());
        if (obj.isPresent() && obj.get().getId() != dto.getId()) {
            throw new DataIntegrityViolationException("E-mail já cadastrado no sistema!");
        }

        obj = tecnicoRepository.findByEmail(dto.getEmail());
        if (obj.isPresent() && obj.get().getId() != dto.getId()) {
            throw new DataIntegrityViolationException("E-mail já cadastrado no sistema para um técnico!");
        }
    }
}
