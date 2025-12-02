package com.turmaa.helpdesk.domain.dtos;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.turmaa.helpdesk.domain.Cliente;
import com.turmaa.helpdesk.domain.enums.Perfil;

import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * Data Transfer Object (DTO) para a entidade Cliente.
 *
 * <p>Usado para transferir dados de clientes entre camadas da aplicação,
 * especialmente na comunicação via API REST.</p>
 *
 * <p>Implementa {@link Serializable} para permitir a conversão do objeto
 * em uma sequência de bytes, facilitando o transporte e armazenamento.</p>
 */

public class ClienteDTO implements Serializable {
    private static final long serialVersionUID = 1L;

    protected Integer id;

    @NotNull(message = "O campo NOME é ncessário")
    protected String nome;

    @NotNull(message = "O campo CPF é necessário")
    protected String cpf;

    @NotNull(message = "O campo EMAIL é necessário")
    protected String email;

    @NotNull(message = "O campo SENHA é requerido")
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY) // A senha só pode ser escrita, nunca lida.
    protected String senha;

    protected Set<Integer> perfis = new HashSet<>();

    @JsonFormat(pattern = "dd/MM/yyyy")
    protected LocalDate dataCriacao = LocalDate.now();

    /**
     * Construtor padrão.
     * Adiciona o perfil de CLIENTE por padrão a qualquer nova instância.
     */
    public ClienteDTO() {
        super();
        addPerfil(Perfil.CLIENTE);
    }

    /**
     * Construtor que converte uma entidade Cliente para um DTO.
     * @param obj A entidade Cliente a ser convertida.
     */
    public ClienteDTO(Cliente obj) {
        super();
        this.id = obj.getId();
        this.nome = obj.getNome();
        this.cpf = obj.getCpf();
        this.email = obj.getEmail();
        this.senha = obj.getSenha();
        this.perfis = obj.getPerfis().stream().map(x -> x.getCodigo()).collect(Collectors.toSet());
        this.dataCriacao = obj.getDataCriacao();
    }

    // Getters e Setters


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public Set<Perfil> getPerfis() {
        return perfis.stream().map(x -> Perfil.toEnum(x)).collect(Collectors.toSet());
    }

    public void addPerfil(Perfil perfil) {
        this.perfis.add(perfil.getCodigo());
    }

    public LocalDate getDataCriacao() {
        return dataCriacao;
    }

    public void setDataCriacao(LocalDate dataCriacao) {
        this.dataCriacao = dataCriacao;
    }
}