package com.turmaa.helpdesk.domain.dtos;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.turmaa.helpdesk.domain.Chamado;

import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.time.LocalDate;

/**
 * Data Transfer Object (DTO) para a entidade Chamado.
 *
 * <p>Usado para transferir dados de chamados entre camadas da aplicação,
 * especialmente na comunicação via API REST.</p>
 *
 * <p>Implementa {@link Serializable} para permitir a conversão do objeto
 * em uma sequência de bytes, facilitando o transporte e armazenamento.</p>
 */

public class ChamadoDTO implements Serializable {
    private static final long serialVersionUID = 1L;

    private Integer id;


    @JsonFormat(pattern = "dd/MM/yyyy")
    private LocalDate dataAbertura = LocalDate.now();

    @JsonFormat(pattern = "dd/MM/yyyy")
    private LocalDate dataFechamento;

    @NotNull(message = "O campo PRIORIDADE é necessário")
    private Integer prioridade;

    @NotNull(message = "O campo STATUS é necessário")
    private Integer status;

    @NotNull(message = "O campo TÍTULO é necessário")
    private String titulo;

    @NotNull(message = "O campo OBSERVAÇÕES é necessário")
    private String observacoes;


    @NotNull(message = "O campo TÉCNICO é necessário")
    private Integer tecnico;

    @NotNull(message = "O campo CLIENTE é necessário")
    private Integer cliente;

    private String nomeTecnico;
    private String nomeCliente;

    public ChamadoDTO() {
        super();
    }

    /**
     * Construtor que converte uma entidade Chamado em um DTO.
     * É usado para formatar a resposta da API.
     * @param obj A entidade Chamado a ser convertida.
     */
    public ChamadoDTO(Chamado obj) {
        this.id = obj.getId();
        this.dataAbertura = obj.getDataAbertura();
        this.dataFechamento = obj.getDataFechamento();
        this.prioridade = obj.getPrioridade().getCodigo();
        this.status = obj.getStatus().getCodigo();
        this.titulo = obj.getTitulo();
        this.observacoes = obj.getObservacoes();
        this.tecnico = obj.getTecnico().getId();
        this.cliente = obj.getCliente().getId();
        this.nomeTecnico = obj.getTecnico().getNome();
        this.nomeCliente = obj.getCliente().getNome();
    }

    // GETTERS E SETTERS


    public Integer getId() { return id; }
    public void setId(Integer id) { this.id = id; }
    public LocalDate getDataAbertura() { return dataAbertura; }
    public void setDataAbertura(LocalDate dataAbertura) { this.dataAbertura = dataAbertura; }
    public LocalDate getDataFechamento() { return dataFechamento; }
    public void setDataFechamento(LocalDate dataFechamento) { this.dataFechamento = dataFechamento; }
    public Integer getPrioridade() { return prioridade; }
    public void setPrioridade(Integer prioridade) { this.prioridade = prioridade; }
    public Integer getStatus() { return status; }
    public void setStatus(Integer status) { this.status = status; }
    public String getTitulo() { return titulo; }
    public void setTitulo(String titulo) { this.titulo = titulo; }
    public String getObservacoes() { return observacoes; }
    public void setObservacoes(String observacoes) { this.observacoes = observacoes; }
    public Integer getTecnico() { return tecnico; }
    public void setTecnico(Integer tecnico) { this.tecnico = tecnico; }
    public Integer getCliente() { return cliente; }
    public void setCliente(Integer cliente) { this.cliente = cliente; }
    public String getNomeTecnico() { return nomeTecnico; }
    public void setNomeTecnico(String nomeTecnico) { this.nomeTecnico = nomeTecnico; }
    public String getNomeCliente() { return nomeCliente; }
    public void setNomeCliente(String nomeCliente) { this.nomeCliente = nomeCliente; }
}
