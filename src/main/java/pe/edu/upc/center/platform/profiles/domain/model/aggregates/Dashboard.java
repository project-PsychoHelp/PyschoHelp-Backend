package pe.edu.upc.center.platform.profiles.domain.model.aggregates;

import jakarta.persistence.*;
import lombok.Getter;
import pe.edu.upc.center.platform.shared.domain.model.aggregates.AuditableAbstractAggregateRoot;

@Entity
@Table(name = "dashboards")
public class Dashboard extends AuditableAbstractAggregateRoot<Dashboard> {
    @Getter
    @Column(name = "active_students", nullable = false)
    private Integer activeStudents;

    @Getter
    @Column(name = "scheduled_sessions", nullable = false)
    private Integer scheduledSessions;

    @Getter
    @Column(name = "consultation_hours", nullable = false)
    private Integer consultationHours;

    @Getter
    @Column(name = "average_rating", nullable = false)
    private Double averageRating;

    @Getter
    @Column(name = "total_ratings", nullable = false)
    private Integer totalRatings;

    @Getter
    @Column(name = "last_update", nullable = false)
    private String lastUpdate;

    public Dashboard() {}

    public Dashboard(Integer activeStudents, Integer scheduledSessions, Integer consultationHours, Double averageRating, Integer totalRatings, String lastUpdate) {
        this.activeStudents = activeStudents;
        this.scheduledSessions = scheduledSessions;
        this.consultationHours = consultationHours;
        this.averageRating = averageRating;
        this.totalRatings = totalRatings;
        this.lastUpdate = lastUpdate;
    }

    public void updateMetrics(Integer activeStudents, Integer scheduledSessions, Integer consultationHours, Double averageRating, Integer totalRatings, String lastUpdate) {
        this.activeStudents = activeStudents;
        this.scheduledSessions = scheduledSessions;
        this.consultationHours = consultationHours;
        this.averageRating = averageRating;
        this.totalRatings = totalRatings;
        this.lastUpdate = lastUpdate;
        //registerEvent(new DashboardMetricsUpdatedEvent(this.getId(), this));
    }
}