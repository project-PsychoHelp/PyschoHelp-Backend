package pe.edu.upc.center.platform.profiles.domain.model.commands;

public record CreateSectionCommand(Long studentId, String type, String date, String time, String endTime, String status, String mode, String notes) {}