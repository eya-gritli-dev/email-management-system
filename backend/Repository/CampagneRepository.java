package com.example.email.Repository;
import com.example.email.Entity.Campagne;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;

public interface CampagneRepository extends JpaRepository<Campagne, Long> {

    @EntityGraph(attributePaths = {"contacts", "mails"})
    Optional<Campagne> findById(Long id);
}
