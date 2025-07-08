package pe.edu.upc.center.platform.student.interfaces.rest.transform;

import pe.edu.upc.center.platform.student.domain.model.commands.CreateStudentCommand;
import pe.edu.upc.center.platform.student.interfaces.rest.resources.CreateStudentResource;

public class CreateStudentCommandFromResourceAssembler {
  public static CreateStudentCommand toCommandFromResource(CreateStudentResource resource) {
    return new CreateStudentCommand(resource.name(), resource.age(), resource.address(),
        resource.programId(), resource.startPeriod());
  }
}
