package pe.edu.upc.center.platform.dashboard.application.internal.commandservices;

import org.springframework.stereotype.Service;
import pe.edu.upc.center.platform.dashboard.domain.model.commands.SeedTypesExamCommand;
import pe.edu.upc.center.platform.dashboard.domain.model.entities.TypeExam;
import pe.edu.upc.center.platform.dashboard.domain.model.valueobjects.TypesExam;
import pe.edu.upc.center.platform.dashboard.domain.services.TypeExamCommandService;
import pe.edu.upc.center.platform.dashboard.infrastructure.persistence.jpa.repositories.TypeExamRepository;
import pe.edu.upc.center.platform.test.domain.model.commands.SeedCategoriesExamCommand;
import pe.edu.upc.center.platform.test.domain.model.entities.CategoryExam;
import pe.edu.upc.center.platform.test.domain.model.valueobjects.CategoriesExam;

import java.util.Arrays;
@Service
public class TypeExamCommandServiceImpl implements TypeExamCommandService {
    private final TypeExamRepository typeExamRepository;

    public TypeExamCommandServiceImpl(TypeExamRepository typeExamRepository) {
        this.typeExamRepository = typeExamRepository;
    }

    @Override
    public void handle(SeedTypesExamCommand command) {
        Arrays.stream(TypesExam.values()).forEach(typeExam -> {
            if (!typeExamRepository.existsByName(typeExam)) {
                typeExamRepository.save(new TypeExam(typeExam));
            }
        });
    }
}
