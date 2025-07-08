package pe.edu.upc.center.platform.test.infrastructure.persistence.jpa.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import pe.edu.upc.center.platform.test.domain.model.aggregates.Exam;

public interface ExamRepository extends JpaRepository<Exam, Long> {
    // Aquí puedes agregar métodos personalizados si es necesario
}
