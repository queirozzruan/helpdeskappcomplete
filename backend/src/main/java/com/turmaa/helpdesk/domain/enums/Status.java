package com.turmaa.helpdesk.domain.enums;

/**
 * Enumeração que representa os status possíveis de um chamado no sistema HelpDesk.
 * Define o estágio atual de atendimento e resolução de um chamado.
 *
 */
public enum Status {

    /**
     * Status aberto.
     * Chamado recém-criado, aguardando início do atendimento pela equipe de suporte.
     */
    ABERTO(0, "ROLE_ABERTO"),

    /**
     * Status em andamento.
     * Chamado sendo analisado e atendido por um técnico, em processo de resolução.
     */
    ANDAMENTO(1, "ROLE_ANDAMENTO"),

    /**
     * Status encerrado.
     * Chamado finalizado com a solução implementada e validada.
     */
    ENCERRADO(2, "ROLE_ENCERRADO");

    /**
     * Construtor do enum Status.
     *
     * @param codigo Código numérico que identifica o status do chamado
     * @param descricao Descrição textual do status (role)
     */
    Status(int codigo, String descricao) {
        this.codigo = codigo;
        this.descricao = descricao;
    }

    private Integer codigo;
    private String descricao;

    /**
     * Retorna o código numérico do status.
     *
     * @return Integer representando o código do status
     */
    public Integer getCodigo() {
        return codigo;
    }

    /**
     * Retorna a descrição do status no formato de role.
     *
     * @return String contendo a role do status (ex: "ROLE_ABERTO")
     */
    public String getDescricao() {
        return descricao;
    }

    /**
     * Converte um código numérico em seu respectivo enum Status.
     *
     * @param codigo Código numérico do status a ser convertido
     * @return Instância do enum Status correspondente ao código, ou null se o código for null
     * @throws IllegalArgumentException Se o código não corresponder a nenhum status válido
     */
    public static Status toEnum(Integer codigo) {
        if (codigo == null) {
            return null;
        }
        for (Status x : Status.values()) {
            if(codigo.equals(x.getCodigo())) {
                return x;
            }
        }
        throw new IllegalArgumentException("Status Inválido");
    }
}