package ntnu.oahjellj.calculator.backend.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import ntnu.oahjellj.calculator.backend.model.Calculation;
import ntnu.oahjellj.calculator.backend.model.User;

import java.util.Optional;
import java.util.Set;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByUsername(String username);
    Boolean existsByUsername(String username);
    Boolean existsByEmail(String email);
    Optional<Set<Calculation>> getHistoryByUsername(String username);
}