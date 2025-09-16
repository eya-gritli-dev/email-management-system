package com.example.email.Repository;

import com.example.email.Entity.Mail;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface MailRepository extends JpaRepository<Mail, Long> {

    // Historique par statut (EN_ATTENTE, ENVOYE, ECHEC, etc.)
    List<Mail> findByStatut(String statut);

    // Tous les mails d’une campagne (pour la visualisation)
    List<Mail> findByCampagneId(Long campagneId);
}
