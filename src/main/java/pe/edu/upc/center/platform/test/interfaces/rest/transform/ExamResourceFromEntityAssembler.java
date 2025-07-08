package pe.edu.upc.center.platform.test.interfaces.rest.transform;

import pe.edu.upc.center.platform.test.domain.model.aggregates.Exam;
import pe.edu.upc.center.platform.test.interfaces.rest.resources.ExamResource;

public class ExamResourceFromEntityAssembler {

public static ExamResource toResourceFromEntity(Exam exam) {
    return new ExamResource(
        exam.getId(),
        exam.getDescription(),
        exam.getCategoryExam().getName().name(),
        exam.getRating(),
        exam.getNumberQuestions()
    );
  }
}
