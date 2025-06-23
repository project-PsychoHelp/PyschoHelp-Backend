package pe.edu.upc.center.platform.profiles.interfaces.rest.resources;

public record StudentResource(Long id, String firstName, String lastName, String fullName, String email, String phone, Integer age, String status, String registrationDate, String userType) {}