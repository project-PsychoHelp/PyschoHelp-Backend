package pe.edu.upc.center.platform.test.application.internal.queryservices;

import org.springframework.stereotype.Service;
import pe.edu.upc.center.platform.test.domain.model.aggregates.Exam;
import pe.edu.upc.center.platform.test.domain.model.queries.GetAllExamsQuery;
import pe.edu.upc.center.platform.test.domain.model.queries.GetExamByIdQuery;
import pe.edu.upc.center.platform.test.domain.services.ExamQueryService;
import pe.edu.upc.center.platform.test.infrastructure.persistence.jpa.repositories.ExamRepository;

import java.util.List;
import java.util.Optional;

@Service
public class ExamQueryServiceImpl implements ExamQueryService {

    private final ExamRepository examRepository;

    public ExamQueryServiceImpl(ExamRepository examRepository) {
        this.examRepository = examRepository;
    }


    @Override
    public List<Exam> handle(GetAllExamsQuery query){
        return this.examRepository.findAll();
    }

    @Override
    public Optional<Exam> handle(GetExamByIdQuery query) {
        return this.examRepository.findById(query.examId());
    }



}
