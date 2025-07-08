package pe.edu.upc.center.platform.student.domain.services;

import pe.edu.upc.center.platform.student.domain.model.aggregates.Student;
import pe.edu.upc.center.platform.student.domain.model.queries.GetAllStudentsQuery;
import pe.edu.upc.center.platform.student.domain.model.queries.GetStudentByIdQuery;
import pe.edu.upc.center.platform.student.domain.model.queries.GetStudentByProfileIdQuery;
import pe.edu.upc.center.platform.student.domain.model.queries.GetStudentByStudentCodeQuery;

import java.util.List;
import java.util.Optional;

public interface StudentQueryService {
  List<Student> handle(GetAllStudentsQuery query);
  Optional<Student> handle(GetStudentByIdQuery query);
  Optional<Student> handle(GetStudentByStudentCodeQuery query);
  Optional<Student> handle(GetStudentByProfileIdQuery query);
}
