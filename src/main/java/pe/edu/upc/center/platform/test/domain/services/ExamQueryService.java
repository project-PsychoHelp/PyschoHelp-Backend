package pe.edu.upc.center.platform.test.domain.services;

import pe.edu.upc.center.platform.test.domain.model.aggregates.Exam;
import pe.edu.upc.center.platform.test.domain.model.queries.GetAllExamsQuery;
import pe.edu.upc.center.platform.test.domain.model.queries.GetExamByIdQuery;

import java.util.List;
import java.util.Optional;

public interface ExamQueryService {
    List<Exam> hangle(GetAllExamsQuery query);
    Optional<Exam> handle(GetExamByIdQuery query);

}
