package dev.Zerphyis.botFinanceiro.model.gastos;

import java.time.LocalDateTime;

public record RequestGastos(String nome, String categoria, Double valor, LocalDateTime dataHora, Long usuarioId) {
}
