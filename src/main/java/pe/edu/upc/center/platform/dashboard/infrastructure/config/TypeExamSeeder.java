package pe.edu.upc.center.platform.dashboard.infrastructure.config;

import jakarta.annotation.PostConstruct;
import org.springframework.stereotype.Component;
import pe.edu.upc.center.platform.dashboard.domain.model.commands.SeedTypesExamCommand;
import pe.edu.upc.center.platform.dashboard.domain.services.TypeExamCommandService;

@Component
public class TypeExamSeeder {

    private final TypeExamCommandService typeExamCommandService;
    public TypeExamSeeder(TypeExamCommandService typeExamCommandService) {
        this.typeExamCommandService = typeExamCommandService;
    }

    @PostConstruct
    public void runSeed(){typeExamCommandService.handle(new SeedTypesExamCommand());}
}
