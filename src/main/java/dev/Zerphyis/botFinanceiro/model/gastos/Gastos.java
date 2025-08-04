package dev.Zerphyis.botFinanceiro.model.gastos;

import dev.Zerphyis.botFinanceiro.model.usuarios.Usuario;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.time.LocalDateTime;


@Entity
@Table(name = "gastos")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Gastos {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @NotBlank
    private String nome;
    @NotBlank
    private String categoria;
    @NotNull
    private Double valor;
    private LocalDateTime dataHora;

    @ManyToOne
    private Usuario usuario;
}
