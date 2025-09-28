import { Component, OnInit } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { CommonModule } from '@angular/common';
import { FormsModule } from '@angular/forms';

@Component({
  selector: 'app-ajouter-contact',
  standalone: true,
  imports: [CommonModule, FormsModule],
  templateUrl: './ajouter-contact.component.html',
  styleUrls: ['./ajouter-contact.component.css']
})
export class AjouterContactComponent implements OnInit {

  contacts: any[] = [];
  contactsFiltres: any[] = [];
  campagnes: any[] = [];
  newContact = { id: null, nom: '', prenom: '', email: '', campagneId: '' };
  message = '';
  isEditing = false;

  constructor(private http: HttpClient) {}

  ngOnInit() {
    this.loadCampagnes();
    this.loadContacts();
  }

  loadCampagnes() {
    this.http.get<any[]>('http://localhost:9090/api/campagnes')
      .subscribe({ next: data => this.campagnes = data });
  }

  loadContacts() {
    this.http.get<any[]>('http://localhost:9090/api/contacts')
      .subscribe({ next: data => this.contacts = data });
  }

  onCampagneChange() {
    if (!this.newContact.campagneId) {
      this.contactsFiltres = [];
      return;
    }

    // Charger seulement les contacts liés à la campagne sélectionnée
    this.http.get<any[]>(`http://localhost:9090/api/contacts/campagne/${this.newContact.campagneId}`)
      .subscribe({ next: data => this.contactsFiltres = data });
  }

  ajouterOuModifierContact() {
    if (!this.newContact.nom || !this.newContact.email || !this.newContact.campagneId) return;

    const headers = new HttpHeaders({ 'Content-Type': 'application/json' });

    if (this.isEditing) {
      // Mettre à jour le contact
      this.http.put(`http://localhost:9090/api/contacts/${this.newContact.id}`,
        JSON.stringify({
          nom: this.newContact.nom,
          prenom: this.newContact.prenom,
          email: this.newContact.email
        }),
        { headers }
      ).subscribe({
        next: () => {
          this.message = '✅ Contact mis à jour';
          this.resetForm();
          this.onCampagneChange(); // recharge les contacts de la campagne
        },
        error: err => console.error(err)
      });
    } else {
      // Ajouter un contact à la campagne
      this.http.post(`http://localhost:9090/api/contacts/campagne/${this.newContact.campagneId}`,
        JSON.stringify({
          nom: this.newContact.nom,
          prenom: this.newContact.prenom,
          email: this.newContact.email
        }),
        { headers }
      ).subscribe({
        next: () => {
          this.message = '✅ Contact ajouté';
          this.resetForm();
          this.onCampagneChange();
        },
        error: err => console.error(err)
      });
    }
  }

  editContact(contact: any) {
    this.newContact = { 
      id: contact.id, 
      nom: contact.nom, 
      prenom: contact.prenom, 
      email: contact.email,
      campagneId: this.newContact.campagneId // garde la campagne actuelle sélectionnée
    };
    this.isEditing = true;
  }

  supprimerContact(id: number) {
    this.http.delete(`http://localhost:9090/api/contacts/${id}`)
      .subscribe(() => this.onCampagneChange());
  }

  resetForm() {
    this.newContact = { id: null, nom: '', prenom: '', email: '', campagneId: '' };
    this.isEditing = false;
    this.contactsFiltres = [];
  }
}
