package com.example.email.Controller;

import com.example.email.Entity.Mail;
import com.example.email.Entity.ProgrammedMessage;
import com.example.email.Repository.MailRepository;
import com.example.email.Repository.ProgrammedMessageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.*;
import java.util.stream.Collectors;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/dashboard")
public class DashboardController {

    @Autowired
    private MailRepository mailRepository;

    @Autowired
    private ProgrammedMessageRepository programmedMessageRepository;

    @GetMapping("/stats")
    public Map<String, Object> getStats() {
        Map<String, Object> stats = new HashMap<>();

        List<Mail> allMails = mailRepository.findAll();
        List<ProgrammedMessage> allProgrammed = programmedMessageRepository.findAll();
        LocalDateTime maintenant = LocalDateTime.now();

        // ===== GLOBAL =====
        stats.put("totalMails", allMails.size());
        stats.put("totalProgrammed", allProgrammed.size());

        long envoyes = allMails.stream().filter(m -> "ENVOYE".equalsIgnoreCase(m.getStatut())).count();
        long echecs = allMails.stream().filter(m -> "ECHEC".equalsIgnoreCase(m.getStatut())).count();
        long enCours = allMails.stream().filter(m -> "EN_COURS".equalsIgnoreCase(m.getStatut())).count();
        long programmes = allMails.stream().filter(m -> "PROGRAMME".equalsIgnoreCase(m.getStatut())).count();

        stats.put("envoyes", envoyes);
        stats.put("echecs", echecs);
        stats.put("enCours", enCours);
        stats.put("programmes", programmes);

        // ===== ProgrammedMessage =====
        stats.put("programmesEnAttente",
                allProgrammed.stream().filter(p -> "EN_ATTENTE".equalsIgnoreCase(p.getStatut())).count());
        stats.put("programmesEnvoyes",
                allProgrammed.stream().filter(p -> "ENVOYE".equalsIgnoreCase(p.getStatut())).count());
        stats.put("programmesEchecs",
                allProgrammed.stream().filter(p -> "ECHEC".equalsIgnoreCase(p.getStatut())).count());

        // ===== Graphiques =====
        Map<String, Long> mailsParJour = allMails.stream()
                .filter(m -> m.getDateEnvoi() != null)
                .collect(Collectors.groupingBy(m -> m.getDateEnvoi().toLocalDate().toString(), Collectors.counting()));

        Map<String, Long> programmesParJour = allProgrammed.stream()
                .filter(p -> p.getDateProgramme() != null)
                .collect(Collectors.groupingBy(p -> p.getDateProgramme().toLocalDate().toString(), Collectors.counting()));

        stats.put("mailsParJour", mailsParJour);
        stats.put("programmesParJour", programmesParJour);

        // ===== Par campagne =====
        Map<String, Long> mailsParCampagne = allMails.stream()
                .filter(m -> m.getCampagne() != null)
                .collect(Collectors.groupingBy(m -> m.getCampagne().getNom(), Collectors.counting()));
        stats.put("mailsParCampagne", mailsParCampagne);

        // ===== Prochains envois =====
        List<Map<String, Object>> prochainsEnvois = allProgrammed.stream()
                .filter(p -> "EN_ATTENTE".equalsIgnoreCase(p.getStatut()) && p.getDateProgramme().isAfter(maintenant))
                .sorted(Comparator.comparing(ProgrammedMessage::getDateProgramme))
                .limit(5)
                .map(p -> {
                    Map<String, Object> envoi = new HashMap<>();
                    envoi.put("sujet", p.getMail().getSujet());
                    envoi.put("dateProgramme", p.getDateProgramme());
                    envoi.put("campagne", p.getMail().getCampagne().getNom());
                    return envoi;
                }).collect(Collectors.toList());
        stats.put("prochainsEnvois", prochainsEnvois);

        // ===== Retards =====
        long retards = allProgrammed.stream()
                .filter(p -> "EN_ATTENTE".equalsIgnoreCase(p.getStatut()) && p.getDateProgramme().isBefore(maintenant))
                .count();
        stats.put("retards", retards);

        // ===== Taux de succès =====
        double tauxSucces = allMails.size() > 0 ? (double) envoyes / allMails.size() * 100 : 0;
        stats.put("tauxSucces", Math.round(tauxSucces * 100.0) / 100.0);

        return stats;
    }
}
