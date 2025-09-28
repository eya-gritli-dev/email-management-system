import { Component } from '@angular/core';
import { CommonModule } from '@angular/common';
import { FormsModule } from '@angular/forms';
import { HttpClient } from '@angular/common/http';

@Component({
  selector: 'app-attachment-email',
  standalone: true,
  imports: [CommonModule, FormsModule],
  templateUrl: './attachment-email.component.html',
  styleUrls: ['./attachment-email.component.css']
})
export class AttachmentEmailComponent {
  email = {
    to: '',
    subject: '',
    text: ''
  };
  selectedFile: File | null = null;
  successMsg = '';
  errorMsg = '';

  constructor(private http: HttpClient) {}

  onFileChange(event: any) {
    this.selectedFile = event.target.files[0];
  }

  onSubmit() {
  if (!this.selectedFile) {
    this.errorMsg = '📎 Veuillez choisir un fichier.';
    return;
  }

  const formData = new FormData();
  formData.append('to', this.email.to);
  formData.append('subject', this.email.subject);
  formData.append('text', this.email.text);
  formData.append('file', this.selectedFile);

  // Correction : envoyer formData au lieu de this.email
  this.http.post<any>('http://localhost:9090/attachment', formData)
    .subscribe({
      next: (res) => {
        this.successMsg = res.message || '✅ Email envoyé avec succès !✔️';
        this.errorMsg = '';
      },
      error: (err) => {
        console.error('Erreur:', err);
        this.errorMsg = '❌ Erreur lors de l\'envoi.';
        this.successMsg = '';
      }
    });
}}