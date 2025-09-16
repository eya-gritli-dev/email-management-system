package com.example.email.Service;

import com.example.email.Entity.Mail;
import com.example.email.Entity.ProgrammedMessage;
import com.example.email.Repository.MailRepository;
import com.example.email.Repository.ProgrammedMessageRepository;
import jakarta.mail.MessagingException;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class ProgrammedMessageService {

    @Autowired
    private ProgrammedMessageRepository programmedMessageRepository;

    @Autowired
    private MailRepository mailRepository;

    @Autowired
    private EmailSenderService emailSenderService;

    @Transactional
    public ProgrammedMessage planifierMessage(Mail mail, LocalDateTime dateProgramme) {
        // 1. Sauvegarder d'abord le Mail pour générer son ID
        Mail savedMail = mailRepository.save(mail);

        // 2. Créer le ProgrammedMessage avec la référence au Mail sauvé
        ProgrammedMessage pm = new ProgrammedMessage();
        pm.setMail(savedMail);
        pm.setDateProgramme(dateProgramme);
        pm.setFile(savedMail.getFile()); // Récupérer le fichier depuis le Mail
        pm.setStatut("EN_ATTENTE");

        // 3. Sauvegarder le ProgrammedMessage
        ProgrammedMessage savedPM = programmedMessageRepository.save(pm);

        // 4. Mettre à jour la relation bidirectionnelle
        savedMail.setMessageProgramme(savedPM);
        mailRepository.save(savedMail);

        return savedPM;
    }

    // Envoi automatique des messages programmés
    @Scheduled(fixedDelay = 60000) // toutes les 60 secondes
    @Transactional
    public void envoyerMessagesProgrammes() {
        List<ProgrammedMessage> messages = programmedMessageRepository
                .findAll()
                .stream()
                .filter(msg -> "EN_ATTENTE".equals(msg.getStatut()) &&
                        msg.getDateProgramme().isBefore(LocalDateTime.now()))
                .toList();

        for (ProgrammedMessage msg : messages) {
            Mail mail = msg.getMail();
            if (mail == null) continue;

            try {
                // Utiliser votre EmailSenderService existant
                for (var contact : mail.getCampagne().getContacts()) {
                    emailSenderService.envoyer(
                            contact.getEmail(),
                            mail.getSujet(),
                            mail.getMessage(),
                            msg.getFile(),
                            msg.getFile() != null ? "fichier.pdf" : null
                    );
                }

                // Mettre à jour le statut après envoi réussi
                msg.setStatut("ENVOYE");
                mail.setStatut("ENVOYE");
                mail.setDateEnvoi(LocalDateTime.now());

                programmedMessageRepository.save(msg);
                mailRepository.save(mail);

                System.out.println("✅ Message programmé envoyé pour le mail : " + mail.getSujet());

            } catch (MessagingException e) {
                msg.setStatut("ECHEC");
                mail.setStatut("ECHEC");

                programmedMessageRepository.save(msg);
                mailRepository.save(mail);

                System.err.println("❌ Échec envoi du message programmé pour le mail : " + mail.getSujet());
                e.printStackTrace();
            }
        }
    }
}