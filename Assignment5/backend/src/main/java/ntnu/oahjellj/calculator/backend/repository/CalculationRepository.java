package ntnu.oahjellj.calculator.backend.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import ntnu.oahjellj.calculator.backend.model.Calculation;

@Repository
public interface CalculationRepository extends JpaRepository<Calculation, Long> {
    
}
