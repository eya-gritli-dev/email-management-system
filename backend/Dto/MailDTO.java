package com.example.email.Dto;

import java.time.LocalDateTime;

public class MailDTO {
    private String sujet;
    private String message;
    private LocalDateTime dateEnvoi;
    private LocalDateTime dateProgramme;
    private String statut;
    private String type;

    public String getSujet() {
        return sujet;
    }

    public void setSujet(String sujet) {
        this.sujet = sujet;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public LocalDateTime getDateEnvoi() {
        return dateEnvoi;
    }

    public void setDateEnvoi(LocalDateTime dateEnvoi) {
        this.dateEnvoi = dateEnvoi;
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

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
    // getters/setters

}