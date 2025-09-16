package com.example.email.Dto;

import org.springframework.web.multipart.MultipartFile;

import java.time.LocalDateTime;
import java.util.List;

public class EmailRequestDTO {
    private List<String> destinataires; // null si toAll = true
    private boolean toAll;
    private String subject;
    private String text;
    private Boolean sendNow;
    private LocalDateTime dateEnvoi;

    private MultipartFile file; // null si pas de pièce jointe

    // Getters & Setters
    public List<String> getDestinataires() { return destinataires; }
    public void setDestinataires(List<String> destinataires) { this.destinataires = destinataires; }

    public boolean isToAll() { return toAll; }
    public void setToAll(boolean toAll) { this.toAll = toAll; }

    public String getSubject() { return subject; }
    public void setSubject(String subject) { this.subject = subject; }

    public String getText() { return text; }
    public void setText(String text) { this.text = text; }

    public Boolean getSendNow() { return sendNow; }
    public void setSendNow(Boolean sendNow) { this.sendNow = sendNow; }

    public LocalDateTime getDateEnvoi() { return dateEnvoi; }
    public void setDateEnvoi(LocalDateTime dateEnvoi) { this.dateEnvoi = dateEnvoi; }

    public MultipartFile getFile() { return file; }
    public void setFile(MultipartFile file) { this.file = file; }
}
