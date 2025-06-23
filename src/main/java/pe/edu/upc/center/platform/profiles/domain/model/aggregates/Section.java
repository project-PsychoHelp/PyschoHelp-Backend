package pe.edu.upc.center.platform.profiles.domain.model.aggregates;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import pe.edu.upc.center.platform.shared.domain.model.aggregates.AuditableAbstractAggregateRoot;

@Entity
@Table(name = "sections")
public class Section extends AuditableAbstractAggregateRoot<Section> {
    @Setter
    @Getter
    @Column(name = "student_id", nullable = false)
    private Long studentId;

    @Getter
    @Column(name = "type", nullable = false)
    private String type;

    @Getter
    @Column(name = "date", nullable = false)
    private String date;

    @Getter
    @Column(name = "time", nullable = false)
    private String time;

    @Getter
    @Column(name = "end_time", nullable = false)
    private String endTime;

    @Getter
    @Column(name = "status", nullable = false)
    private String status;

    @Getter
    @Column(name = "mode", nullable = false)
    private String mode;

    @Getter
    @Column(name = "notes")
    private String notes;

    public Section() {}

    public Section(Long studentId, String type, String date, String time, String endTime, String status, String mode, String notes) {
        this.studentId = studentId;
        this.type = type;
        this.date = date;
        this.time = time;
        this.endTime = endTime;
        this.status = status;
        this.mode = mode;
        this.notes = notes;
    }

}