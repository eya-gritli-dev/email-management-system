package com.example.email.Repository;

import com.example.email.Entity.ProgrammedMessage;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface ProgrammedMessageRepository extends JpaRepository<ProgrammedMessage, Long> {

    // Récupérer les messages à déclencher (statut = EN_ATTENTE et date atteinte)
    List<ProgrammedMessage> findByStatutAndDateProgrammeBefore(String statut, LocalDateTime now);

    // Historique des programmations (le plus récent en premier)
    List<ProgrammedMessage> findAllByOrderByDateProgrammeDesc();
}
