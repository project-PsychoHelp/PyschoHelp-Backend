package pe.edu.upc.center.platform.profiles.interfaces.acl;

import org.springframework.stereotype.Service;
import pe.edu.upc.center.platform.profiles.domain.model.aggregates.Student;
import pe.edu.upc.center.platform.profiles.domain.model.aggregates.Section;
import pe.edu.upc.center.platform.profiles.domain.model.aggregates.Dashboard;
import pe.edu.upc.center.platform.profiles.domain.model.aggregates.Psychologist;
import pe.edu.upc.center.platform.profiles.domain.model.commands.*;
import pe.edu.upc.center.platform.profiles.domain.model.queries.*;
import pe.edu.upc.center.platform.profiles.application.internal.commandservices.*;
import pe.edu.upc.center.platform.profiles.application.internal.queryservices.*;

import java.util.List;
import java.util.Optional;
@Service
public class ProfilesFacade {
    private final CreateStudentCommandService createStudentCommandService;
    private final GetAllStudentsQueryService getAllStudentsQueryService;
    private final CreateSectionCommandService createSectionCommandService;
    private final GetAllSectionsQueryService getAllSectionsQueryService;
    private final CreateDashboardCommandService createDashboardCommandService;
    private final GetDashboardQueryService getDashboardQueryService;
    private final CreatePsychologistCommandService createPsychologistCommandService;
    private final GetPsychologistQueryService getPsychologistQueryService;

    public ProfilesFacade(CreateStudentCommandService createStudentCommandService,
                          GetAllStudentsQueryService getAllStudentsQueryService,
                          CreateSectionCommandService createSectionCommandService,
                          GetAllSectionsQueryService getAllSectionsQueryService,
                          CreateDashboardCommandService createDashboardCommandService,
                          GetDashboardQueryService getDashboardQueryService,
                          CreatePsychologistCommandService createPsychologistCommandService,
                          GetPsychologistQueryService getPsychologistQueryService) {
        this.createStudentCommandService = createStudentCommandService;
        this.getAllStudentsQueryService = getAllStudentsQueryService;
        this.createSectionCommandService = createSectionCommandService;
        this.getAllSectionsQueryService = getAllSectionsQueryService;
        this.createDashboardCommandService = createDashboardCommandService;
        this.getDashboardQueryService = getDashboardQueryService;
        this.createPsychologistCommandService = createPsychologistCommandService;
        this.getPsychologistQueryService = getPsychologistQueryService;
    }

    public Optional<Student> createStudent(CreateStudentCommand command) {
        return createStudentCommandService.handle(command);
    }

    public List<Student> getAllStudents() {
        return getAllStudentsQueryService.handle(new GetAllStudentsQuery());
    }

    public Optional<Section> createSection(CreateSectionCommand command) {
        return createSectionCommandService.handle(command);
    }

    public List<Section> getAllSections() {
        return getAllSectionsQueryService.handle(new GetAllSectionsQuery());
    }

    public Optional<Dashboard> createDashboard(CreateDashboardCommand command) {
        return createDashboardCommandService.handle(command);
    }

    public Optional<Dashboard> getDashboard() {
        return getDashboardQueryService.handle(new GetDashboardQuery());
    }

    public Optional<Psychologist> createPsychologist(CreatePsychologistCommand command) {
        return createPsychologistCommandService.handle(command);
    }

    public Optional<Psychologist> getPsychologist() {
        return getPsychologistQueryService.handle(new GetPsychologistQuery());
    }
}