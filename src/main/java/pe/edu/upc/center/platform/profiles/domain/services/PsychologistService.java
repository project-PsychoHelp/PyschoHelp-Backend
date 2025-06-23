package pe.edu.upc.center.platform.profiles.domain.services;

import pe.edu.upc.center.platform.profiles.domain.model.aggregates.Psychologist;
import pe.edu.upc.center.platform.profiles.domain.model.commands.CreatePsychologistCommand;

import java.util.Optional;

public interface PsychologistService {
    Optional<Psychologist> createPsychologist(CreatePsychologistCommand command);
    Optional<Psychologist> getPsychologist();
}