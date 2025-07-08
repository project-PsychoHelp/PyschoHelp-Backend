package pe.edu.upc.center.platform.test.interfaces.rest.transform;

import pe.edu.upc.center.platform.test.domain.model.aggregates.Question;
import pe.edu.upc.center.platform.test.interfaces.rest.resources.QuestionResource;

public class QuestionResourceFromEntityAssembler {
    public static QuestionResource toResourceFromEntity(Question question) {
        return new QuestionResource(
            question.getId(),
            question.getPregunta(),
            question.getOpcionA(),
            question.getOpcionB(),
            question.getOpcionC(),
            question.getExamId().examId()
        );
    }
}
