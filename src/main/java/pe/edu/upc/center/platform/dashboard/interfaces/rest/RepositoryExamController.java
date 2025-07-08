package pe.edu.upc.center.platform.dashboard.interfaces.rest;


import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pe.edu.upc.center.platform.dashboard.domain.model.queries.GetAllRepositoriesExamByExamIdQuery;
import pe.edu.upc.center.platform.dashboard.domain.model.queries.GetAllRepositoriesExamQuery;
import pe.edu.upc.center.platform.dashboard.domain.model.queries.GetRepositoryExamByIdQuery;
import pe.edu.upc.center.platform.dashboard.domain.model.valueobjects.ExamId;
import pe.edu.upc.center.platform.dashboard.domain.services.RepositoryExamCommandService;
import pe.edu.upc.center.platform.dashboard.domain.services.RepositoryExamQueryService;
import pe.edu.upc.center.platform.dashboard.interfaces.rest.resources.CreateRespositoryExamResource;
import pe.edu.upc.center.platform.dashboard.interfaces.rest.resources.RepositoryExamResource;
import pe.edu.upc.center.platform.dashboard.interfaces.rest.transform.CreateRepositoryExamCommandFromResourceAssembler;
import pe.edu.upc.center.platform.dashboard.interfaces.rest.transform.RepositoryExamResourceFromEntityAssembler;



import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping(value = "/api/v1/exams/{examId}/repositoriesExam", produces = MediaType.APPLICATION_JSON_VALUE)
@Tag(name = "Repositories Exam", description = "Repositories Exam Management Endpoints")
public class RepositoryExamController {

    private final RepositoryExamQueryService repositoryExamQueryService;
    private final RepositoryExamCommandService repositoryExamCommandService;
    public RepositoryExamController(RepositoryExamQueryService repositoryExamQueryService,
                                    RepositoryExamCommandService repositoryExamCommandService) {
        this.repositoryExamQueryService = repositoryExamQueryService;
        this.repositoryExamCommandService = repositoryExamCommandService;
    }


    @Operation(
            summary = "Create a new repository exam",
            description = "Creates a new repository exam associated with a specific examID."
    )
    @ApiResponses({
            @ApiResponse(responseCode = "201", description = "repository exam created successfully",
                    content = @Content(schema = @Schema(implementation = RepositoryExamResource.class))),
            @ApiResponse(responseCode = "400", description = "Invalid repository exam ID or request body"),
            @ApiResponse(responseCode = "404", description = "repository exam not found")
    })
    @PostMapping
    public ResponseEntity<RepositoryExamResource> createRepositoryExam(
            @Parameter(description = "ID of the  to associate the exam with", required = true)
            @PathVariable Long examId,
            @Parameter(description = "Details of the repositoryExam to be created", required = true)
            @Valid @RequestBody CreateRespositoryExamResource resource) {
        var createRepositoryExamCommand = CreateRepositoryExamCommandFromResourceAssembler.toCommandFromResource(resource);

        var repositoryExamId = this.repositoryExamCommandService.handle(examId, createRepositoryExamCommand);

        if (examId.equals(0L)) return ResponseEntity.badRequest().build();


        var getRepositoryExamByIdQuery = new GetRepositoryExamByIdQuery(repositoryExamId);
        var optionalRepositoryExam = this.repositoryExamQueryService.handle(getRepositoryExamByIdQuery);

        if (optionalRepositoryExam.isEmpty()) return ResponseEntity.notFound().build();

        var repositoryExamResource = RepositoryExamResourceFromEntityAssembler.toResourceFromEntity(optionalRepositoryExam.get());

        return new ResponseEntity<>(repositoryExamResource, HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<RepositoryExamResource>> getAllRepositoryExams() {
        var getAllRepositoryExamsQuery = new GetAllRepositoriesExamQuery();
        var repositoryExams = this.repositoryExamQueryService.handle(getAllRepositoryExamsQuery);
        var repositoryExamResources = repositoryExams.stream()
                .map(RepositoryExamResourceFromEntityAssembler::toResourceFromEntity)
                .collect(Collectors.toList());
        return ResponseEntity.ok(repositoryExamResources);
    }


    @GetMapping("/search")
    public ResponseEntity<List<RepositoryExamResource>> getRepositoryExamsByExamId(@RequestParam(required = false) Long examId) {
        if (examId == null) return ResponseEntity.badRequest().build();
        var examIdObj = new ExamId(examId);
        var getAllRepositoryExamsByExamIdQuery = new GetAllRepositoriesExamByExamIdQuery(examIdObj.examId());
        var repositoryExams = this.repositoryExamQueryService.handle(getAllRepositoryExamsByExamIdQuery);
        var repositoryExamResources = repositoryExams.stream()
                .map(RepositoryExamResourceFromEntityAssembler::toResourceFromEntity)
                .collect(Collectors.toList());
        return ResponseEntity.ok(repositoryExamResources);
    }


}
