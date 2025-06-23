package pe.edu.upc.center.platform.profiles.interfaces.rest.resources;

public record SectionResource(Long id, Long studentId, String type, String date, String time, String endTime, String status, String mode, String notes) {}