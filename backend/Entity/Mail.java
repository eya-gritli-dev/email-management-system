package com.example.email.Entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;

import java.time.LocalDateTime;

@Entity
public class Mail {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String sujet;
    private String message;
    private LocalDateTime dateEnvoi;
    private LocalDateTime dateProgramme;
    private String type;
    @Lob
    private byte[] file;
    private String statut;

    @ManyToOne
    @JoinColumn(name = "campagne_id")
    @JsonIgnore   // 🔥 empêche boucle Mail -> Campagne -> Mails -> Mail...
    private Campagne campagne;

    @OneToOne(mappedBy = "mail", cascade = CascadeType.ALL, orphanRemoval = true)
    private ProgrammedMessage messageProgramme;

    // getters/setters

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

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

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public byte[] getFile() {
        return file;
    }

    public void setFile(byte[] file) {
        this.file = file;
    }

    public String getStatut() {
        return statut;
    }

    public void setStatut(String statut) {
        this.statut = statut;
    }

    public Campagne getCampagne() {
        return campagne;
    }

    public void setCampagne(Campagne campagne) {
        this.campagne = campagne;
    }

    public ProgrammedMessage getMessageProgramme() {
        return messageProgramme;
    }

    public void setMessageProgramme(ProgrammedMessage messageProgramme) {
        this.messageProgramme = messageProgramme;
    }
}


