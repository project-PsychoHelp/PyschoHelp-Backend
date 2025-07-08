package pe.edu.upc.center.platform.dashboard.interfaces.rest.resources;

public record RepositoryExamResource(
        Long id,
        String typeExam,
        String description,
        Long examId
) { }
