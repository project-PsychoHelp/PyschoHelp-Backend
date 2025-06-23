package pe.edu.upc.center.platform.profiles.domain.model.commands;

import java.util.List;

public record CreatePsychologistCommand(String name, String title, String email, String phone, List<String> specialties) {}