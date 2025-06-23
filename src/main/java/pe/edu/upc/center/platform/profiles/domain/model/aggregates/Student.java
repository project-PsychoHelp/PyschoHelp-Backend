package pe.edu.upc.center.platform.profiles.domain.model.aggregates;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import pe.edu.upc.center.platform.shared.domain.model.aggregates.AuditableAbstractAggregateRoot;
import pe.edu.upc.center.platform.profiles.domain.model.valueobjects.Progress;
import pe.edu.upc.center.platform.profiles.domain.model.valueobjects.RecommendedCareer;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "students")
public class Student extends AuditableAbstractAggregateRoot<Student> {

    @Getter
    @NotNull
    @NotBlank
    @Column(name = "studentId" , unique = true)
    private Long studentId;

    @Getter
    @Column(name = "first_name", nullable = false)
    private String firstName;

    @Getter
    @Column(name = "last_name", nullable = false)
    private String lastName;

    @Getter
    @Column(name = "full_name")
    private String fullName;

    @Getter
    @Column(name = "email", nullable = false, unique = true)
    private String email;

    @Getter
    @Column(name = "phone", nullable = false)
    private String phone;

    @Getter
    @Column(name = "age", nullable = false)
    private Integer age;

    @Getter
    @Column(name = "status", nullable = false)
    private String status;

    @Getter
    @Column(name = "registration_date", nullable = false)
    private String registrationDate;

    @Getter
    @Column(name = "user_type")
    private String userType;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "progress_id")
    private Progress progress;

    @ElementCollection
    @CollectionTable(name = "recommended_careers", joinColumns = @JoinColumn(name = "student_id"))
    private List<RecommendedCareer> recommendedCareers = new ArrayList<>();

    @OneToMany(mappedBy = "studentId", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Section> sections = new ArrayList<>();

    public Student(){}
    public Student(String userType) {
        userType = userType;
        this.progress = new Progress();
    }

    public Student(Long studentId, String firstName, String lastName, String email, String phone, Integer age, String status, String registrationDate) {
        this.studentId = studentId;
        this.firstName = firstName;
        this.lastName = lastName;
        this.fullName = firstName + " " + lastName;
        this.email = email;
        this.phone = phone;
        this.age = age;
        this.status = status;
        this.registrationDate = registrationDate;
        this.userType = "Estudiante";
        this.progress = new Progress();
    }

    public Student(String s, String s1, String email, String phone, Integer age, String status, String s2) {
        super();
    }

//    public void updateProgress(Progress progress) {
//        this.progress = progress;
//        registerEvent(new StudentProgressUpdatedEvent(this.getId(), progress));
//    }

    public void addRecommendedCareer(RecommendedCareer career) {
        this.recommendedCareers.add(career);
    }

    public void addSection(Section section) {
        this.sections.add(section);
        section.setStudentId(this.getId());
    }
}