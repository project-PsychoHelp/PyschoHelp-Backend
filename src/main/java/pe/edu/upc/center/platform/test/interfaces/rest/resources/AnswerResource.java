package pe.edu.upc.center.platform.test.interfaces.rest.resources;

public record AnswerResource(
        Long id,
        String correctAnswer,
        String explication,
        int points,
        Long questionId
) {
}
