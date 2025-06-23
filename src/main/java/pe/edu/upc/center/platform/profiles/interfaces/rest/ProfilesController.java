package pe.edu.upc.center.platform.profiles.interfaces.rest;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pe.edu.upc.center.platform.profiles.domain.model.aggregates.Student;
import pe.edu.upc.center.platform.profiles.domain.model.aggregates.Section;
import pe.edu.upc.center.platform.profiles.domain.model.aggregates.Dashboard;
import pe.edu.upc.center.platform.profiles.domain.model.aggregates.Psychologist;
import pe.edu.upc.center.platform.profiles.domain.model.commands.CreateStudentCommand;
import pe.edu.upc.center.platform.profiles.domain.model.commands.CreateSectionCommand;
import pe.edu.upc.center.platform.profiles.domain.model.commands.CreateDashboardCommand;
import pe.edu.upc.center.platform.profiles.domain.model.commands.CreatePsychologistCommand;
import pe.edu.upc.center.platform.profiles.domain.model.queries.GetAllStudentsQuery;
import pe.edu.upc.center.platform.profiles.domain.model.queries.GetAllSectionsQuery;
import pe.edu.upc.center.platform.profiles.domain.model.queries.GetDashboardQuery;
import pe.edu.upc.center.platform.profiles.domain.model.queries.GetPsychologistQuery;
import pe.edu.upc.center.platform.profiles.interfaces.acl.ProfilesFacade;
import pe.edu.upc.center.platform.profiles.interfaces.rest.resources.*;
import pe.edu.upc.center.platform.profiles.interfaces.rest.transform.*;
import pe.edu.upc.center.platform.shared.interfaces.rest.resources.MessageResource;

import java.util.List;
import java.util.stream.Collectors;

@CrossOrigin(origins = "*", methods = { RequestMethod.POST, RequestMethod.GET, RequestMethod.PUT, RequestMethod.DELETE })
@RestController
@RequestMapping("/api/v1/profiles")
@Tag(name = "Profile Dashboard and Analytics", description = "Profiles Management Endpoints")
public class ProfilesController {
    private final ProfilesFacade profilesFacade;

    public ProfilesController(ProfilesFacade profilesFacade) {
        this.profilesFacade = profilesFacade;
    }

    @PostMapping("/students")
    @Operation(
            summary = "Crear un nuevo estudiante",
            description = "Crea un estudiante en el sistema con los datos proporcionados en el cuerpo de la solicitud."
    )
    public ResponseEntity<MessageResource> createStudent(@RequestBody CreateStudentCommand command) {
        profilesFacade.createStudent(command);
        return ResponseEntity.ok(new MessageResource("Student created successfully"));
    }

    @GetMapping("/students")
    @Operation(
            summary = "Obtener todos los estudiantes",
            description = "Recupera una lista de todos los estudiantes registrados en el sistema."
    )
    public ResponseEntity<List<StudentResource>> getAllStudents() {
        List<Student> students = profilesFacade.getAllStudents();
        List<StudentResource> resources = students.stream()
                .map(StudentResourceAssembler::toResourceFromEntity)
                .collect(Collectors.toList());
        return ResponseEntity.ok(resources);
    }

    @PostMapping("/sections")
    @Operation(
            summary = "Crear una nueva sección",
            description = "Crea una sección en el sistema con los datos proporcionados en el cuerpo de la solicitud."
    )
    public ResponseEntity<MessageResource> createSection(@RequestBody CreateSectionCommand command) {
        profilesFacade.createSection(command);
        return ResponseEntity.ok(new MessageResource("Section created successfully"));
    }

    @GetMapping("/sections")
    @Operation(
            summary = "Obtener todas las secciones",
            description = "Recupera una lista de todas las secciones registradas en el sistema."
    )
    public ResponseEntity<List<SectionResource>> getAllSections() {
        List<Section> sections = profilesFacade.getAllSections();
        List<SectionResource> resources = sections.stream()
                .map(SectionResourceAssembler::toResourceFromEntity)
                .collect(Collectors.toList());
        return ResponseEntity.ok(resources);
    }

    @PostMapping("/dashboard")
@Operation(
            summary = "Crear un nuevo dashboard",
            description = "Crea un dashboard en el sistema con los datos proporcionados en el cuerpo de la solicitud."
    )
    public ResponseEntity<MessageResource> createDashboard(@RequestBody CreateDashboardCommand command) {
        profilesFacade.createDashboard(command);
        return ResponseEntity.ok(new MessageResource("Dashboard created successfully"));
    }

    @GetMapping("/dashboard")
@Operation(
            summary = "Obtener el dashboard",
            description = "Recupera el dashboard del sistema con los datos actuales."
    )
    public ResponseEntity<DashboardResource> getDashboard() {
        return profilesFacade.getDashboard()
                .map(DashboardResourceAssembler::toResourceFromEntity)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping("/psychologist")
    @Operation(
            summary = "Crear un nuevo psicólogo",
            description = "Crea un psicólogo en el sistema con los datos proporcionados en el cuerpo de la solicitud."
    )
    public ResponseEntity<MessageResource> createPsychologist(@RequestBody CreatePsychologistCommand command) {
        profilesFacade.createPsychologist(command);
        return ResponseEntity.ok(new MessageResource("Psychologist created successfully"));
    }

    @GetMapping("/psychologist")
@Operation(
            summary = "Obtener el psicólogo",
            description = "Recupera los datos del psicólogo registrado en el sistema."
    )
    public ResponseEntity<PsychologistResource> getPsychologist() {
        return profilesFacade.getPsychologist()
                .map(PsychologistResourceAssembler::toResourceFromEntity)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }
}