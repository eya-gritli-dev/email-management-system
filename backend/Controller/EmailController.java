/*package com.example.email.Controller;

import com.example.email.Dto.EmailRequestDTO;
import com.example.email.Dto.ApiResponse;
import com.example.email.Service.ProgrammedMessageService;
import jakarta.mail.MessagingException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
public class EmailController {

    @Autowired
    private EmailService emailService;

    @Autowired
    private ProgrammedMessageService programmedMessageService;

    @PostMapping("/envoyer")
    public ResponseEntity<ApiResponse> envoyerEmail(@ModelAttribute EmailRequestDTO email) {
        try {
            boolean withAttachment = email.getFile() != null && !email.getFile().isEmpty();
            boolean sendNow = Boolean.TRUE.equals(email.getSendNow());

            if (sendNow) {
                if (email.isToAll()) {
                    // ✅ À tous les contacts
                    if (withAttachment) {
                        emailService.sendEmailWithAttachmentToAll(email.getSubject(), email.getText(), email.getFile());
                    } else {
                        emailService.sendEmailToAllReceivers(email.getSubject(), email.getText());
                    }

                } else {
                    // ✅ À destinataires spécifiques
                    if (withAttachment) {
                        byte[] fileBytes = email.getFile().getBytes();
                        String fileName = email.getFile().getOriginalFilename();

                        for (String to : email.getDestinataires()) {
                            emailService.envoyerEmailAvecPieceJointe(to, email.getSubject(), email.getText(), fileBytes, fileName);
                        }

                    } else {
                        for (String to : email.getDestinataires()) {
                            emailService.envoyerEmail(to, email.getSubject(), email.getText());
                        }
                    }
                }

                return ResponseEntity.ok(new ApiResponse(true, "✅ Email(s) envoyé(s) avec succès."));

            } else {
                // ⏳ Envoi programmé
                if (email.isToAll()) {
                    programmedMessageService.planifierEnvoiPourTous(email);
                } else {
                    for (String to : email.getDestinataires()) {
                        programmedMessageService.planifierEnvoi(email, to);
                    }
                }

                return ResponseEntity.ok(new ApiResponse(true, "⏳ Email(s) programmé(s)."));
            }

        } catch (MessagingException | IOException e) {
            e.printStackTrace();
            return ResponseEntity.status(500).body(
                    new ApiResponse(false, "❌ Erreur lors de l'envoi : " + e.getMessage())
            );
        }
    }
}*/