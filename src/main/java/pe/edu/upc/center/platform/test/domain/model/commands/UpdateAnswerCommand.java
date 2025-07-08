package pe.edu.upc.center.platform.test.domain.model.commands;

public record UpdateAnswerCommand (Long answerId, String correctAnswer,
                                   String explication, int points,
                                   Long questionId){
}
