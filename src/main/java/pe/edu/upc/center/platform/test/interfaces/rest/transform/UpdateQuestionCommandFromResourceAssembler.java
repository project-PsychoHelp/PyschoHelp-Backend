package pe.edu.upc.center.platform.test.interfaces.rest.transform;

import pe.edu.upc.center.platform.test.domain.model.commands.UpdateQuestionCommand;
import pe.edu.upc.center.platform.test.interfaces.rest.resources.QuestionResource;

public class UpdateQuestionCommandFromResourceAssembler {
    public static UpdateQuestionCommand toCommandFromResource(Long questionId, QuestionResource resource) {
        return new UpdateQuestionCommand(
                questionId,
                resource.pregunta(),
                resource.opcionA(),
                resource.opcionB(),
                resource.opcionC(),
                resource.examId()
        );
    }
}
