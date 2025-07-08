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

import pe.edu.upc.center.platform.test.domain.model.commands.DeleteAnswerCommand;
import pe.edu.upc.center.platform.test.domain.model.commands.DeleteQuestionCommand;
import pe.edu.upc.center.platform.test.domain.model.queries.*;

import pe.edu.upc.center.platform.test.domain.model.valueobjects.ExamId;
import pe.edu.upc.center.platform.test.domain.model.valueobjects.QuestionId;
import pe.edu.upc.center.platform.test.domain.services.AnswerCommandService;
import pe.edu.upc.center.platform.test.domain.services.AnswerQueryService;
import pe.edu.upc.center.platform.test.interfaces.rest.resources.AnswerResource;
import pe.edu.upc.center.platform.test.interfaces.rest.resources.CreateAnswerResource;
import pe.edu.upc.center.platform.test.interfaces.rest.resources.QuestionResource;
import pe.edu.upc.center.platform.test.interfaces.rest.transform.*;


import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping(value = "/api/v1/questions/{questionId}/answers", produces = MediaType.APPLICATION_JSON_VALUE)
@Tag(name = "Answers", description = "Answers Management Endpoints")
public class AnswerController {

    private final AnswerQueryService answerQueryService;
    private final AnswerCommandService answerCommandService;
    public AnswerController(AnswerQueryService answerQueryService, AnswerCommandService answerCommandService) {
        this.answerQueryService = answerQueryService;
        this.answerCommandService = answerCommandService;
    }

    @Operation(
            summary = "Create a new answer",
            description = "Creates a new answer associated with a specific examID."
    )
    @ApiResponses({
            @ApiResponse(responseCode = "201", description = "answer created successfully",
                    content = @Content(schema = @Schema(implementation = AnswerResource.class))),
            @ApiResponse(responseCode = "400", description = "Invalid answer ID or request body"),
            @ApiResponse(responseCode = "404", description = "Answer not found")
    })
    @PostMapping
    public ResponseEntity<AnswerResource> createAnswer(
            @Parameter(description = "ID of the  to associate the answer with", required = true)
            @PathVariable Long questionId,
            @Parameter(description = "Details of the answer to be created", required = true)
            @Valid @RequestBody CreateAnswerResource resource) {
        var createAnswerCommand = CreateAnswerCommandFromResourceAssembler.toCommandFromResource(resource);

        var answerId = this.answerCommandService.handle(questionId, createAnswerCommand);

        if (questionId.equals(0L)) return ResponseEntity.badRequest().build();


        var getAnswerByIdQuery = new GetAnswerByIdQuery(answerId);
        var optionalAnswer = this.answerQueryService.handle(getAnswerByIdQuery);

        if (optionalAnswer.isEmpty()) return ResponseEntity.notFound().build();

        var answerResource = AnswerResourceFromEntityAssembler.toResourceFromEntity(optionalAnswer.get());

        return new ResponseEntity<>(answerResource, HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<AnswerResource>> getAllAnswers() {
        var getAllAnswersQuery = new GetAllAnswersQuery();
        var answers = this.answerQueryService.handle(getAllAnswersQuery);
        var answerResources = answers.stream()
                .map(AnswerResourceFromEntityAssembler::toResourceFromEntity)
                .collect(Collectors.toList());
        return ResponseEntity.ok(answerResources);
    }

    @GetMapping("/{answerId}")
    public ResponseEntity<AnswerResource> getAnswerById(@PathVariable Long answerId) {
        var getAnswerByIdQuery = new GetAnswerByIdQuery(answerId);
        var optionalAnswer = this.answerQueryService.handle(getAnswerByIdQuery);

        if (optionalAnswer.isEmpty())
            return ResponseEntity.badRequest().build();
        var answerResource = AnswerResourceFromEntityAssembler.toResourceFromEntity(optionalAnswer.get());
        return ResponseEntity.ok(answerResource);
    }


    @PutMapping("/{answerId}")
    public ResponseEntity<AnswerResource> updateAnswer(@PathVariable Long answerId, @RequestBody AnswerResource resource) {
        var updateAnswerCommand = UpdateAnswerCommandFromResourceAssembler.toCommandFromResource(answerId, resource);
        var optionalAnswer = this.answerCommandService.handle(updateAnswerCommand);

        if (optionalAnswer.isEmpty())
            return ResponseEntity.badRequest().build();
        var answerResource = AnswerResourceFromEntityAssembler.toResourceFromEntity(optionalAnswer.get());
        return ResponseEntity.ok(answerResource);
    }

    @DeleteMapping("/{answerId}")
    public ResponseEntity<?> deleteAnswer(@PathVariable Long answerId) {
        var deleteAnswerCommand = new DeleteAnswerCommand(answerId);
        this.answerCommandService.handle(deleteAnswerCommand);
        return ResponseEntity.noContent().build();
    }


    @GetMapping("/search")
    public ResponseEntity<List<AnswerResource>> getAnswersByQuestionId(@RequestParam(required = false) Long questionId) {
        if (questionId == null) return ResponseEntity.badRequest().build();
        var questionIdObj = new QuestionId(questionId);
        var getAllAnswersByExamIdQuery = new GetAllAnswersByQuestionIdQuery(questionIdObj.questionId());
        var answers = this.answerQueryService.handle(getAllAnswersByExamIdQuery);
        var answerResources = answers.stream()
                .map(AnswerResourceFromEntityAssembler::toResourceFromEntity)
                .collect(Collectors.toList());
        return ResponseEntity.ok(answerResources);
    }

}
