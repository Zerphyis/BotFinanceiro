package dev.Zerphyis.botFinanceiro.model.cartao;

import java.time.LocalDate;

public record ResponseCartao(
                                String nomeCartao,
                                 Double limite,
                                 String portadorAtual,
                                 LocalDate dataInicio,
                                 LocalDate dataFim,
                                 String nomeUsuario) {
}
