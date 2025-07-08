package pe.edu.upc.center.platform.test.interfaces.rest.resources;

public record CreateExamResource(
    String description,
    String categoryExam,
    int rating,
    int numberQuestion
) {
}
