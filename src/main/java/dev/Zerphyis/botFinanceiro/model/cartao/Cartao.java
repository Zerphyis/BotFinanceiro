package dev.Zerphyis.botFinanceiro.model.cartao;

import dev.Zerphyis.botFinanceiro.model.usuarios.Usuario;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.time.LocalDate;

@Entity
@Table(name = "Cartao")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Cartao {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @NotBlank
    private String nomeCartao;
    @NotNull
    private Double limite;
    @NotBlank
    private String portadorAtual;
    private LocalDate dataInicio;
    private LocalDate dataFim;

    @ManyToOne
    private Usuario usuario;
}
