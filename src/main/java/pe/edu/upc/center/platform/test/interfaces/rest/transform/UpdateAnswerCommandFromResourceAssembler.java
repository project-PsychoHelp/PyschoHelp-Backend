package pe.edu.upc.center.platform.test.interfaces.rest.transform;

import pe.edu.upc.center.platform.test.domain.model.commands.UpdateAnswerCommand;
import pe.edu.upc.center.platform.test.interfaces.rest.resources.AnswerResource;

public class UpdateAnswerCommandFromResourceAssembler {
    public static UpdateAnswerCommand toCommandFromResource(Long answerId, AnswerResource resource){
        return new UpdateAnswerCommand(
                answerId,
                resource.correctAnswer(),
                resource.explication(),
                resource.points(),
                resource.questionId()
        );
    }
}
