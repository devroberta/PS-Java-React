package br.com.banco.service.exception;

public class APIException extends RuntimeException {
    private static final long serialVersionUID = 1L;

    public APIException(String msg) {
        super(msg);
    }
}
