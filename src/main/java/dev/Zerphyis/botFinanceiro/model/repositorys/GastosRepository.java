package dev.Zerphyis.botFinanceiro.model.repositorys;

import dev.Zerphyis.botFinanceiro.model.gastos.Gastos;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface GastosRepository extends JpaRepository<Gastos,Long> {
    List<Gastos> findByUsuarioId(Long usuarioId);
}
