package com.example.email.Service;

import com.example.email.Dto.MessageHistoriqueDTO;
import com.example.email.Entity.Mail;
import com.example.email.Entity.ProgrammedMessage;
import com.example.email.Repository.MailRepository;
import com.example.email.Repository.ProgrammedMessageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class HistoriqueService {

    @Autowired
    private ProgrammedMessageRepository programmedMessageRepository;

    @Autowired
    private MailRepository mailRepository;

    // Retourne tous les mails programmés ou envoyés sous forme de DTO
    public List<MessageHistoriqueDTO> getHistorique() {

        // On peut récupérer soit tous les mails programmés, soit tous les mails envoyés
        List<ProgrammedMessage> programmedMessages = programmedMessageRepository.findAll();
        List<Mail> mails = mailRepository.findByStatut("ENVOYE");

        // Fusionner les deux listes et transformer en DTO
        return programmedMessages.stream().map(pm -> {
            MessageHistoriqueDTO dto = new MessageHistoriqueDTO();
            dto.setId(pm.getId());
            dto.setSujet(pm.getMail().getSujet());
            dto.setContenu(pm.getMail().getMessage());
            dto.setStatut(pm.getStatut());
            dto.setNomFichier(pm.getMail().getFile() != null ? "fichier.pdf" : null);
            dto.setDateEnvoi(pm.getDateProgramme() != null ? pm.getDateProgramme().toString() : null);
            return dto;
        }).collect(Collectors.toList());
    }
}
