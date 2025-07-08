package pe.edu.upc.center.platform.test.interfaces.rest.transform;

import pe.edu.upc.center.platform.test.domain.model.aggregates.Answer;
import pe.edu.upc.center.platform.test.interfaces.rest.resources.AnswerResource;

public class AnswerResourceFromEntityAssembler {

    public static AnswerResource toResourceFromEntity(Answer answer) {
        return new AnswerResource(
            answer.getId(),
            answer.getCorrectAnswer().getName().name(),
            answer.getExplication(),
            answer.getPoints(),
            answer.getQuestionId().questionId()
        );
    }
}
