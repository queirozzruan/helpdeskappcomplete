package com.turmaa.helpdesk.domain.dtos;

/**
 * DTO (Data Transfer Object) para receber as credenciais de autenticação.
 * Usado no processo de login para transferir email e senha do cliente para o servidor.
 *
 */
public class CredenciaisDTO {

    private String email;
    private String senha;

    /**
     * Construtor padrão.
     */
    public CredenciaisDTO() {
    }

    /**
     * Construtor com parâmetros.
     *
     * @param email email do usuário
     * @param senha senha do usuário
     */
    public CredenciaisDTO(String email, String senha) {
        this.email = email;
        this.senha = senha;
    }

    /**
     * Retorna o email do usuário.
     *
     * @return String contendo o email
     */
    public String getEmail() {
        return email;
    }

    /**
     * Define o email do usuário.
     *
     * @param email email do usuário
     */
    public void setEmail(String email) {
        this.email = email;
    }

    /**
     * Retorna a senha do usuário.
     *
     * @return String contendo a senha
     */
    public String getSenha() {
        return senha;
    }

    /**
     * Define a senha do usuário.
     *
     * @param senha senha do usuário
     */
    public void setSenha(String senha) {
        this.senha = senha;
    }
}