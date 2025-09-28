import { Component, Input, OnChanges, Output, EventEmitter, SimpleChanges } from '@angular/core';
import { CommonModule } from '@angular/common';
import { FormsModule } from '@angular/forms';
import { CampagneService } from './campagne.service';

@Component({
  selector: 'app-modifier-campagne',
  standalone: true,
  imports: [CommonModule, FormsModule],
  templateUrl: './modifier-campagne.component.html'
})
export class ModifierCampagneComponent implements OnChanges {
  @Input() campagne!: any;
  @Output() fermer = new EventEmitter<boolean>();

  clients: any[] = [];
  mails: any[] = [];

  constructor(private campagneService: CampagneService) {}

  ngOnChanges(changes: SimpleChanges): void {
    if (changes['campagne'] && this.campagne) {
      this.clients = this.campagne.clients || [];
      this.mails = this.campagne.mails || [];
    }
  }

  saveCampagne() {
    // Vérification rapide que l'id existe bien
    if (!this.campagne?.id) {
      alert('Erreur : campagne invalide (id manquant)');
      return;
    }

    this.campagneService.updateCampagne(this.campagne.id, this.campagne).subscribe({
      next: () => {
        alert('✅ Campagne mise à jour');
        this.fermer.emit(true);
      },
      error: (error) => {
        alert('Erreur lors de la mise à jour : ' + error.message);
      }
    });
  }

  updateClient(client: any) {
    // TODO: implémenter mise à jour client si nécessaire
  }

  deleteClient(id: number) {
    // TODO: implémenter suppression client si nécessaire
  }

  updateMail(mail: any) {
    // TODO: implémenter mise à jour mail si nécessaire
  }
}
