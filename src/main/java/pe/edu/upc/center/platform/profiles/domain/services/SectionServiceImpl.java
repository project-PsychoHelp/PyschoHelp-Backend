package pe.edu.upc.center.platform.profiles.domain.services;

import org.springframework.stereotype.Service;
import pe.edu.upc.center.platform.profiles.domain.model.aggregates.Section;
import pe.edu.upc.center.platform.profiles.domain.model.aggregates.Student;
import pe.edu.upc.center.platform.profiles.domain.model.commands.CreateSectionCommand;
import pe.edu.upc.center.platform.profiles.infrastructure.persistence.jpa.repositories.SectionRepository;
import pe.edu.upc.center.platform.profiles.infrastructure.persistence.jpa.repositories.StudentRepository;

import java.util.List;
import java.util.Optional;

@Service
public class SectionServiceImpl implements SectionService {
    private final SectionRepository sectionRepository;
    private final StudentRepository studentRepository;

    public SectionServiceImpl(SectionRepository sectionRepository, StudentRepository studentRepository) {
        this.sectionRepository = sectionRepository;
        this.studentRepository = studentRepository;
    }

    @Override
    public Optional<Section> createSection(CreateSectionCommand command) {
        Optional<Student> student = studentRepository.findById(command.studentId());
        if (student.isEmpty()) return Optional.empty();
        Section section = new Section(
                command.studentId(),
                command.type(),
                command.date(),
                command.time(),
                command.endTime(),
                command.status(),
                command.mode(),
                command.notes()
        );
        return Optional.of(sectionRepository.save(section));
    }

    @Override
    public List<Section> getAllSections() {
        return sectionRepository.findAll();
    }

    @Override
    public Optional<Section> getSectionById(Long id) {
        return sectionRepository.findById(id);
    }
}