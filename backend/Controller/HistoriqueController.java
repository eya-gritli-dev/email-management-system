package com.example.email.Controller;

import com.example.email.Dto.MessageHistoriqueDTO;
import com.example.email.Service.HistoriqueService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping("/historique")
public class HistoriqueController {

    @Autowired
    private HistoriqueService service;

    @GetMapping
    public List<MessageHistoriqueDTO> getHistorique() {
        return service.getHistorique();
    }
}

