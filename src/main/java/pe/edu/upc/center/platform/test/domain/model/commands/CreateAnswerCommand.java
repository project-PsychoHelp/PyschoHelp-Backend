package pe.edu.upc.center.platform.test.domain.model.commands;

public record CreateAnswerCommand(String correctAnswer, String explication, int points) {
}
