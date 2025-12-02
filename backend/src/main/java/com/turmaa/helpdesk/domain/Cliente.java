package com.turmaa.helpdesk.domain;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.OneToMany;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.turmaa.helpdesk.domain.dtos.ClienteDTO;
import com.turmaa.helpdesk.domain.enums.Perfil;

/**
 * Entidade Cliente que representa um cliente no sistema de helpdesk.
 * Herda de Pessoa e implementa Serializable para permitir a conversão do objeto em uma sequência de bytes.
 */

@Entity
public class Cliente extends Pessoa {

    private static final long serialVersionUID = 1L;

    // Um cliente pode ter muitos chamados associados a ele.
    // O mapeamento é feito pelo atributo "cliente" na classe Chamado.

    @OneToMany(mappedBy = "cliente")
    @JsonIgnore
    private List<Chamado> chamados = new ArrayList<>();

    public Cliente() {
        super();
        addPerfil(Perfil.CLIENTE);
    }

    // Construtor com os campos essenciais

    public Cliente(Integer id, String nome, String cpf, String email, String senha) {
        super(id, nome, cpf, email, senha);
        addPerfil(Perfil.CLIENTE);
    }

    // Construtor que converte um DTO em uma entidade Cliente.

    public Cliente(ClienteDTO obj) {
        super();
        this.id = obj.getId();
        this.nome = obj.getNome();
        this.cpf = obj.getCpf();
        this.email = obj.getEmail();
        this.senha = obj.getSenha();
        this.perfis = obj.getPerfis(); // O DTO já converte os perfis para o tipo correto
        this.dataCriacao = obj.getDataCriacao();
        addPerfil(Perfil.CLIENTE); // Garante que o perfil de cliente seja sempre adicionado
    }

    public List<Chamado> getChamados() {
        return chamados;
    }

    public void setChamados(List<Chamado> chamados) {
        this.chamados = chamados;
    }
}