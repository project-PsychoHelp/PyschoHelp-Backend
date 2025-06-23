package pe.edu.upc.center.platform.profiles.domain.services;

import org.springframework.stereotype.Service;
import pe.edu.upc.center.platform.profiles.domain.model.aggregates.Psychologist;
import pe.edu.upc.center.platform.profiles.domain.model.commands.CreatePsychologistCommand;
import pe.edu.upc.center.platform.profiles.infrastructure.persistence.jpa.repositories.PsychologistRepository;

import java.util.Optional;

@Service
public class PsychologistServiceImpl implements PsychologistService {
    private final PsychologistRepository psychologistRepository;

    public PsychologistServiceImpl(PsychologistRepository psychologistRepository) {
        this.psychologistRepository = psychologistRepository;
    }

    @Override
    public Optional<Psychologist> createPsychologist(CreatePsychologistCommand command) {
        Psychologist psychologist = new Psychologist(
                command.name(),
                command.title(),
                command.email(),
                command.phone(),
                command.specialties()
        );
        return Optional.of(psychologistRepository.save(psychologist));
    }

    @Override
    public Optional<Psychologist> getPsychologist() {
        return psychologistRepository.findAll().stream().findFirst();
    }
}