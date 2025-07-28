package dev.Zerphyis.botFinanceiro.model.repositorys;

import dev.Zerphyis.botFinanceiro.model.expense.Expense;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface ExpenseRepository  extends JpaRepository<Expense,Long> {
    List<Expense> findByUserId(Long userId);
    List<Expense> findByUserIdAndDateBetween(Long userId, LocalDate start, LocalDate end);
}
