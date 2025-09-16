package com.example.email.Controller;

import com.example.email.Entity.Mail;
import com.example.email.Service.MailService;
import com.example.email.Repository.MailRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.time.LocalDateTime;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/api/mails")
public class MailController {

    @Autowired
    private MailService mailService;

    @Autowired
    private MailRepository mailRepository;

    // Envoi / création d'un mail (texte ou pièce jointe, immédiat ou programmé)
    @PostMapping(value = "/envoyer", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<Mail> envoyerMail(
            @RequestParam("sujet") String sujet,
            @RequestParam("contenu") String contenu,
            @RequestParam(value = "dateEnvoi", required = false) String dateEnvoiStr,
            @RequestParam(value = "statut", required = false) String statut,
            @RequestParam("campagneId") Long campagneId,
            @RequestPart(value = "fichier", required = false) MultipartFile fichier
    ) throws IOException {

        // Conversion de la date si elle est fournie
        LocalDateTime dateEnvoi = null;
        if (dateEnvoiStr != null && !dateEnvoiStr.isEmpty()) {
            dateEnvoi = LocalDateTime.parse(dateEnvoiStr);
        }

        // Appel du service pour créer ou envoyer le mail
        Mail mail = mailService.creerOuEnvoyerMail(
                sujet,
                contenu,
                dateEnvoi,
                fichier,
                statut,
                campagneId
        );

        return ResponseEntity.ok(mail);
    }

    // Les autres endpoints CRUD
    @GetMapping
    public ResponseEntity<?> getAllMails() {
        return ResponseEntity.ok(mailService.getAllMails());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Mail> getMailById(@PathVariable Long id) {
        return mailRepository.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public void deleteMail(@PathVariable Long id) {
        mailService.deleteMail(id);
    }

    @GetMapping("/envoyes")
    public ResponseEntity<?> getMailsEnvoyes() {
        return ResponseEntity.ok(mailService.getMailsEnvoyes());
    }

    @GetMapping("/programmes")
    public ResponseEntity<?> getMailsProgrammes() {
        return ResponseEntity.ok(mailService.getMailsProgrammes());
    }
}
