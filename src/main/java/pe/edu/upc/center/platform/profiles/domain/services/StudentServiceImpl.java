package pe.edu.upc.center.platform.profiles.domain.services;

import org.springframework.stereotype.Service;
import pe.edu.upc.center.platform.profiles.domain.model.aggregates.Student;
import pe.edu.upc.center.platform.profiles.domain.model.commands.CreateStudentCommand;
//import pe.edu.upc.center.platform.profiles.domain.model.commands.UpdateStudentProgressCommand;
import pe.edu.upc.center.platform.profiles.infrastructure.persistence.jpa.repositories.StudentRepository;

import java.util.List;
import java.util.Optional;

@Service
public class StudentServiceImpl implements StudentService {
    private final StudentRepository studentRepository;

    public StudentServiceImpl(StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }

    @Override
    public Optional<Student> createStudent(CreateStudentCommand command) {
        Student student = new Student(
                command.firstName(),
                command.lastName(),
                command.email(),
                command.phone(),
                command.age(),
                command.status(),
                command.registrationDate()
        );
        return Optional.of(studentRepository.save(student));
    }

//    @Override
//    public Optional<Student> updateStudentProgress(UpdateStudentProgressCommand command) {
//        return studentRepository.findById(command.studentId())
//                .map(student -> {
//                    student.updateProgress(command.progress());
//                    return studentRepository.save(student);
//                });
//    }

    @Override
    public List<Student> getAllStudents() {
        return studentRepository.findAll();
    }

    @Override
    public Optional<Student> getStudentById(Long id) {
        return studentRepository.findById(id);
    }
}