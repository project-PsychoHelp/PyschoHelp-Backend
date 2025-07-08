package pe.edu.upc.center.platform.dashboard.domain.services;

import pe.edu.upc.center.platform.dashboard.domain.model.commands.SeedTypesExamCommand;

public interface TypeExamCommandService {
    void handle(SeedTypesExamCommand command);
}
