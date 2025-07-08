package pe.edu.upc.center.platform.test.application.internal.commandservices;


import org.springframework.stereotype.Service;
import pe.edu.upc.center.platform.test.domain.model.aggregates.Answer;

import pe.edu.upc.center.platform.test.domain.model.commands.*;

import pe.edu.upc.center.platform.test.domain.model.valueobjects.CorrectsAnswer;
import pe.edu.upc.center.platform.test.domain.model.valueobjects.QuestionId;
import pe.edu.upc.center.platform.test.domain.services.AnswerCommandService;
import pe.edu.upc.center.platform.test.infrastructure.persistence.jpa.repositories.AnswerRepository;
import pe.edu.upc.center.platform.test.infrastructure.persistence.jpa.repositories.CorrectAnswerRepository;

import java.util.Optional;

@Service
public class AnswerCommandServiceImpl implements AnswerCommandService {

    private final AnswerRepository answerRepository;
    private final CorrectAnswerRepository correctAnswerRepository;

    public AnswerCommandServiceImpl(AnswerRepository answerRepository, CorrectAnswerRepository correctAnswerRepository) {
        this.answerRepository = answerRepository;
        this.correctAnswerRepository = correctAnswerRepository;
    }

    @Override
    public Long handle(Long questionIdParam, CreateAnswerCommand command){
        var questionId = new QuestionId(questionIdParam);
        CorrectsAnswer correctAnswer = CorrectsAnswer.fromName(command.correctAnswer());

        var correctAnswerEntity = correctAnswerRepository.findByName(correctAnswer).orElseThrow(() ->
                new IllegalArgumentException("Answer not found: " + correctAnswer));

        var answer = new Answer(
                correctAnswerEntity,
                command.explication(),
                command.points(),
                questionId);

        return answerRepository.save(answer).getId();
    }


    @Override
    public Optional<Answer> handle(UpdateAnswerCommand command) {
        var answerId = command.answerId();

        // If the exam does not exist, throw an exception
        if (!this.answerRepository.existsById(answerId)) {
            throw new IllegalArgumentException("Exam with id " + answerId + " does not exist");
        }

        CorrectsAnswer correctAnswer = CorrectsAnswer.fromName(command.correctAnswer());
        var correctAnswerEntity = correctAnswerRepository.findByName(correctAnswer).orElseThrow(() ->
                new IllegalArgumentException("Category not found: " + correctAnswer));
        //

        var answerToUpdate = this.answerRepository.findById(answerId).get();
        answerToUpdate.updateInformation(correctAnswerEntity, command.explication(), command.points(), command.questionId());

        try {
            var updatedAnswer = this.answerRepository.save(answerToUpdate);
            return Optional.of(updatedAnswer);
        } catch (Exception e) {
            throw new IllegalArgumentException("Error while updating Answer: " + e.getMessage());
        }
    }

    @Override
    public void handle(DeleteAnswerCommand command) {
        // If the Exam does not exist, throw an exception
        if (!this.answerRepository.existsById(command.answerId())) {
            throw new IllegalArgumentException("Exam with id " + command.answerId() + " does not exist");
        }

        // Try to delete the Exam, if an error occurs, throw an exception
        try {
            this.answerRepository.deleteById(command.answerId());
        } catch (Exception e) {
            throw new IllegalArgumentException("Error while deleting Exam: " + e.getMessage());
        }
    }

}
