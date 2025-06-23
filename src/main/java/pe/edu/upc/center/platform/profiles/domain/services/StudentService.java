package pe.edu.upc.center.platform.profiles.domain.services;

import pe.edu.upc.center.platform.profiles.domain.model.aggregates.Student;
import pe.edu.upc.center.platform.profiles.domain.model.commands.CreateStudentCommand;

import java.util.List;
import java.util.Optional;

public interface StudentService {
    Optional<Student> createStudent(CreateStudentCommand command);
    //Optional<Student> updateStudentProgress(UpdateStudentProgressCommand command);
    List<Student> getAllStudents();
    Optional<Student> getStudentById(Long id);
}