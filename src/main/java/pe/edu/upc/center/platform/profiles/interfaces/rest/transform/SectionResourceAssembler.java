package pe.edu.upc.center.platform.profiles.interfaces.rest.transform;

import pe.edu.upc.center.platform.profiles.domain.model.aggregates.Section;
import pe.edu.upc.center.platform.profiles.interfaces.rest.resources.SectionResource;

public class SectionResourceAssembler {
    public static SectionResource toResourceFromEntity(Section section) {
        return new SectionResource(
                section.getId(),
                section.getStudentId(),
                section.getType(),
                section.getDate(),
                section.getTime(),
                section.getEndTime(),
                section.getStatus(),
                section.getMode(),
                section.getNotes()
        );
    }
}