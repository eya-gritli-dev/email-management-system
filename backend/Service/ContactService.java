package com.example.email.Service;

import com.example.email.Entity.Campagne;
import com.example.email.Entity.Contact;
import com.example.email.Repository.CampagneRepository;
import com.example.email.Repository.ContactRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
public class ContactService {

    @Autowired
    private ContactRepository contactRepository;

    @Autowired
    private CampagneRepository campagneRepository;

    public Contact ajouterContactALaCampagne(Long campagneId, Contact contact) {
        Campagne campagne = campagneRepository.findById(campagneId)
                .orElseThrow(() -> new RuntimeException("Campagne non trouvée"));

        // Initialisation du set de campagnes du contact si null
        if (contact.getCampagnes() == null) {
            contact.setCampagnes(new HashSet<>());
        }

        // Ajouter la campagne au contact
        contact.getCampagnes().add(campagne);

        // Initialisation du set de contacts de la campagne si null
        if (campagne.getContacts() == null) {
            campagne.setContacts(new HashSet<>());
        }

        // Ajouter le contact à la campagne
        campagne.getContacts().add(contact);

        return contactRepository.save(contact);
    }

    public Set<Contact> getContactsParCampagne(Long campagneId) {
        return contactRepository.findByCampagneId(campagneId);
    }

    public List<Contact> getAll() {
        return contactRepository.findAll();
    }

    @Transactional
    public void delete(Long contactId) {
        Contact contact = contactRepository.findById(contactId)
                .orElseThrow(() -> new RuntimeException("Contact not found"));

        // Supprime les liens avec les campagnes
        for (Campagne campagne : contact.getCampagnes()) {
            campagne.getContacts().remove(contact);
        }
        contact.getCampagnes().clear();

        contactRepository.delete(contact);
    }
    public Contact update(Long id, Contact contactDetails) {
        Contact contact = contactRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Contact introuvable"));
        contact.setNom(contactDetails.getNom());
        contact.setPrenom(contactDetails.getPrenom());
        contact.setEmail(contactDetails.getEmail());
        return contactRepository.save(contact);
    }

}
