package pe.edu.upc.center.platform.dashboard.domain.services;

import pe.edu.upc.center.platform.dashboard.domain.model.aggregates.RepositoryExam;
import pe.edu.upc.center.platform.dashboard.domain.model.queries.GetAllRepositoriesExamByExamIdQuery;
import pe.edu.upc.center.platform.dashboard.domain.model.queries.GetAllRepositoriesExamQuery;
import pe.edu.upc.center.platform.dashboard.domain.model.queries.GetRepositoryExamByIdQuery;

import java.util.List;
import java.util.Optional;

public interface RepositoryExamQueryService {
    List<RepositoryExam> handle(GetAllRepositoriesExamQuery query);
    Optional<RepositoryExam> handle(GetRepositoryExamByIdQuery query);
    List<RepositoryExam> handle(GetAllRepositoriesExamByExamIdQuery query);
}
