package pe.edu.upc.center.platform.test.interfaces.rest.transform;

import pe.edu.upc.center.platform.test.domain.model.commands.CreateQuestionCommand;
import pe.edu.upc.center.platform.test.interfaces.rest.resources.CreateQuestionResource;

public class CreateQuestionCommandFromResourceAssembler {
    public static CreateQuestionCommand toCommandFromResource(CreateQuestionResource resource) {
        return new CreateQuestionCommand(resource.pregunta(),
                resource.opcionA(),
                resource.opcionB(),
                resource.opcionC());
    }
}
