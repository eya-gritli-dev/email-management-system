import { Component, OnInit } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { CommonModule } from '@angular/common';
import { FormsModule } from '@angular/forms';

@Component({
  selector: 'app-simple-email',
  templateUrl: './simple-email.component.html',
  styleUrls: ['./simple-email.component.css'],
  standalone: true,
  imports: [CommonModule, FormsModule]
})
export class SimpleEmailComponent implements OnInit {
  email = {
    to: '',
    subject: '',
    text: '',
    dateEnvoi: null
  };

  sendNow: boolean = true;
  successMsg = '';
  errorMsg = '';
  receivers: any[] = [];

  // Liste des messages envoyés ou en cours
  messagesEnvoyes: { to: string; subject: string; statut: string; date?: string | null }[] = [];

  constructor(private http: HttpClient) {}

  ngOnInit() {
    this.loadReceivers();
  }

  loadReceivers() {
    this.http.get<any[]>('http://localhost:9090/receivers')
      .subscribe({
        next: data => this.receivers = data,
        error: err => {
          console.error('Erreur lors du chargement des destinataires', err);
        }
      });
  }

 onSubmit() {
  const payload = {
    ...this.email,
    dateEnvoi: this.sendNow ? null : this.email.dateEnvoi
  };

  const statutInitial = this.sendNow ? 'Envoi en cours...' : 'Programmé';
  const messageAjoute = {
    to: this.email.to,
    subject: this.email.subject,
    statut: statutInitial,
    date: this.email.dateEnvoi
  };
  this.messagesEnvoyes.push(messageAjoute);

  this.http.post('http://localhost:9090/send-email', payload)
    .subscribe({
      next: () => {
        this.errorMsg = '';
        if (this.sendNow) {
          this.successMsg = "Email envoyé avec succès.";
          messageAjoute.statut = 'Envoyé';
        } else {
          this.successMsg = "Email programmé avec succès.";
          // Simuler le changement de statut après 2 minutes (120000 ms)
          const index = this.messagesEnvoyes.length - 1;
          setTimeout(() => {
            this.messagesEnvoyes[index].statut = 'Envoyé (après programmation)';
          }, 2 * 60 * 1000); // 2 minutes
        }
      },
      error: err => {
        this.errorMsg = "Échec de l'envoi.";
        this.successMsg = '';
        messageAjoute.statut = 'Erreur';
        console.error(err);
      }
    });
}}
