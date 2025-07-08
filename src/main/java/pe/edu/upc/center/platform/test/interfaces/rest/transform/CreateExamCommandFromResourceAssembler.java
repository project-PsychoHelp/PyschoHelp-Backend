package pe.edu.upc.center.platform.test.interfaces.rest.transform;

import pe.edu.upc.center.platform.test.domain.model.commands.CreateExamCommand;
import pe.edu.upc.center.platform.test.interfaces.rest.resources.CreateExamResource;

public class CreateExamCommandFromResourceAssembler {
public static CreateExamCommand toCommandFromResource(CreateExamResource resource) {
        return new CreateExamCommand(
                resource.description(),
                resource.categoryExam(),
                resource.rating(),
                resource.numberQuestion()
        );
    }
}
