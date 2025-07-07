package pe.edu.upc.center.platform.test.domain.model.commands;

public record UpdateExamCommand (Long examId,
                                 String description,
                                 String categoryExam,
                                 int rating,
                                 int numberQuestion)
{}
