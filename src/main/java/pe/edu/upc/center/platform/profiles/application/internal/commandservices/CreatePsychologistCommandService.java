package pe.edu.upc.center.platform.profiles.application.internal.commandservices;

import org.springframework.stereotype.Service;
import pe.edu.upc.center.platform.profiles.domain.model.aggregates.Psychologist;
import pe.edu.upc.center.platform.profiles.domain.model.commands.CreatePsychologistCommand;
import pe.edu.upc.center.platform.profiles.domain.services.PsychologistService;

import java.util.Optional;

@Service
public class CreatePsychologistCommandService {
    private final PsychologistService psychologistService;

    public CreatePsychologistCommandService(PsychologistService psychologistService) {
        this.psychologistService = psychologistService;
    }

    public Optional<Psychologist> handle(CreatePsychologistCommand command) {
        return psychologistService.createPsychologist(command);
    }
}