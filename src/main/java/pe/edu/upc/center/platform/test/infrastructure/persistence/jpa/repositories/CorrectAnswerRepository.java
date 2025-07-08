package pe.edu.upc.center.platform.test.infrastructure.persistence.jpa.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import pe.edu.upc.center.platform.test.domain.model.entities.CorrectAnswer;
import pe.edu.upc.center.platform.test.domain.model.valueobjects.CorrectsAnswer;

import java.util.Optional;

public interface CorrectAnswerRepository extends JpaRepository<CorrectAnswer, Long> {

    boolean existsByName(CorrectsAnswer name);
    Optional<CorrectAnswer> findByName(CorrectsAnswer name);
}
