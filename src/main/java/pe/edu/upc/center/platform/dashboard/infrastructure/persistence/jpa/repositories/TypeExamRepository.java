package pe.edu.upc.center.platform.dashboard.infrastructure.persistence.jpa.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import pe.edu.upc.center.platform.dashboard.domain.model.entities.TypeExam;
import pe.edu.upc.center.platform.dashboard.domain.model.valueobjects.TypesExam;

import java.util.Optional;

public interface TypeExamRepository extends JpaRepository<TypeExam, Long> {

    boolean existsByName(TypesExam name);
    Optional<TypeExam> findByName(TypesExam name);
}
