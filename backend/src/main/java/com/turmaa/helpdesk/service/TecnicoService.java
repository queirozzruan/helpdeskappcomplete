package com.turmaa.helpdesk.service;

import com.turmaa.helpdesk.domain.Pessoa;
import com.turmaa.helpdesk.domain.Tecnico;
import com.turmaa.helpdesk.domain.dtos.TecnicoDTO;
import com.turmaa.helpdesk.repositories.ClienteRepository;
import com.turmaa.helpdesk.repositories.TecnicoRepository;
import com.turmaa.helpdesk.service.exceptions.DataIntegrityViolationException;
import com.turmaa.helpdesk.service.exceptions.ObjectNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TecnicoService {

    @Autowired
    private TecnicoRepository repository;

    @Autowired
    private ClienteRepository clienteRepository;

    @Autowired
    private PasswordEncoder encoder;

    public Tecnico findById(Integer id) {
        Optional<Tecnico> obj = repository.findById(id);
        return obj.orElseThrow(() -> new ObjectNotFoundException("Objeto não encontrado! Id: " + id));
    }

    public List<Tecnico> findAll() {
        return repository.findAll();
    }

    public Tecnico create(TecnicoDTO dto) {
        dto.setId(null);
        validaPorCpfEEmail(dto);
        dto.setSenha(encoder.encode(dto.getSenha()));
        Tecnico newObj = new Tecnico(dto);
        return repository.save(newObj);
    }

    // Valida se o CPF e o e-mail já estão cadastrados no sistema.

    private void validaPorCpfEEmail(TecnicoDTO dto) {
        Optional<Pessoa> obj = repository.findByCpf(dto.getCpf());
        if (obj.isPresent() && obj.get().getId() != dto.getId()) {
            throw new DataIntegrityViolationException("CPF já cadastrado no sistema!");
        }

        obj = clienteRepository.findByCpf(dto.getCpf());
        if (obj.isPresent() && obj.get().getId() != dto.getId()) {
            throw new DataIntegrityViolationException("CPF já cadastrado no sistema para um cliente!");
        }

        obj = repository.findByEmail(dto.getEmail());
        if (obj.isPresent() && obj.get().getId() != dto.getId()) {
            throw new DataIntegrityViolationException("E-mail já cadastrado no sistema!");
        }

        obj = clienteRepository.findByEmail(dto.getEmail());
        if (obj.isPresent() && obj.get().getId() != dto.getId()) {
            throw new DataIntegrityViolationException("E-mail já cadastrado no sistema para um cliente!");
        }
    }

    // Atualiza um técnico existente com base no ID e nos dados do DTO.

    public Tecnico update(Integer id, TecnicoDTO dto) {
        Tecnico obj = findById(id);
        validaPorCpfEEmail(dto);

        if (dto.getSenha() != null && !dto.getSenha().isEmpty()) {
            dto.setSenha(encoder.encode(dto.getSenha()));
        } else {
            dto.setSenha(obj.getSenha());
        }

        obj = new Tecnico(dto);
        return repository.save(obj);
    }

    public void delete(Integer id) {
        Tecnico obj = findById(id);

        if (!obj.getChamados().isEmpty()) {
            throw new DataIntegrityViolationException("Técnico possui chamados e não pode ser deletado!");
        }

        repository.deleteById(id);
    }
}
