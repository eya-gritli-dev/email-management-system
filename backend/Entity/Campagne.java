package com.example.email.Entity;

import jakarta.persistence.*;
import java.util.Set;

@Entity
public class Campagne {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nom;
    private String description;

    @ManyToMany
    @JoinTable(
            name = "campagne_contact",
            joinColumns = @JoinColumn(name = "campagne_id"),
            inverseJoinColumns = @JoinColumn(name = "contact_id")
    )
    private Set<Contact> contacts;

    @OneToMany(mappedBy = "campagne", cascade = CascadeType.ALL)
    private Set<Mail> mails;

    // Getters et setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getNom() { return nom; }
    public void setNom(String nom) { this.nom = nom; }

    public String getDescription() { return description; }
    public void setDescription(String description) { this.description = description; }

    public Set<Contact> getContacts() { return contacts; }
    public void setContacts(Set<Contact> contacts) { this.contacts = contacts; }

    public Set<Mail> getMails() { return mails; }
    public void setMails(Set<Mail> mails) { this.mails = mails; }
}
