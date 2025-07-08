package pe.edu.upc.center.platform.test.application.internal.commandservices;


import org.springframework.stereotype.Service;
import pe.edu.upc.center.platform.test.domain.model.commands.SeedCategoriesExamCommand;
import pe.edu.upc.center.platform.test.domain.model.entities.CategoryExam;
import pe.edu.upc.center.platform.test.domain.model.valueobjects.CategoriesExam;
import pe.edu.upc.center.platform.test.domain.services.CategoryExamCommandService;
import pe.edu.upc.center.platform.test.infrastructure.persistence.jpa.repositories.CategoryExamRepository;

import java.util.Arrays;

@Service
public class CategoryExamCommandServiceImpl implements CategoryExamCommandService {

    private final CategoryExamRepository categoryExamRepository;

    public CategoryExamCommandServiceImpl(CategoryExamRepository categoryExamRepository) {
        this.categoryExamRepository = categoryExamRepository;
    }

    @Override
    public void handle(SeedCategoriesExamCommand command) {
        Arrays.stream(CategoriesExam.values()).forEach(categoryExam -> {
            if (!categoryExamRepository.existsByName(categoryExam)) {
                categoryExamRepository.save(new CategoryExam(categoryExam));
            }
        });
    }


}
