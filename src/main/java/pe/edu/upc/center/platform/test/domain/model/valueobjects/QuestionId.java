package pe.edu.upc.center.platform.test.domain.model.valueobjects;

import jakarta.persistence.Embeddable;

@Embeddable
public record QuestionId(Long questionId) {
    public QuestionId {
        if (questionId == null || questionId <= 0) {
            throw new IllegalArgumentException("Exam ID must be a positive number.");
        }
    }

    @Override
    public String toString() {
        return "ExamId{" +
                "examId=" + questionId +
                '}';
    }


}