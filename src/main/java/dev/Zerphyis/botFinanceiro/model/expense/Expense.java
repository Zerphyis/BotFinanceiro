package dev.Zerphyis.botFinanceiro.model.expense;

import dev.Zerphyis.botFinanceiro.model.user.User;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDate;

@Table(name = "despesa")
@Getter
@Setter
@NoArgsConstructor
@Entity
public class Expense {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @Column(name = "quantidade")
    private BigDecimal amount;

    @NotBlank
    @Column(name = "categoria")
    private String category;

    @NotNull
    @Column(name = "dia")
    private LocalDate date;

    @ManyToOne
    private User user;

    public Expense(RequestExpense data){
        this.amount=data.amount();
        this.category=data.category();
        this.date=data.date();
    }
}
