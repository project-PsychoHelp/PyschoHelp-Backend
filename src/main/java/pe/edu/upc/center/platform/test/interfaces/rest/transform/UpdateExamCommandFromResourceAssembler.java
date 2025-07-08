package pe.edu.upc.center.platform.test.interfaces.rest.transform;

import pe.edu.upc.center.platform.test.domain.model.commands.UpdateExamCommand;
import pe.edu.upc.center.platform.test.interfaces.rest.resources.ExamResource;

public class UpdateExamCommandFromResourceAssembler {
    public static UpdateExamCommand toCommandFromResource(Long examId, ExamResource resource){

        return new UpdateExamCommand(
                examId,
                resource.description(),
                resource.categoryExam(),
                resource.rating(),
                resource.numberQuestions()
        );
    }
}
