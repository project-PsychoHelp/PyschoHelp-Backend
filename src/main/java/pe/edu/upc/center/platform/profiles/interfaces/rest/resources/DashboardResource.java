package pe.edu.upc.center.platform.profiles.interfaces.rest.resources;

public record DashboardResource(Long id, Integer activeStudents, Integer scheduledSessions, Integer consultationHours, Double averageRating, Integer totalRatings, String lastUpdate) {}