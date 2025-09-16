package com.example.email.Entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
public class ProgrammedMessage {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private LocalDateTime dateProgramme;
    @Lob
    private byte[] file;
    private String statut;

    @OneToOne
    @JoinColumn(name = "mail_id")
    @JsonIgnore
    private Mail mail;

    // getters/setters






    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public LocalDateTime getDateProgramme() {
        return dateProgramme;
    }

    public void setDateProgramme(LocalDateTime dateProgramme) {
        this.dateProgramme = dateProgramme;
    }

    public Mail getMail() {
        return mail;
    }

    public void setMail(Mail mail) {
        this.mail = mail;
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
}


