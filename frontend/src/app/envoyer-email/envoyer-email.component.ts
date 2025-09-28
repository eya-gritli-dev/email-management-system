import { Component, OnInit } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { CommonModule } from '@angular/common';
import { FormsModule } from '@angular/forms';
import { ReactiveFormsModule } from '@angular/forms';

@Component({
  selector: 'app-envoyer-email',
  standalone: true,
  imports: [CommonModule, FormsModule, ReactiveFormsModule],
  templateUrl: './envoyer-email.component.html',
  styleUrls: ['./envoyer-email.component.css']
})
export class EnvoyerEmailComponent implements OnInit {

  campagnes: any[] = [];
  selectedCampagneId!: number;
  typeContenu: string = 'texte';   // texte | piece_jointe
  sendNow: boolean = true;
  selectedFile: File | null = null;
  email = {
    sujet: '',
    message: '',
    dateEnvoi: ''
  };
  successMsg = '';
  errorMsg = '';

  constructor(private http: HttpClient) {}

  ngOnInit() {
    this.loadCampagnes();
  }

  loadCampagnes() {
    this.http.get<any[]>('http://localhost:9090/api/campagnes')
      .subscribe({
        next: data => this.campagnes = data,
        error: err => console.error('Erreur chargement campagnes', err)
      });
  }

  onFileChange(event: any) {
    this.selectedFile = event.target.files[0];
  }

  onSubmit() {
    if (!this.selectedCampagneId) {
      this.errorMsg = "❌ Veuillez sélectionner une campagne.";
      return;
    }

    const formData = new FormData();
    formData.append('sujet', this.email.sujet);
    formData.append('contenu', this.email.message);
    formData.append('campagneId', this.selectedCampagneId.toString());
    formData.append('statut', this.sendNow ? "ENVOYE" : "PROGRAMME");

    if (!this.sendNow && this.email.dateEnvoi) {
      formData.append('dateEnvoi', this.email.dateEnvoi);
    }

    if (this.typeContenu === 'piece_jointe' && this.selectedFile) {
      formData.append('fichier', this.selectedFile);
    }

  this.http.post('http://localhost:9090/api/mails/envoyer', formData, { responseType: 'text' })
      .subscribe({
        next: (res: any) => {
          this.successMsg = "✅ Mail envoyé/programmé avec succès !";
          this.errorMsg = '';
          this.email = { sujet: '', message: '', dateEnvoi: '' };
          this.selectedFile = null;
        },
        error: err => {
          this.errorMsg = "❌ Erreur lors de l'envoi : " + err.message;
          this.successMsg = '';
          console.error(err);
        }
      });
  }
}
