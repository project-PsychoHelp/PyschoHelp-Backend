package pe.edu.upc.center.platform.dashboard.application.internal.commandservices;


import org.springframework.stereotype.Service;
import pe.edu.upc.center.platform.dashboard.domain.model.aggregates.RepositoryExam;
import pe.edu.upc.center.platform.dashboard.domain.model.commands.CreateRepositoryExamCommand;
import pe.edu.upc.center.platform.dashboard.domain.model.valueobjects.ExamId;
import pe.edu.upc.center.platform.dashboard.domain.model.valueobjects.TypesExam;
import pe.edu.upc.center.platform.dashboard.domain.services.RepositoryExamCommandService;
import pe.edu.upc.center.platform.dashboard.infrastructure.persistence.jpa.repositories.RepositoryExamRepository;
import pe.edu.upc.center.platform.dashboard.infrastructure.persistence.jpa.repositories.TypeExamRepository;


import java.util.Optional;

@Service
public class RepositoryExamCommandServiceImpl implements RepositoryExamCommandService {

    private final RepositoryExamRepository repositoryExamRepository;
    private final TypeExamRepository typeExamRepository;

    public RepositoryExamCommandServiceImpl(RepositoryExamRepository repositoryExamRepository, TypeExamRepository typeExamRepository) {
        this.repositoryExamRepository = repositoryExamRepository;
        this.typeExamRepository = typeExamRepository;
    }

    @Override
    public Long handle(Long examIdParam, CreateRepositoryExamCommand command){
        var examId = new ExamId(examIdParam);
        TypesExam typeExam = TypesExam.fromName(command.typeExam());

        var typeExamEntity = typeExamRepository.findByName(typeExam).orElseThrow(() ->
                new IllegalArgumentException("RepositoryExam not found: " + typeExam));

        var repositoryExam = new RepositoryExam(
                typeExamEntity,
                command.description(),
                examId);

        return repositoryExamRepository.save(repositoryExam).getId();
    }

}
