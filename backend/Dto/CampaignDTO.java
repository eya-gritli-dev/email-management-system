package com.example.email.Dto;

import com.example.email.Dto.MailDTO;

import java.util.Set;

public class CampaignDTO {
    private Long id;
    private String nom;
    private String description;
    private Set<String> contactsEmails; // juste les emails des contacts
    private Set<MailDTO> mails;
    // getters/setters

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Set<String> getContactsEmails() {
        return contactsEmails;
    }

    public void setContactsEmails(Set<String> contactsEmails) {
        this.contactsEmails = contactsEmails;
    }

    public Set<MailDTO> getMails() {
        return mails;
    }

    public void setMails(Set<MailDTO> mails) {
        this.mails = mails;
    }
}