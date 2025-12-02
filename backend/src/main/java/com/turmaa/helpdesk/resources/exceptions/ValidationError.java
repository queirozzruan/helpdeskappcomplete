package com.turmaa.helpdesk.resources.exceptions;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import com.turmaa.helpdesk.service.exceptions.FieldMessage;
import com.turmaa.helpdesk.service.exceptions.StandardError;

/**
 * Classe que representa erros de validação de dados em requisições.
 *
 * <p>Extende {@link StandardError} para incluir uma lista de mensagens de erro
 * específicas de campos que falharam na validação.</p>
 *
 * <p>Utilizada para fornecer feedback detalhado sobre erros de validação
 * em APIs REST.</p>
 */

public class ValidationError extends StandardError implements Serializable {

    /**
     *
     */
    private static final long serialVersionUID = 1L;

    private List<FieldMessage> errors = new ArrayList<>();

    public ValidationError() {
        super();
    }

    public ValidationError(Long timestamp, Integer status, String error, String message, String path) {
        super(timestamp, status, error, message, path);
    }

    public List<FieldMessage> getErrors() {
        return errors;
    }

    public void addErrors(String fieldName, String message) {
        this.errors.add(new FieldMessage(fieldName, message));
    }

}