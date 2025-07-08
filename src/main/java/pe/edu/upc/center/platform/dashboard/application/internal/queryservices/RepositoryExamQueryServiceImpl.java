package pe.edu.upc.center.platform.dashboard.application.internal.queryservices;

import org.springframework.stereotype.Service;
import pe.edu.upc.center.platform.dashboard.domain.model.aggregates.RepositoryExam;
import pe.edu.upc.center.platform.dashboard.domain.model.queries.GetAllRepositoriesExamByExamIdQuery;
import pe.edu.upc.center.platform.dashboard.domain.model.queries.GetAllRepositoriesExamQuery;
import pe.edu.upc.center.platform.dashboard.domain.model.queries.GetRepositoryExamByIdQuery;
import pe.edu.upc.center.platform.dashboard.domain.model.valueobjects.ExamId;
import pe.edu.upc.center.platform.dashboard.domain.services.RepositoryExamQueryService;
import pe.edu.upc.center.platform.dashboard.infrastructure.persistence.jpa.repositories.RepositoryExamRepository;

import java.util.List;
import java.util.Optional;

@Service
public class RepositoryExamQueryServiceImpl implements RepositoryExamQueryService {
    private final RepositoryExamRepository repositoryExamRepository;

    public RepositoryExamQueryServiceImpl(RepositoryExamRepository repositoryExamRepository) {
        this.repositoryExamRepository = repositoryExamRepository;
    }

    @Override
    public List<RepositoryExam> handle(GetAllRepositoriesExamQuery query) {
        return this.repositoryExamRepository.findAll();
    }

    @Override
    public Optional<RepositoryExam> handle(GetRepositoryExamByIdQuery query) {
        return this.repositoryExamRepository.findById(query.repositoryExamId());
    }

    @Override
    public List<RepositoryExam> handle(GetAllRepositoriesExamByExamIdQuery query){
        var examIdObj = new ExamId(query.examId());
        return this.repositoryExamRepository.findByExamId(examIdObj);
    }

}
