package pe.edu.upc.center.platform.profiles.domain.services;

import pe.edu.upc.center.platform.profiles.domain.model.aggregates.Section;
import pe.edu.upc.center.platform.profiles.domain.model.commands.CreateSectionCommand;

import java.util.List;
import java.util.Optional;

public interface SectionService {
    Optional<Section> createSection(CreateSectionCommand command);
    List<Section> getAllSections();
    Optional<Section> getSectionById(Long id);
}