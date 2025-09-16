package com.example.email.Service;

import com.example.email.Entity.Campagne;
import com.example.email.Entity.Mail;
import com.example.email.Entity.Contact;
import com.example.email.Repository.CampagneRepository;
import com.example.email.Repository.MailRepository;
import jakarta.mail.MessagingException;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Set;

@Service
public class MailService {

    @Autowired
    private MailRepository mailRepository;

    @Autowired
    private CampagneRepository campagneRepository;

    @Autowired
    private EmailSenderService emailSenderService;

    @Autowired
    private ProgrammedMessageService programmedMessageService;

    // Création ou envoi d'un mail (texte ou pièce jointe)
    public Mail creerOuEnvoyerMail(String sujet, String message, LocalDateTime dateProgramme,
                                   MultipartFile fichier, String statut, Long campagneId) throws IOException {

        // Récupérer la campagne
        Campagne campagne = campagneRepository.findById(campagneId)
                .orElseThrow(() -> new RuntimeException("Campagne non trouvée"));

        // Créer le mail
        Mail mail = new Mail();
        mail.setSujet(sujet);
        mail.setMessage(message);
        mail.setCampagne(campagne);

        // Gérer le fichier
        if (fichier != null && !fichier.isEmpty()) {
            mail.setFile(fichier.getBytes());
            mail.setType("AVEC_FICHIER");
        } else {
            mail.setType("TEXTE_SEUL");
        }

        // Ajouter à la campagne
        if (campagne.getMails() == null) {
            campagne.setMails(Set.of(mail));
        } else {
            campagne.getMails().add(mail);
        }

        // Décider si envoi immédiat ou programmé
        if (dateProgramme != null && dateProgramme.isAfter(LocalDateTime.now())) {
            // MESSAGE PROGRAMMÉ - utiliser ProgrammedMessageService
            mail.setDateProgramme(dateProgramme);
            mail.setStatut("PROGRAMME");

            // Utiliser le service pour créer le message programmé
            programmedMessageService.planifierMessage(mail, dateProgramme);

            System.out.println("📅 Mail programmé créé pour le " + dateProgramme);
            return mail;

        } else {
            // ENVOI IMMÉDIAT
            mail.setDateEnvoi(LocalDateTime.now());
            mail.setStatut("EN_COURS");

            // Sauvegarder d'abord
            mail = mailRepository.save(mail);

            // Envoyer immédiatement
            try {
                Set<Contact> contacts = campagne.getContacts();
                for (Contact contact : contacts) {
                    emailSenderService.envoyer(
                            contact.getEmail(),
                            sujet,
                            message,
                            mail.getFile(),
                            mail.getFile() != null ? "fichier.pdf" : null
                    );
                }
                mail.setStatut("ENVOYE");
                System.out.println("✅ Mail envoyé immédiatement : " + sujet);

            } catch (MessagingException e) {
                mail.setStatut("ECHEC");
                System.err.println("❌ Échec envoi immédiat : " + e.getMessage());
                e.printStackTrace();
            }

            return mailRepository.save(mail);
        }
    }

    // SUPPRIMER cette méthode car maintenant c'est ProgrammedMessageService qui s'en charge
    // @Scheduled(fixedDelay = 6000)
    // public void envoyerMailsProgrammes() { ... }

    // CRUD classique
    public Mail creerMail(Mail mail) {
        return mailRepository.save(mail);
    }

    public List<Mail> getAllMails() {
        return mailRepository.findAll();
    }

    public Mail getMailById(Long id) {
        return mailRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Mail non trouvé"));
    }

    public void deleteMail(Long id) {
        mailRepository.deleteById(id);
    }

    public List<Mail> getMailsEnvoyes() {
        return mailRepository.findByStatut("ENVOYE");
    }

    public List<Mail> getMailsProgrammes() {
        return mailRepository.findByStatut("PROGRAMME");
    }
}