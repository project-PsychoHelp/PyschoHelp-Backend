package pe.edu.upc.center.platform.profiles.infrastructure.persistence.jpa.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import pe.edu.upc.center.platform.profiles.domain.model.aggregates.Psychologist;

import java.util.Optional;

public interface PsychologistRepository extends JpaRepository<Psychologist, Long> {
    Optional<Psychologist> findByEmail(String email);
}