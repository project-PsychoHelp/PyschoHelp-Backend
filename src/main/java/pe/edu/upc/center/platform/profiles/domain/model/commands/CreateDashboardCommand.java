package pe.edu.upc.center.platform.profiles.domain.model.commands;

public record CreateDashboardCommand(Integer activeStudents, Integer scheduledSessions, Integer consultationHours, Double averageRating, Integer totalRatings, String lastUpdate) {}