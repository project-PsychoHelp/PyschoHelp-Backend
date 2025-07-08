package pe.edu.upc.center.platform.test.domain.services;

import pe.edu.upc.center.platform.test.domain.model.aggregates.Question;
import pe.edu.upc.center.platform.test.domain.model.queries.GetAllAnswersByQuestionIdQuery;
import pe.edu.upc.center.platform.test.domain.model.queries.GetAllAnswersQuery;
import pe.edu.upc.center.platform.test.domain.model.queries.GetAnswerByIdQuery;
import pe.edu.upc.center.platform.test.domain.model.aggregates.Answer;

import java.util.List;
import java.util.Optional;

public interface AnswerQueryService {


    List<Answer> handle(GetAllAnswersQuery query);
    Optional<Answer> handle(GetAnswerByIdQuery query);
    List<Answer> handle(GetAllAnswersByQuestionIdQuery query);
}
