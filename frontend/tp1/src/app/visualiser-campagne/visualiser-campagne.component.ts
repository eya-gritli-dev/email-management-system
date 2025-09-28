// visualiser-campagne.component.ts
import { Component, OnInit } from '@angular/core';
import { CommonModule } from '@angular/common';
import { ModifierCampagneComponent } from '../modifier-campagne/modifier-campagne.component';
import { HttpClient } from '@angular/common/http';
import { CampaignDTO } from './campaign-dto.model';

@Component({
  selector: 'app-visualiser-campagne',
  standalone: true,
  imports: [CommonModule, ModifierCampagneComponent],
  templateUrl: './visualiser-campagne.component.html',
  styleUrls: ['./visualiser-campagne.component.css']
})
export class VisualiserCampagneComponent implements OnInit {

  campagnes: CampaignDTO[] = [];
  campagneAModifier: CampaignDTO | null = null;

  constructor(private http: HttpClient) {}

  ngOnInit() {
    this.chargerCampagnes();
  }

  chargerCampagnes() {
    this.http.get<CampaignDTO[]>('http://localhost:9090/api/campagnes')
      .subscribe({
        next: (data) => {
          this.campagnes = data;
        },
        error: (err) => {
          console.error("Erreur lors du chargement des campagnes :", err);
        }
      });
  }

  afficherModification(campagne: CampaignDTO) {
    this.campagneAModifier = campagne;
  }

  supprimerCampagne(id: number) {
    if (!confirm('Confirmer la suppression de cette campagne ?')) return;

    this.http.delete(`http://localhost:9090/api/campagnes/${id}`)
      .subscribe({
        next: () => {
          alert('Campagne supprimée avec succès !');
          this.chargerCampagnes();
        },
        error: (err) => {
          alert('Erreur lors de la suppression : ' + err.message);
        }
      });
  }

  fermerModification(rechargerListe: boolean = false) {
    this.campagneAModifier = null;
    if (rechargerListe) {
      this.chargerCampagnes();
    }
  }
}
