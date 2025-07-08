package pe.edu.upc.center.platform.dashboard.interfaces.rest.transform;

import pe.edu.upc.center.platform.dashboard.domain.model.aggregates.RepositoryExam;
import pe.edu.upc.center.platform.dashboard.interfaces.rest.resources.RepositoryExamResource;

public class RepositoryExamResourceFromEntityAssembler {

    public static RepositoryExamResource toResourceFromEntity(RepositoryExam repositoryExam){

        return new RepositoryExamResource(
                repositoryExam.getId(),
                repositoryExam.getTypeExam().getName().name(),
                repositoryExam.getDescription(),
                repositoryExam.getExamId().examId()
        );
    }
}
