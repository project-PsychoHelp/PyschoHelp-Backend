package pe.edu.upc.center.platform.profiles.application.internal.queryservices;

import org.springframework.stereotype.Service;
import pe.edu.upc.center.platform.profiles.domain.model.aggregates.Student;
import pe.edu.upc.center.platform.profiles.domain.model.queries.GetAllStudentsQuery;
import pe.edu.upc.center.platform.profiles.domain.services.StudentService;

import java.util.List;

@Service
public class GetAllStudentsQueryService {
    private final StudentService studentService;

    public GetAllStudentsQueryService(StudentService studentService) {
        this.studentService = studentService;
    }

    public List<Student> handle(GetAllStudentsQuery query) {
        return studentService.getAllStudents();
    }
}