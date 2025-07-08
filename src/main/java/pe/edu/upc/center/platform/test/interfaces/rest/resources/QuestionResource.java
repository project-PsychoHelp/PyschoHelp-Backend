package pe.edu.upc.center.platform.test.interfaces.rest.resources;

public record QuestionResource (
    Long id,
    String pregunta,
    String opcionA,
    String opcionB,
    String opcionC,
    Long examId
) {}
