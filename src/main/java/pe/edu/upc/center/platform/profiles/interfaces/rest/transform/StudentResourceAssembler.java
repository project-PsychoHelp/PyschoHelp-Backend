package pe.edu.upc.center.platform.profiles.interfaces.rest.transform;

import pe.edu.upc.center.platform.profiles.domain.model.aggregates.Student;
import pe.edu.upc.center.platform.profiles.interfaces.rest.resources.StudentResource;

public class StudentResourceAssembler {
    public static StudentResource toResourceFromEntity(Student student) {
        return new StudentResource(
                student.getId(),
                student.getFirstName(),
                student.getLastName(),
                student.getFullName(),
                student.getEmail(),
                student.getPhone(),
                student.getAge(),
                student.getStatus(),
                student.getRegistrationDate(),
                student.getUserType()
        );
    }
}