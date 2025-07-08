package pe.edu.upc.center.platform.dashboard.interfaces.rest.transform;

import pe.edu.upc.center.platform.dashboard.domain.model.commands.CreateRepositoryExamCommand;
import pe.edu.upc.center.platform.dashboard.interfaces.rest.resources.CreateRespositoryExamResource;

public class CreateRepositoryExamCommandFromResourceAssembler {

    public static CreateRepositoryExamCommand toCommandFromResource(CreateRespositoryExamResource resource){
        return new CreateRepositoryExamCommand(
                resource.typeExam(),
                resource.description());
    }

}
