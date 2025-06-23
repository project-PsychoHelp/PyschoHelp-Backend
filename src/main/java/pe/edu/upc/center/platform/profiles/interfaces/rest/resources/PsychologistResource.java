package pe.edu.upc.center.platform.profiles.interfaces.rest.resources;

import java.util.List;

public record PsychologistResource(Long id, String name, String title, String email, String phone, List<String> specialties) {}