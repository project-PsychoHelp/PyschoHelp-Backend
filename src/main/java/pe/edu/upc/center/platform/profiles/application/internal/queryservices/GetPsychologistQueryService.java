package pe.edu.upc.center.platform.profiles.application.internal.queryservices;

import org.springframework.stereotype.Service;
import pe.edu.upc.center.platform.profiles.domain.model.aggregates.Psychologist;
import pe.edu.upc.center.platform.profiles.domain.model.queries.GetPsychologistQuery;
import pe.edu.upc.center.platform.profiles.domain.services.PsychologistService;

import java.util.Optional;

@Service
public class GetPsychologistQueryService {
    private final PsychologistService psychologistService;

    public GetPsychologistQueryService(PsychologistService psychologistService) {
        this.psychologistService = psychologistService;
    }

    public Optional<Psychologist> handle(GetPsychologistQuery query) {
        return psychologistService.getPsychologist();
    }
}