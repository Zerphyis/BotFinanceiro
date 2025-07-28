package dev.Zerphyis.botFinanceiro.model.expense;

import java.math.BigDecimal;
import java.time.LocalDate;

public record RequestExpense(BigDecimal amount , String category, LocalDate date) {
}
