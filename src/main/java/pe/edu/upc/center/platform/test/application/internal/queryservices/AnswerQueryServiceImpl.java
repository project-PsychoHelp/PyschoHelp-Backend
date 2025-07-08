package pe.edu.upc.center.platform.test.application.internal.queryservices;

import org.springframework.stereotype.Service;
import pe.edu.upc.center.platform.test.domain.model.aggregates.Answer;
import pe.edu.upc.center.platform.test.domain.model.aggregates.Question;
import pe.edu.upc.center.platform.test.domain.model.queries.*;

import pe.edu.upc.center.platform.test.domain.model.valueobjects.ExamId;
import pe.edu.upc.center.platform.test.domain.model.valueobjects.QuestionId;
import pe.edu.upc.center.platform.test.domain.services.AnswerQueryService;
import pe.edu.upc.center.platform.test.infrastructure.persistence.jpa.repositories.AnswerRepository;

import java.util.List;
import java.util.Optional;

@Service
public class AnswerQueryServiceImpl implements AnswerQueryService {
    private final AnswerRepository answerRepository;

    public AnswerQueryServiceImpl(AnswerRepository answerRepository) {
        this.answerRepository = answerRepository;
    }

    @Override
    public List<Answer> handle(GetAllAnswersQuery query){
        return this.answerRepository.findAll();
    }

    @Override
    public Optional<Answer> handle(GetAnswerByIdQuery query) { return this.answerRepository.findById(query.answerId());
    }

    @Override
    public List<Answer> handle(GetAllAnswersByQuestionIdQuery query) {
        var questionIdObj = new QuestionId(query.questionId());
        return this.answerRepository.findByQuestionId(questionIdObj);
    }


}
