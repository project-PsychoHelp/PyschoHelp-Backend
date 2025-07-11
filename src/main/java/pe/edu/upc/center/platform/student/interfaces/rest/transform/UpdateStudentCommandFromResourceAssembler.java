package pe.edu.upc.center.platform.student.interfaces.rest.transform;

import pe.edu.upc.center.platform.student.domain.model.commands.UpdateStudentCommand;
import pe.edu.upc.center.platform.student.domain.model.valueobjects.StudentCode;
import pe.edu.upc.center.platform.student.interfaces.rest.resources.StudentResource;

public class UpdateStudentCommandFromResourceAssembler {
  public static UpdateStudentCommand toCommandFromResource(String studentCode, StudentResource resource) {
    return new UpdateStudentCommand(new StudentCode(studentCode), resource.name(), resource.age(), resource.address());
  }
}
