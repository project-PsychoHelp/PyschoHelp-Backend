package pe.edu.upc.center.platform.test.infrastructure.config;


import jakarta.annotation.PostConstruct;
import org.springframework.stereotype.Component;
import pe.edu.upc.center.platform.test.domain.model.commands.SeedCategoriesExamCommand;
import pe.edu.upc.center.platform.test.domain.services.CategoryExamCommandService;

@Component
public class CategoryExamSeeder {
    private final CategoryExamCommandService categoryExamCommandService;
    public CategoryExamSeeder(CategoryExamCommandService categoryExamCommandService) {
        this.categoryExamCommandService = categoryExamCommandService;
    }

    @PostConstruct
    public  void runSeed() {
        categoryExamCommandService.handle(new SeedCategoriesExamCommand());
    }

}
