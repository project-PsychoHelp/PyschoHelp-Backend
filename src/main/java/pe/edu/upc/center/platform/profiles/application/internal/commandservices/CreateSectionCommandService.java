package pe.edu.upc.center.platform.profiles.application.internal.commandservices;

import org.springframework.stereotype.Service;
import pe.edu.upc.center.platform.profiles.domain.model.aggregates.Section;
import pe.edu.upc.center.platform.profiles.domain.model.commands.CreateSectionCommand;
import pe.edu.upc.center.platform.profiles.domain.services.SectionService;

import java.util.Optional;

@Service
public class CreateSectionCommandService {
    private final SectionService sectionService;

    public CreateSectionCommandService(SectionService sectionService) {
        this.sectionService = sectionService;
    }

    public Optional<Section> handle(CreateSectionCommand command) {
        return sectionService.createSection(command);
    }
}