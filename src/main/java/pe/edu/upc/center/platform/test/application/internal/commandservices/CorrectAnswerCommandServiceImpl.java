package pe.edu.upc.center.platform.test.application.internal.commandservices;

import org.springframework.stereotype.Service;
import pe.edu.upc.center.platform.test.domain.model.commands.SeedCorrectsAnswerCommand;
import pe.edu.upc.center.platform.test.domain.model.entities.CategoryExam;
import pe.edu.upc.center.platform.test.domain.model.entities.CorrectAnswer;
import pe.edu.upc.center.platform.test.domain.model.valueobjects.CategoriesExam;
import pe.edu.upc.center.platform.test.domain.model.valueobjects.CorrectsAnswer;
import pe.edu.upc.center.platform.test.domain.services.CorrectAnswerCommandService;
import pe.edu.upc.center.platform.test.infrastructure.persistence.jpa.repositories.CorrectAnswerRepository;

import java.util.Arrays;


@Service
public class CorrectAnswerCommandServiceImpl implements CorrectAnswerCommandService {

    private final CorrectAnswerRepository correctAnswerRepository;
    public CorrectAnswerCommandServiceImpl(CorrectAnswerRepository correctAnswerRepository) {
        this.correctAnswerRepository = correctAnswerRepository;
    }

    @Override
    public void handle(SeedCorrectsAnswerCommand command) {
        Arrays.stream(CorrectsAnswer.values()).forEach(correctAnswer -> {
            if (!correctAnswerRepository.existsByName(correctAnswer)) {
                correctAnswerRepository.save(new CorrectAnswer(correctAnswer));
            }
        });
    }
}
