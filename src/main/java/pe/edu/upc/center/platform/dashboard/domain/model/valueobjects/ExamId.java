package pe.edu.upc.center.platform.dashboard.domain.model.valueobjects;

import jakarta.persistence.Embeddable;

@Embeddable
public record ExamId(Long examId) {
    public ExamId {
        if (examId == null || examId <= 0) {
            throw new IllegalArgumentException("Exam ID must be a positive number.");
        }
    }

    @Override
    public String toString() {
        return "ExamId{" +
                "examId=" + examId +
                '}';
    }


}
