package dev.Zerphyis.botFinanceiro.model.repositorys;

import dev.Zerphyis.botFinanceiro.model.usuarios.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UsuarioRepository extends JpaRepository<Usuario,Long> {
}
