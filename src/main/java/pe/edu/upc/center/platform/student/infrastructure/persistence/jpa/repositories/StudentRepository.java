package pe.edu.upc.center.platform.student.infrastructure.persistence.jpa.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pe.edu.upc.center.platform.student.domain.model.aggregates.Student;
import pe.edu.upc.center.platform.student.domain.model.valueobjects.ProfileId;
import pe.edu.upc.center.platform.student.domain.model.valueobjects.StudentCode;

import java.util.Optional;

@Repository
public interface StudentRepository extends JpaRepository<Student, Long> {
  Optional<Student> findByStudentCode(StudentCode studentCode);
  Optional<Student> findByProfileId(ProfileId profileId);
  boolean existsByStudentCode(StudentCode studentCode);
}
