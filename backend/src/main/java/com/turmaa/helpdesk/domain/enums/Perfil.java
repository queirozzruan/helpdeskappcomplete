package com.turmaa.helpdesk.domain.enums;

/**
 * Enumeração que representa os perfis de usuário do sistema HelpDesk.
 * Define os diferentes tipos de acesso e permissões disponíveis no sistema.
 *
 */
public enum Perfil {

    /**
     * Perfil de administrador do sistema.
     * Possui acesso total a todas as funcionalidades.
     */
    ADMIN(0, "ROLE_ADMIN"),

    /**
     * Perfil de cliente do sistema.
     * Usuário que abre chamados e solicita suporte.
     */
    CLIENTE(1, "ROLE_CLIENTE"),

    /**
     * Perfil de técnico do sistema.
     * Usuário responsável por atender e resolver chamados.
     */
    TECNICO(2, "ROLE_TECNICO");

    /**
     * Construtor do enum Perfil.
     *
     * @param codigo Código numérico que identifica o perfil
     * @param descricao Descrição textual do perfil (role)
     */
    Perfil(int codigo, String descricao) {
        this.codigo = codigo;
        this.descricao = descricao;
    }

    private Integer codigo;
    private String descricao;

    /**
     * Retorna o código numérico do perfil.
     *
     * @return Integer representando o código do perfil
     */
    public Integer getCodigo() {
        return codigo;
    }

    /**
     * Retorna a descrição do perfil no formato de role.
     *
     * @return String contendo a role do perfil (ex: "ROLE_ADMIN")
     */
    public String getDescricao() {
        return descricao;
    }

    /**
     * Converte um código numérico em seu respectivo enum Perfil.
     *
     * @param perfil Código numérico do perfil a ser convertido
     * @return Instância do enum Perfil correspondente ao código
     * @throws IllegalArgumentException Se o código não corresponder a nenhum perfil válido
     */
    public static Perfil toEnum(Integer perfil) {
        if (perfil == null) {
            return null;
        }
        for (Perfil x : Perfil.values()) {
            if(perfil.equals(x.getCodigo())) {
                return x;
            }
        }
        throw new IllegalArgumentException("Perfil Inválido " + perfil);
    }
}