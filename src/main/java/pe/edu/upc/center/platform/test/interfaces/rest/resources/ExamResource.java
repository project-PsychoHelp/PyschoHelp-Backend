package pe.edu.upc.center.platform.test.interfaces.rest.resources;

public record ExamResource(
    Long id,
    String description,
    String categoryExam,
    int rating,
    int numberQuestions
) {
}
