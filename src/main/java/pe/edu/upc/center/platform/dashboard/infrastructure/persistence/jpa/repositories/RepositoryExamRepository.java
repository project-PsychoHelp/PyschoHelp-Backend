package pe.edu.upc.center.platform.dashboard.infrastructure.persistence.jpa.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import pe.edu.upc.center.platform.dashboard.domain.model.aggregates.RepositoryExam;
import pe.edu.upc.center.platform.dashboard.domain.model.valueobjects.ExamId;


import java.util.List;

public interface RepositoryExamRepository extends JpaRepository<RepositoryExam, Long> {

    List<RepositoryExam> findByExamId(ExamId examId);
}
