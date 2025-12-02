package com.turmaa.helpdesk.domain;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.OneToMany;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.turmaa.helpdesk.domain.dtos.TecnicoDTO;
import com.turmaa.helpdesk.domain.enums.Perfil;

/**
 * A classe Tecnico representa um usuário com permissões de técnico no sistema.
 * Herda os atributos básicos da classe Pessoa.
 */
@Entity
public class Tecnico extends Pessoa {

    private static final long serialVersionUID = 1L;

    @OneToMany(mappedBy = "tecnico")
    @JsonIgnore
    private List<Chamado> chamados = new ArrayList<>();

    public Tecnico() {
        super();
        addPerfil(Perfil.TECNICO);
    }

    /**
     * Construtor com parâmetros.
     * Adiciona o perfil de TECNICO ao novo objeto.
     */
    public Tecnico(Integer id, String nome, String cpf, String email, String senha) {
        super(id, nome, cpf, email, senha);
        addPerfil(Perfil.TECNICO);
    }

    public Tecnico(TecnicoDTO obj) {
        super();
        this.id = obj.getId();
        this.nome = obj.getNome();
        this.cpf = obj.getCpf();
        this.email = obj.getEmail();
        this.senha = obj.getSenha();
        this.perfis = obj.getPerfis(); // o DTO já converte os perfis para o tipo correto
        this.dataCriacao = obj.getDataCriacao();
        addPerfil(Perfil.TECNICO); // Garante que o perfil de técnico seja sempre adicionado
    }

    public List<Chamado> getChamados() {
        return chamados;
    }

    public void setChamados(List<Chamado> chamados) {
        this.chamados = chamados;
    }
}