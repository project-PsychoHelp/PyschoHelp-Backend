package pe.edu.upc.center.platform.profiles.interfaces.rest.transform;

import pe.edu.upc.center.platform.profiles.domain.model.aggregates.Psychologist;
import pe.edu.upc.center.platform.profiles.interfaces.rest.resources.PsychologistResource;

public class PsychologistResourceAssembler {
    public static PsychologistResource toResourceFromEntity(Psychologist psychologist) {
        return new PsychologistResource(
                psychologist.getId(),
                psychologist.getName(),
                psychologist.getTitle(),
                psychologist.getEmail(),
                psychologist.getPhone(),
                psychologist.getSpecialties()
        );
    }
}