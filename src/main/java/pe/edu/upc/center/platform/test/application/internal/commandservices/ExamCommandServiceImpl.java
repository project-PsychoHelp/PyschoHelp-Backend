package pe.edu.upc.center.platform.test.application.internal.commandservices;


import org.springframework.stereotype.Service;
import pe.edu.upc.center.platform.test.domain.model.aggregates.Exam;
import pe.edu.upc.center.platform.test.domain.model.commands.CreateExamCommand;
import pe.edu.upc.center.platform.test.domain.model.commands.DeleteExamCommand;
import pe.edu.upc.center.platform.test.domain.model.commands.UpdateExamCommand;
import pe.edu.upc.center.platform.test.domain.model.valueobjects.CategoriesExam;
import pe.edu.upc.center.platform.test.domain.services.ExamCommandService;
import pe.edu.upc.center.platform.test.infrastructure.persistence.jpa.repositories.CategoryExamRepository;
import pe.edu.upc.center.platform.test.infrastructure.persistence.jpa.repositories.ExamRepository;

import java.util.Optional;

@Service
public class ExamCommandServiceImpl implements ExamCommandService {

    private final ExamRepository examRepository;
    private final CategoryExamRepository categoryExamRepository;

    public ExamCommandServiceImpl(ExamRepository examRepository, CategoryExamRepository categoryExamRepository) {
        this.examRepository = examRepository;
        this.categoryExamRepository = categoryExamRepository;
    }

    @Override
    public Long handle(CreateExamCommand command) {

        CategoriesExam categoryExam = CategoriesExam.fromName(command.categoryExam());

        var categoryExamEntity = categoryExamRepository.findByName(categoryExam).orElseThrow(() ->
                new IllegalArgumentException("Category not found: " + categoryExam));


        var exam = new Exam(
                command.description(),
                categoryExamEntity,
                command.rating(),
                command.numberQuestions()
        );
        // Save the disease and return its ID
        return examRepository.save(exam).getId();
    }

    @Override
    public Optional<Exam> handle(UpdateExamCommand command) {
        var examId = command.examId();

        // If the exam does not exist, throw an exception
        if (!this.examRepository.existsById(examId)) {
            throw new IllegalArgumentException("Exam with id " + examId + " does not exist");
        }


        // If the category does not exist, throw an exception
        CategoriesExam categoryExam = CategoriesExam.fromName(command.categoryExam());
        var categoryExamEntity = categoryExamRepository.findByName(categoryExam).orElseThrow(() ->
                new IllegalArgumentException("Category not found: " + categoryExam));
        //


        var examToUpdate = this.examRepository.findById(examId).get();
        examToUpdate.updateInformation(command.description(), categoryExamEntity, command.rating(), command.numberQuestion());

        try {
            var updatedExam = this.examRepository.save(examToUpdate);
            return Optional.of(updatedExam);
        } catch (Exception e) {
            throw new IllegalArgumentException("Error while updating Exam: " + e.getMessage());
        }
    }

    @Override
    public void handle(DeleteExamCommand command) {
        // If the Exam does not exist, throw an exception
        if (!this.examRepository.existsById(command.examId())) {
            throw new IllegalArgumentException("Exam with id " + command.examId() + " does not exist");
        }

        // Try to delete the Exam, if an error occurs, throw an exception
        try {
            this.examRepository.deleteById(command.examId());
        } catch (Exception e) {
            throw new IllegalArgumentException("Error while deleting Exam: " + e.getMessage());
        }
    }

}
