package dev.Zerphyis.botFinanceiro.infra.exceptions;

public class SegurancaException extends RuntimeException {

    public SegurancaException() {
        super();
    }

    public SegurancaException(String message) {
        super(message);
    }

    public SegurancaException(String message, Throwable cause) {
        super(message, cause);
    }

    public SegurancaException(Throwable cause) {
        super(cause);
    }
}
