import { Component, OnInit } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { CommonModule } from '@angular/common';
import { FormsModule } from '@angular/forms';

@Component({
  selector: 'app-ajouter-campagne',
  standalone: true,
  imports: [CommonModule, FormsModule],
  templateUrl: './ajouter-campagne.component.html',
  styleUrls: ['./ajouter-campagne.component.css']
})
export class AjouterCampagneComponent implements OnInit {
  campagnes: any[] = [];
  newCampagne = { nom: '', description: '' };
  message = '';

  constructor(private http: HttpClient) {}

  ngOnInit() {
    this.loadCampagnes();
  }

  loadCampagnes() {
    this.http.get<any[]>('http://localhost:9090/api/campagnes')
      .subscribe({ next: data => this.campagnes = data });
  }

  ajouterCampagne() {
    if (!this.newCampagne.nom) return;
    this.http.post('http://localhost:9090/api/campagnes', this.newCampagne)
      .subscribe({
        next: () => {
          this.message = '✅ Campagne créée';
          this.newCampagne = { nom: '', description: '' };
          this.loadCampagnes();
        },
        error: err => console.error(err)
      });
  }

  supprimerCampagne(id: number) {
    this.http.delete(`http://localhost:9090/api/campagnes/${id}`)
      .subscribe(() => this.loadCampagnes());
  }
}
