package pe.edu.upc.center.platform.dashboard.domain.model.aggregates;


import jakarta.persistence.*;
import lombok.Getter;
import pe.edu.upc.center.platform.dashboard.domain.model.commands.CreateRepositoryExamCommand;
import pe.edu.upc.center.platform.dashboard.domain.model.entities.TypeExam;
import pe.edu.upc.center.platform.dashboard.domain.model.valueobjects.ExamId;
import pe.edu.upc.center.platform.shared.domain.model.aggregates.AuditableAbstractAggregateRoot;

@Entity
@Table(name = "repository_exams")
@Getter
public class RepositoryExam extends AuditableAbstractAggregateRoot {


    @Getter
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "type_exam_id", nullable = false)
    private TypeExam typeExam;

    @Column(name = "description", nullable = false, length = 80)
    private String description;

    @Embedded
    @AttributeOverrides(
            {
                    @AttributeOverride(name= "examId", column = @Column(name = "exam_id", nullable = false))
            }
    )
    private ExamId examId;

    protected RepositoryExam(){}

    public RepositoryExam(TypeExam typeExam, String description, ExamId examId) {
        this.typeExam = typeExam;
        this.description = description;
        this.examId = examId;
    }

    public RepositoryExam(Long examIdParam, CreateRepositoryExamCommand command) {
        this.typeExam = TypeExam.toTypeExamFromName(command.typeExam());
        this.description = command.description();
        this.examId = new ExamId(examIdParam);
    }

}
