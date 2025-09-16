package com.example.email.Controller;

import com.example.email.Entity.Contact;
import com.example.email.Service.ContactService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Set;

@RestController
@RequestMapping("/api/contacts")
@CrossOrigin(origins = "http://localhost:4200")
public class ContactController {

    @Autowired
    private ContactService contactService;

    // Récupérer tous les contacts
    @GetMapping
    public List<Contact> getAll() {
        return contactService.getAll();
    }

    // Ajouter un contact à une campagne
    @PostMapping("/campagne/{campagneId}")
    public Contact addContact(@PathVariable Long campagneId, @RequestBody Contact contact) {
        return contactService.ajouterContactALaCampagne(campagneId, contact);
    }

    // Récupérer les contacts d'une campagne
    @GetMapping("/campagne/{campagneId}")
    public Set<Contact> getByCampagne(@PathVariable Long campagneId) {
        return contactService.getContactsParCampagne(campagneId);
    }
    // UPDATE contact
    @PutMapping("/{id}")
    public Contact update(@PathVariable Long id, @RequestBody Contact contactDetails) {
        return contactService.update(id, contactDetails);
    }

    // Supprimer un contact
    @DeleteMapping("/{id}")

    public void delete(@PathVariable Long id) {
        contactService.delete(id);
    }
}
