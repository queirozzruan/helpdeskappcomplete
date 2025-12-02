package com.turmaa.helpdesk.service.exceptions;

/**
 * Exceção lançada quando ocorre uma violação de integridade de dados no sistema.
 * Geralmente utilizada para indicar que uma operação tentou inserir ou atualizar
 * dados que violam restrições de integridade do banco de dados, como chaves
 * primárias duplicadas ou restrições de chave estrangeira.
 */

public class DataIntegrityViolationException extends RuntimeException{

    private static final long serialVersionUID = 1L;

    public DataIntegrityViolationException(String message, Throwable cause) {
        super(message, cause);
    }

    public DataIntegrityViolationException(String message) {
        super(message);
    }



}