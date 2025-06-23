package pe.edu.upc.center.platform.profiles.domain.model.commands;

public record CreateStudentCommand(String firstName, String lastName, String email, String phone, Integer age, String status, String registrationDate) {}