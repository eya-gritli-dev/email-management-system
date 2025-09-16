package com.example.email.Service;

import com.example.email.Dto.CampaignDTO;
import com.example.email.Dto.MailDTO;
import com.example.email.Entity.Campagne;
import com.example.email.Entity.Contact;
import com.example.email.Entity.Mail;
import com.example.email.Repository.CampagneRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

@Service
public class CampagneService {

    @Autowired
    private CampagneRepository campagneRepository;

    // Retourne toutes les campagnes en DTO
    public List<CampaignDTO> getAll() {
        return campagneRepository.findAll().stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    public CampaignDTO getById(Long id) {
        Campagne campagne = campagneRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Campagne non trouvée"));
        return convertToDTO(campagne);
    }

    public Campagne create(Campagne campagne) {
        // Associer correctement les contacts et mails
        if(campagne.getContacts() != null){
            campagne.getContacts().forEach(c -> c.getCampagnes().add(campagne));
        }
        if(campagne.getMails() != null){
            campagne.getMails().forEach(m -> m.setCampagne(campagne));
        }
        return campagneRepository.save(campagne);
    }

    public Campagne update(Long id, Campagne campagneDetails) {
        Campagne campagne = campagneRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Campagne non trouvée"));

        campagne.setNom(campagneDetails.getNom());
        campagne.setDescription(campagneDetails.getDescription());

        // Contacts
        campagne.getContacts().clear();
        if (campagneDetails.getContacts() != null) {
            campagneDetails.getContacts().forEach(c -> {
                c.getCampagnes().add(campagne);
                campagne.getContacts().add(c);
            });
        }

        // Mails
        campagne.getMails().clear();
        if (campagneDetails.getMails() != null) {
            campagneDetails.getMails().forEach(m -> {
                m.setCampagne(campagne);
                campagne.getMails().add(m);
            });
        }

        return campagneRepository.save(campagne);
    }

    public void delete(Long id) {
        campagneRepository.deleteById(id);
    }

    // ===== Conversion vers DTO =====
    private CampaignDTO convertToDTO(Campagne campagne) {
        CampaignDTO dto = new CampaignDTO();
        dto.setId(campagne.getId());
        dto.setNom(campagne.getNom());
        dto.setDescription(campagne.getDescription());

        if(campagne.getContacts() != null){
            dto.setContactsEmails(
                    campagne.getContacts().stream()
                            .map(Contact::getEmail)
                            .collect(Collectors.toSet())
            );
        }

        if(campagne.getMails() != null){
            dto.setMails(
                    campagne.getMails().stream()
                            .map(this::convertMailToDTO)
                            .collect(Collectors.toSet())
            );
        }

        return dto;
    }

    private MailDTO convertMailToDTO(Mail mail) {
        MailDTO dto = new MailDTO();
        dto.setSujet(mail.getSujet());
        dto.setMessage(mail.getMessage());
        dto.setDateEnvoi(mail.getDateEnvoi());
        dto.setDateProgramme(mail.getDateProgramme());
        dto.setStatut(mail.getStatut());
        dto.setType(mail.getType());
        return dto;
    }
}
