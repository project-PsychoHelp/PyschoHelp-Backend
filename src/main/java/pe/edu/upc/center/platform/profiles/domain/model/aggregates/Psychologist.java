package pe.edu.upc.center.platform.profiles.domain.model.aggregates;

import jakarta.persistence.*;
import lombok.Getter;
import pe.edu.upc.center.platform.shared.domain.model.aggregates.AuditableAbstractAggregateRoot;

import java.util.List;

@Entity
@Table(name = "psychologists")
public class Psychologist extends AuditableAbstractAggregateRoot<Psychologist> {
    @Getter
    @Column(name = "name", nullable = false)
    private String name;

    @Getter
    @Column(name = "title", nullable = false)
    private String title;

    @Getter
    @Column(name = "email", nullable = false, unique = true)
    private String email;

    @Getter
    @Column(name = "phone", nullable = false)
    private String phone;

    @Getter
    @ElementCollection
    @CollectionTable(name = "psychologist_specialties", joinColumns = @JoinColumn(name = "psychologist_id"))
    private List<String> specialties;

    @OneToMany(mappedBy = "studentId", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Student> students;

    public Psychologist() {}

    public Psychologist(String name, String title, String email, String phone, List<String> specialties) {
        this.name = name;
        this.title = title;
        this.email = email;
        this.phone = phone;
        this.specialties = specialties;
    }

    public void addStudent(Student student) {
        this.students.add(student);
    }
}