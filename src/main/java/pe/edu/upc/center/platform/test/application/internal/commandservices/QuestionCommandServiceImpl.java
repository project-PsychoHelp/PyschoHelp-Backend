package pe.edu.upc.center.platform.test.application.internal.commandservices;


import org.springframework.stereotype.Service;
import pe.edu.upc.center.platform.test.domain.model.aggregates.Exam;
import pe.edu.upc.center.platform.test.domain.model.aggregates.Question;
import pe.edu.upc.center.platform.test.domain.model.commands.*;
import pe.edu.upc.center.platform.test.domain.model.valueobjects.CategoriesExam;
import pe.edu.upc.center.platform.test.domain.model.valueobjects.ExamId;
import pe.edu.upc.center.platform.test.domain.services.QuestionCommandService;
import pe.edu.upc.center.platform.test.infrastructure.persistence.jpa.repositories.QuestionRepository;

import java.util.Optional;

@Service
public class QuestionCommandServiceImpl implements QuestionCommandService {

    private final QuestionRepository questionRepository;

    public QuestionCommandServiceImpl(QuestionRepository questionRepository) {
        this.questionRepository = questionRepository;
    }


    @Override
    public Long handle(Long examIdParam, CreateQuestionCommand command) {
        var examId = new ExamId(examIdParam);

        var question = new Question(
                command.pregunta(),
                command.opcionA(),
                command.opcionB(),
                command.opcionC(),
                examId);
        // Save the disease and return its ID
        return questionRepository.save(question).getId();
    }

    @Override
    public Optional<Question> handle(UpdateQuestionCommand command) {
        var questionId = command.questionId();

        // If the exam does not exist, throw an exception
        if (!this.questionRepository.existsById(questionId)) {
            throw new IllegalArgumentException("Exam with id " + questionId + " does not exist");
        }

        var questionToUpdate = this.questionRepository.findById(questionId).get();
        questionToUpdate.updateInformation(command.pregunta(), command.opcionA(), command.opcionB(), command.opcionC(), command.examId());

        try {
            var updatedQuestion = this.questionRepository.save(questionToUpdate);
            return Optional.of(updatedQuestion);
        } catch (Exception e) {
            throw new IllegalArgumentException("Error while updating Question: " + e.getMessage());
        }
    }

    @Override
    public void handle(DeleteQuestionCommand command) {
        // If the Exam does not exist, throw an exception
        if (!this.questionRepository.existsById(command.questionId())) {
            throw new IllegalArgumentException("Exam with id " + command.questionId() + " does not exist");
        }

        // Try to delete the Exam, if an error occurs, throw an exception
        try {
            this.questionRepository.deleteById(command.questionId());
        } catch (Exception e) {
            throw new IllegalArgumentException("Error while deleting Exam: " + e.getMessage());
        }
    }

}
