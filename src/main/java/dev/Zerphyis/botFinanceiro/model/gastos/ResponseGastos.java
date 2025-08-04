package dev.Zerphyis.botFinanceiro.model.gastos;

import java.time.LocalDateTime;

public record ResponseGastos(String nome,
                             String categoria,
                             Double valor,
                             LocalDateTime dataHora,
                             String  NomeUsuario) {
}
