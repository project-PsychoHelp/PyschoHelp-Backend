package pe.edu.upc.center.platform.student.domain.model.commands;

import pe.edu.upc.center.platform.student.domain.model.valueobjects.StudentCode;

public record DeleteStudentCommand(StudentCode studentCode) {
}
