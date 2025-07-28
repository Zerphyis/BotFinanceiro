package dev.Zerphyis.botFinanceiro.application.chat;

public record ChatResponse(String reply, byte[] chartImage) {
    public ChatResponse(String reply) {
        this(reply, null);
    }
}
