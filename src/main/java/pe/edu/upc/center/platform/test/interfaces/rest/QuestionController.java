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
import pe.edu.upc.center.platform.test.domain.model.commands.DeleteQuestionCommand;
import pe.edu.upc.center.platform.test.domain.model.queries.*;
import pe.edu.upc.center.platform.test.domain.model.queries.GetAllQuestionsQuery;
import pe.edu.upc.center.platform.test.domain.model.queries.GetQuestionByIdQuery;
import pe.edu.upc.center.platform.test.domain.model.valueobjects.ExamId;
import pe.edu.upc.center.platform.test.domain.services.QuestionCommandService;
import pe.edu.upc.center.platform.test.domain.services.QuestionQueryService;
import pe.edu.upc.center.platform.test.interfaces.rest.resources.CreateQuestionResource;
import pe.edu.upc.center.platform.test.interfaces.rest.resources.QuestionResource;
import pe.edu.upc.center.platform.test.interfaces.rest.resources.QuestionResource;
import pe.edu.upc.center.platform.test.interfaces.rest.transform.CreateQuestionCommandFromResourceAssembler;
import pe.edu.upc.center.platform.test.interfaces.rest.transform.QuestionResourceFromEntityAssembler;
import pe.edu.upc.center.platform.test.interfaces.rest.transform.QuestionResourceFromEntityAssembler;
import pe.edu.upc.center.platform.test.interfaces.rest.transform.UpdateQuestionCommandFromResourceAssembler;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping(value = "/api/v1/exams/{examId}/questions", produces = MediaType.APPLICATION_JSON_VALUE)
@Tag(name = "Questions", description = "Questions Management Endpoints")

public class QuestionController {
    private final QuestionQueryService questionQueryService;
    private final QuestionCommandService questionCommandService;

    public QuestionController(QuestionQueryService questionQueryService, QuestionCommandService questionCommandService) {
        this.questionQueryService = questionQueryService;
        this.questionCommandService = questionCommandService;
    }

    @Operation(
            summary = "Create a new question",
            description = "Creates a new question associated with a specific examID."
    )
    @ApiResponses({
            @ApiResponse(responseCode = "201", description = "question created successfully",
                    content = @Content(schema = @Schema(implementation = QuestionResource.class))),
            @ApiResponse(responseCode = "400", description = "Invalid question ID or request body"),
            @ApiResponse(responseCode = "404", description = "Question not found")
    })
    @PostMapping
    public ResponseEntity<QuestionResource> createQuestion(
            @Parameter(description = "ID of the  to associate the question with", required = true)
            @PathVariable Long examId,
            @Parameter(description = "Details of the question to be created", required = true)
            @Valid @RequestBody CreateQuestionResource resource) {
        var createQuestionCommand = CreateQuestionCommandFromResourceAssembler.toCommandFromResource(resource);

        var questionId = this.questionCommandService.handle(examId, createQuestionCommand);

        if (examId.equals(0L)) return ResponseEntity.badRequest().build();


        var getQuestionByIdQuery = new GetQuestionByIdQuery(questionId);
        var optionalQuestion = this.questionQueryService.handle(getQuestionByIdQuery);

        if (optionalQuestion.isEmpty()) return ResponseEntity.notFound().build();

        var questionResource = QuestionResourceFromEntityAssembler.toResourceFromEntity(optionalQuestion.get());

        return new ResponseEntity<>(questionResource, HttpStatus.CREATED);
    }


    @GetMapping
    public ResponseEntity<List<QuestionResource>> getAllQuestions() {
        var getAllQuestionsQuery = new GetAllQuestionsQuery();
        var questions = this.questionQueryService.handle(getAllQuestionsQuery);
        var questionResources = questions.stream()
                .map(QuestionResourceFromEntityAssembler::toResourceFromEntity)
                .collect(Collectors.toList());
        return ResponseEntity.ok(questionResources);
    }

    @GetMapping("/{questionId}")
    public ResponseEntity<QuestionResource> getQuestionById(@PathVariable Long questionId) {
        var getQuestionByIdQuery = new GetQuestionByIdQuery(questionId);
        var optionalQuestion = this.questionQueryService.handle(getQuestionByIdQuery);

        if (optionalQuestion.isEmpty())
            return ResponseEntity.badRequest().build();
        var questionResource = QuestionResourceFromEntityAssembler.toResourceFromEntity(optionalQuestion.get());
        return ResponseEntity.ok(questionResource);
    }

    @PutMapping("/{questionId}")
    public ResponseEntity<QuestionResource> updateQuestion(@PathVariable Long questionId, @RequestBody QuestionResource resource) {
        var updateQuestionCommand = UpdateQuestionCommandFromResourceAssembler.toCommandFromResource(questionId, resource);
        var optionalQuestion = this.questionCommandService.handle(updateQuestionCommand);

        if (optionalQuestion.isEmpty())
            return ResponseEntity.badRequest().build();
        var questionResource = QuestionResourceFromEntityAssembler.toResourceFromEntity(optionalQuestion.get());
        return ResponseEntity.ok(questionResource);
    }

    @DeleteMapping("/{questionId}")
    public ResponseEntity<?> deleteQuestion(@PathVariable Long questionId) {
        var deleteQuestionCommand = new DeleteQuestionCommand(questionId);
        this.questionCommandService.handle(deleteQuestionCommand);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/search")
    public ResponseEntity<List<QuestionResource>> getQuestionsByExamId(@RequestParam(required = false) Long examId) {
        if (examId == null) return ResponseEntity.badRequest().build();
        var examIdObj = new ExamId(examId);
        var getAllQuestionsByExamIdQuery = new GetAllQuestionsByExamIdQuery(examIdObj.examId());
        var questions = this.questionQueryService.handle(getAllQuestionsByExamIdQuery);
        var questionResources = questions.stream()
                .map(QuestionResourceFromEntityAssembler::toResourceFromEntity)
                .collect(Collectors.toList());
        return ResponseEntity.ok(questionResources);
    }


}
