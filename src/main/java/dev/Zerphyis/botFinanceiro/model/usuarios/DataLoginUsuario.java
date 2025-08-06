package dev.Zerphyis.botFinanceiro.model.usuarios;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record DataLoginUsuario(@NotBlank String nome,
                               @NotNull Double mediaSalarial) {
}
