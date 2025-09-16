/*package com.example.email.Controller;

import com.example.email.Dto.EmailAttachemnetDTO;
import com.example.email.Service.EmailService;
import jakarta.mail.MessagingException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

@RestController
public class emailAttachementController {
    @Autowired
    private EmailService emailService;

    @PostMapping("/attachment")
    public ResponseEntity<Map<String, String>> sendAttachement(
            @RequestParam("to") String to,
            @RequestParam("subject") String subject,
            @RequestParam("text") String text,
            @RequestParam("file") MultipartFile file) {

        try {
            System.out.println("Début envoi email avec pièce jointe");
            System.out.println("To: " + to);
            System.out.println("Subject: " + subject);
            System.out.println("File: " + (file != null ? file.getOriginalFilename() : "null"));

            emailService.envoyerEmailAvecPieceJointe(to, subject, text, MultipartFile);

            Map<String, String> response = new HashMap<>();
            response.put("message", "Email envoyé avec succès !");
            return ResponseEntity.ok(response);

        } catch (MessagingException e) {
            System.err.println("Erreur MessagingException: " + e.getMessage());
            e.printStackTrace();
            Map<String, String> errorResponse = new HashMap<>();
            errorResponse.put("error", "Erreur lors de l'envoi de l'email: " + e.getMessage());
            return ResponseEntity.status(500).body(errorResponse);

        } catch (IOException e) {
            System.err.println("Erreur IOException: " + e.getMessage());
            e.printStackTrace();
            Map<String, String> errorResponse = new HashMap<>();
            errorResponse.put("error", "Erreur lors du traitement du fichier: " + e.getMessage());
            return ResponseEntity.status(500).body(errorResponse);

        } catch (Exception e) {
            System.err.println("Erreur générale: " + e.getMessage());
            e.printStackTrace();
            Map<String, String> errorResponse = new HashMap<>();
            errorResponse.put("error", "Erreur inattendue: " + e.getMessage());
            return ResponseEntity.status(500).body(errorResponse);
        }
    }
*/