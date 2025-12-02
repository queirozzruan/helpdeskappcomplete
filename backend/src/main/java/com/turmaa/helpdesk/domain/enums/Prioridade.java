package com.turmaa.helpdesk.domain.enums;

/**
 * Enumeração que representa os níveis de prioridade para chamados do sistema HelpDesk.
 * Define a urgência com que um chamado deve ser atendido pela equipe de suporte.
 *
 */
public enum Prioridade {

    /**
     * Prioridade baixa.
     * Chamados que podem ser atendidos sem urgência, não impactam operações críticas.
     */
    BAIXA(0, "ROLE_BAIXA"),

    /**
     * Prioridade média.
     * Chamados que requerem atenção dentro de um prazo razoável, com impacto moderado.
     */
    MEDIA(1, "ROLE_MEDIA"),

    /**
     * Prioridade alta.
     * Chamados críticos que requerem atenção imediata, impactam operações essenciais.
     */
    ALTA(2, "ROLE_ALTA");

    /**
     * Construtor do enum Prioridade.
     *
     * @param codigo Código numérico que identifica o nível de prioridade
     * @param descricao Descrição textual da prioridade (role)
     */
    Prioridade(int codigo, String descricao) {
        this.codigo = codigo;
        this.descricao = descricao;
    }

    private Integer codigo;
    private String descricao;

    /**
     * Retorna o código numérico da prioridade.
     *
     * @return Integer representando o código da prioridade
     */
    public Integer getCodigo() {
        return codigo;
    }

    /**
     * Retorna a descrição da prioridade no formato de role.
     *
     * @return String contendo a role da prioridade (ex: "ROLE_ALTA")
     */
    public String getDescricao() {
        return descricao;
    }

    /**
     * Converte um código numérico em seu respectivo enum Prioridade.
     *
     * @param codigo Código numérico da prioridade a ser convertido
     * @return Instância do enum Prioridade correspondente ao código
     * @throws IllegalArgumentException Se o código não corresponder a nenhuma prioridade válida
     */
    public static Prioridade toEnum(Integer codigo) {
        if (codigo == null) {
            return null;
        }
        for (Prioridade x : Prioridade.values()) {
            if(codigo.equals(x.getCodigo())) {
                return x;
            }
        }
        throw new IllegalArgumentException("Prioridade Inválida: " + codigo);
    }
}