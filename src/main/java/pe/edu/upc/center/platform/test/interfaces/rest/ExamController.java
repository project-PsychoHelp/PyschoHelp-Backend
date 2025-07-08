package pe.edu.upc.center.platform.test.interfaces.rest;

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
import pe.edu.upc.center.platform.test.domain.model.commands.DeleteExamCommand;
import pe.edu.upc.center.platform.test.domain.model.queries.GetAllExamsQuery;
import pe.edu.upc.center.platform.test.domain.model.queries.GetExamByIdQuery;
import pe.edu.upc.center.platform.test.domain.services.ExamCommandService;
import pe.edu.upc.center.platform.test.domain.services.ExamQueryService;
import pe.edu.upc.center.platform.test.interfaces.rest.resources.CreateExamResource;
import pe.edu.upc.center.platform.test.interfaces.rest.resources.ExamResource;
import pe.edu.upc.center.platform.test.interfaces.rest.transform.CreateExamCommandFromResourceAssembler;
import pe.edu.upc.center.platform.test.interfaces.rest.transform.ExamResourceFromEntityAssembler;
import pe.edu.upc.center.platform.test.interfaces.rest.transform.UpdateExamCommandFromResourceAssembler;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping(value = "/api/v1/exams", produces = MediaType.APPLICATION_JSON_VALUE)
@Tag(name = "Exams", description = "Exam Management Endpoints")
public class ExamController {

    private final ExamCommandService examCommandService;
    private final ExamQueryService examQueryService;

    public ExamController(ExamCommandService examCommandService, ExamQueryService examQueryService) {
        this.examCommandService = examCommandService;
        this.examQueryService = examQueryService;
    }

    @Operation(
            summary = "Create a new exam",
            description = "Creates a new exam."
    )
    @ApiResponses({
            @ApiResponse(responseCode = "201", description = "Disease created successfully",
                    content = @Content(schema = @Schema(implementation = ExamResource.class))),
            @ApiResponse(responseCode = "400", description = "Invalid diagnosis ID or request body"),
            @ApiResponse(responseCode = "404", description = "Diagnosis not found")
    })
    @PostMapping
    public ResponseEntity<ExamResource> createExam(
            @Parameter(description = "Details of the exam to be created", required = true)
            @Valid @RequestBody CreateExamResource resource) {
        var createExamCommand = CreateExamCommandFromResourceAssembler.toCommandFromResource(resource);

        var examId = this.examCommandService.handle(createExamCommand);

        if(examId.equals(0L)) return ResponseEntity.badRequest().build();

        var getExamByIdQuery = new GetExamByIdQuery(examId);
        var optionalExam = this.examQueryService.handle(getExamByIdQuery);

        if(optionalExam.isEmpty()) return ResponseEntity.notFound().build();

        var examResource = ExamResourceFromEntityAssembler.toResourceFromEntity(optionalExam.get());

        return new ResponseEntity<>(examResource, HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<ExamResource>> getAllExams() {
        var getAllExamsQuery = new GetAllExamsQuery();
        var exams = this.examQueryService.handle(getAllExamsQuery);
        var examResources = exams.stream()
                .map(ExamResourceFromEntityAssembler::toResourceFromEntity)
                .collect(Collectors.toList());
        return ResponseEntity.ok(examResources);
    }

    @GetMapping("/{examId}")
    public ResponseEntity<ExamResource> getExamById(@PathVariable Long examId) {
        var getExamByIdQuery = new GetExamByIdQuery(examId);
        var optionalExam = this.examQueryService.handle(getExamByIdQuery);
        if (optionalExam.isEmpty())
            return ResponseEntity.badRequest().build();
        var examResource = ExamResourceFromEntityAssembler.toResourceFromEntity(optionalExam.get());
        return ResponseEntity.ok(examResource);
    }

    @PutMapping("/{examId}")
    public ResponseEntity<ExamResource> updateExam(@PathVariable Long examId, @RequestBody ExamResource resource) {
        var updateExamCommand = UpdateExamCommandFromResourceAssembler.toCommandFromResource(examId, resource);
        var optionalExam = this.examCommandService.handle(updateExamCommand);

        if (optionalExam.isEmpty())
            return ResponseEntity.badRequest().build();
        var examResource = ExamResourceFromEntityAssembler.toResourceFromEntity(optionalExam.get());
        return ResponseEntity.ok(examResource);
    }

    @DeleteMapping("/{examId}")
    public ResponseEntity<?> deleteExam(@PathVariable Long examId) {
        var deleteExamCommand = new DeleteExamCommand(examId);
        this.examCommandService.handle(deleteExamCommand);
        return ResponseEntity.noContent().build();
    }
}