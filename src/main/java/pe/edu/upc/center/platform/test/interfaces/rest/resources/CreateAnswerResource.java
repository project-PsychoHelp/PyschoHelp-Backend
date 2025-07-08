package pe.edu.upc.center.platform.test.interfaces.rest.resources;

public record CreateAnswerResource(
        String correctAnswer,
        String explication,
        int points) { }
