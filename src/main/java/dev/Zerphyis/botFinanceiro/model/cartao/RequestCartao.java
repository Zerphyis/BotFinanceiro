package dev.Zerphyis.botFinanceiro.model.cartao;

import java.time.LocalDate;

public record RequestCartao(String nomeCartao, Double limite, String portadorAtual, LocalDate dataInicio, LocalDate dataFim, Long usuarioId) {
}
