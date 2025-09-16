package com.example.email.Dto;

import org.springframework.web.multipart.MultipartFile;
import java.time.LocalDateTime;
import java.util.List;

public class MailRequestDTO {
    private String sujet;           // ✅ Correspond à votre frontend
    private String contenu;         // ✅ Correspond à votre frontend
    private LocalDateTime dateProgramme;
    private String statut;
    private String nomFichier;
    private Long campagneId;
    private MultipartFile fichier; // ✅ Correspond à votre frontend
    private Boolean sendNow = true; // Par défaut envoi immédiat
    private boolean toAll = false;
    private List<String> destinataires;

    // Constructeurs
    public MailRequestDTO() {}

    // Getters et Setters
    public String getSujet() {
        return sujet;
    }

    public void setSujet(String sujet) {
        this.sujet = sujet;
    }

    public String getContenu() {
        return contenu;
    }

    public void setContenu(String contenu) {
        this.contenu = contenu;
    }

    public LocalDateTime getDateProgramme() {
        return dateProgramme;
    }

    public void setDateProgramme(LocalDateTime dateProgramme) {
        this.dateProgramme = dateProgramme;
    }

    public String getStatut() {
        return statut;
    }

    public void setStatut(String statut) {
        this.statut = statut;
    }

    public String getNomFichier() {
        return nomFichier;
    }

    public void setNomFichier(String nomFichier) {
        this.nomFichier = nomFichier;
    }

    public Long getCampagneId() {
        return campagneId;
    }

    public void setCampagneId(Long campagneId) {
        this.campagneId = campagneId;
    }

    public MultipartFile getFichier() {
        return fichier;
    }

    public void setFichier(MultipartFile fichier) {
        this.fichier = fichier;
    }

    public Boolean getSendNow() {
        return sendNow;
    }

    public void setSendNow(Boolean sendNow) {
        this.sendNow = sendNow;
    }

    public boolean isToAll() {
        return toAll;
    }

    public void setToAll(boolean toAll) {
        this.toAll = toAll;
    }

    public List<String> getDestinataires() {
        return destinataires;
    }

    public void setDestinataires(List<String> destinataires) {
        this.destinataires = destinataires;
    }

    // ✅ Méthodes d'adaptation pour votre EmailService existant
    public String getSubject() {
        return this.sujet;
    }

    public String getText() {
        return this.contenu;
    }

    public MultipartFile getFile() {
        return this.fichier;
    }
}