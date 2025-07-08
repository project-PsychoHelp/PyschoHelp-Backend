package pe.edu.upc.center.platform.test.application.internal.queryservices;


import org.springframework.stereotype.Service;
import pe.edu.upc.center.platform.test.domain.model.aggregates.Exam;
import pe.edu.upc.center.platform.test.domain.model.aggregates.Question;
import pe.edu.upc.center.platform.test.domain.model.queries.*;
import pe.edu.upc.center.platform.test.domain.model.valueobjects.ExamId;
import pe.edu.upc.center.platform.test.domain.services.QuestionQueryService;
import pe.edu.upc.center.platform.test.infrastructure.persistence.jpa.repositories.QuestionRepository;

import java.util.List;
import java.util.Optional;

@Service
public class QuestionQueryServiceImpl implements QuestionQueryService {

    private final QuestionRepository questionRepository;

    public QuestionQueryServiceImpl(QuestionRepository questionRepository) {
        this.questionRepository = questionRepository;
    }

    @Override
    public List<Question> handle(GetAllQuestionsQuery query){
        return this.questionRepository.findAll();
    }

    @Override
    public Optional<Question> handle(GetQuestionByIdQuery query) { return this.questionRepository.findById(query.questionId());
    }

    @Override
    public List<Question> handle(GetAllQuestionsByExamIdQuery query) {
        var examIdObj = new ExamId(query.examId());
        return this.questionRepository.findByExamId(examIdObj);
    }

}
