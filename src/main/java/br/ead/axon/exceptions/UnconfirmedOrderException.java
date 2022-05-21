package br.ead.axon.exceptions;

public class UnconfirmedOrderException extends RuntimeException {

    public UnconfirmedOrderException() {
    }

    public UnconfirmedOrderException(String message) {
        super(message);
    }

    public UnconfirmedOrderException(String message, Throwable cause) {
        super(message, cause);
    }
}
