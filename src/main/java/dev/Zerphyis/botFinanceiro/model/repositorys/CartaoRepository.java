package dev.Zerphyis.botFinanceiro.model.repositorys;

import dev.Zerphyis.botFinanceiro.model.cartao.Cartao;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface CartaoRepository extends JpaRepository<Cartao,Long> {
    List<Cartao> findByUsuarioId(Long usuarioId);
}
