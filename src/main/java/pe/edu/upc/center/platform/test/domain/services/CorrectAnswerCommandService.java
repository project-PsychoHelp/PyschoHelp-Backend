package pe.edu.upc.center.platform.test.domain.services;

import pe.edu.upc.center.platform.test.domain.model.commands.SeedCorrectsAnswerCommand;

public interface CorrectAnswerCommandService {
    void handle(SeedCorrectsAnswerCommand command);
}
