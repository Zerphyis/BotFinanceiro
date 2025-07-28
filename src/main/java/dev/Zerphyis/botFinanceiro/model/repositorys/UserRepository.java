package dev.Zerphyis.botFinanceiro.model.repositorys;

import dev.Zerphyis.botFinanceiro.model.user.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User,Long> {
}
