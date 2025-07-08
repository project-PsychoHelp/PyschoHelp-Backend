package pe.edu.upc.center.platform.test.infrastructure.config;

import jakarta.annotation.PostConstruct;
import org.springframework.stereotype.Component;
import pe.edu.upc.center.platform.test.domain.model.commands.SeedCorrectsAnswerCommand;
import pe.edu.upc.center.platform.test.domain.services.CorrectAnswerCommandService;

@Component
public class CorrectAnswerSeeder {
    private final CorrectAnswerCommandService correctAnswerCommandService;
    public CorrectAnswerSeeder(CorrectAnswerCommandService correctAnswerCommandService) {
        this.correctAnswerCommandService = correctAnswerCommandService;
    }

    @PostConstruct
    public  void runSeed() {
        correctAnswerCommandService.handle(new SeedCorrectsAnswerCommand());
    }
}
