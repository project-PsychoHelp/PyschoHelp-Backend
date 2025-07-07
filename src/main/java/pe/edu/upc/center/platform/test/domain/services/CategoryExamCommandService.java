package pe.edu.upc.center.platform.test.domain.services;

import pe.edu.upc.center.platform.test.domain.model.commands.SeedCategoriesExamCommand;

public interface CategoryExamCommandService {
    void handle(SeedCategoriesExamCommand command);
}
