package pe.edu.upc.center.platform.test.domain.model.commands;

public record CreateExamCommand(String description, String categoryExam, int rating, int numberQuestions) {
}
