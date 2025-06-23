package pe.edu.upc.center.platform.profiles.application.internal.commandservices;

import org.springframework.stereotype.Service;
import pe.edu.upc.center.platform.profiles.domain.model.aggregates.Student;
import pe.edu.upc.center.platform.profiles.domain.model.commands.CreateStudentCommand;
import pe.edu.upc.center.platform.profiles.domain.services.StudentService;

import java.util.Optional;

@Service
public class CreateStudentCommandService {
    private final StudentService studentService;

    public CreateStudentCommandService(StudentService studentService) {
        this.studentService = studentService;
    }

    public Optional<Student> handle(CreateStudentCommand command) {
        return studentService.createStudent(command);
    }
}