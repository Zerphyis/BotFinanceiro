package dev.Zerphyis.botFinanceiro.infra.exceptions;

public class TranscricaoException extends RuntimeException {
    public TranscricaoException(String message) {
        super(message);
    }

    public TranscricaoException(String message, Throwable cause) {
        super(message, cause);
    }
}
