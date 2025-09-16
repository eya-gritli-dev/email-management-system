package com.example.email.Controller;

import com.example.email.Dto.CampaignDTO;
import com.example.email.Dto.ContactDTO;
import com.example.email.Dto.MailDTO;
import com.example.email.Entity.Campagne;
import com.example.email.Entity.Contact;
import com.example.email.Entity.Mail;
import com.example.email.Service.CampagneService;
import com.example.email.Service.ContactService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping("/api/campagnes")
public class CampagneController {

    @Autowired
    private CampagneService campagneService;

    @Autowired
    private ContactService contactService;

    // GET all campagnes (DTO)
    @GetMapping
    public List<CampaignDTO> getAll() {
        return campagneService.getAll(); // retourne déjà des DTO
    }

    // GET campagne par id (DTO)
    @GetMapping("/{id}")
    public ResponseEntity<CampaignDTO> getById(@PathVariable Long id) {
        CampaignDTO dto = campagneService.getById(id); // retourne déjà un DTO
        return ResponseEntity.ok(dto);
    }

    // CREATE campagne
    @PostMapping
    public Campagne create(@RequestBody Campagne campagne) {
        return campagneService.create(campagne);
    }

    // UPDATE campagne
    @PutMapping("/{id}")
    public ResponseEntity<Campagne> update(@PathVariable Long id, @RequestBody Campagne campagneDetails) {
        Campagne updated = campagneService.update(id, campagneDetails);
        return ResponseEntity.ok(updated);
    }

    // DELETE campagne
    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        campagneService.delete(id);
    }
}
