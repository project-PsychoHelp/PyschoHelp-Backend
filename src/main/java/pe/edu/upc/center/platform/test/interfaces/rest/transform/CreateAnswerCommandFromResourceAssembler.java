package pe.edu.upc.center.platform.test.interfaces.rest.transform;

import pe.edu.upc.center.platform.test.domain.model.commands.CreateAnswerCommand;
import pe.edu.upc.center.platform.test.interfaces.rest.resources.CreateAnswerResource;

public class CreateAnswerCommandFromResourceAssembler {
    public static CreateAnswerCommand toCommandFromResource(CreateAnswerResource resource){
        return new CreateAnswerCommand(
                resource.correctAnswer(),
                resource.explication(),
                resource.points());
    }
}
