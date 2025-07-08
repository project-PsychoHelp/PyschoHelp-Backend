package pe.edu.upc.center.platform.dashboard.domain.services;

import pe.edu.upc.center.platform.dashboard.domain.model.commands.CreateRepositoryExamCommand;

public interface RepositoryExamCommandService {

    Long handle(Long examId, CreateRepositoryExamCommand command);

}
