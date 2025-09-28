import { Component } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { CommonModule } from '@angular/common';
import { ReactiveFormsModule } from '@angular/forms';

@Component({
  standalone: true,
  selector: 'app-ajouter-mail',
  imports: [CommonModule, ReactiveFormsModule],
  templateUrl: './ajouter-mail-campagne.component.html',
  styleUrls: ['./ajouter-mail-campagne.component.css']
})
export class AjouterMailCampagneComponent {
  form: FormGroup;
  fichier: File | null = null;
  message = '';
  isLoading = false;

  constructor(private fb: FormBuilder, private http: HttpClient) {
    this.form = this.fb.group({
      sujet: ['', Validators.required],
      contenu: ['', Validators.required],
      dateProgramme: [''],
      statut: ['PROGRAMME', Validators.required],
      nomFichier: [''],
      campagneId: ['', Validators.required]  // garde comme string ici
    });
  }

  onFileChange(event: any) {
    this.fichier = event.target.files[0];
    if (this.fichier) {
      console.log('✅ Fichier sélectionné:', this.fichier.name);
    }
  }

  submit() {
    if (this.form.invalid) {
      this.message = '❌ Veuillez remplir tous les champs obligatoires.';
      return;
    }

    this.isLoading = true;
    this.message = '';

    const formData = new FormData();
    formData.append('sujet', this.form.value.sujet);
    formData.append('contenu', this.form.value.contenu);
    formData.append('dateProgramme', this.form.value.dateProgramme || '');
    formData.append('statut', this.form.value.statut);
    formData.append('nomFichier', this.form.value.nomFichier || '');

    // Attention ici : conversion string => string mais API attend un Long en backend
    formData.append('campagneId', this.form.value.campagneId.toString());

    if (this.fichier) {
      formData.append('fichier', this.fichier);
    }

    this.http.post('http://localhost:9090/api/mails/envoyer', formData).subscribe({
      next: (response: any) => {
        this.isLoading = false;
        this.message = '✅ Mail programmé avec succès.';
        this.form.reset();
        this.fichier = null;
      },
      error: err => {
        this.isLoading = false;
        console.error('❌ Erreur complète:', err);
        this.message = '❌ Erreur lors de la création du mail.';
      }
    });
  }
}
