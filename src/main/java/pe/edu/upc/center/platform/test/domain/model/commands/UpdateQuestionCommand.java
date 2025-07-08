package pe.edu.upc.center.platform.test.domain.model.commands;

public record UpdateQuestionCommand(Long questionId, String pregunta, String opcionA, String opcionB, String opcionC, Long examId) {}
