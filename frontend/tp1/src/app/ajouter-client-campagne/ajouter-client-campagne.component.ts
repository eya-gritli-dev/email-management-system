
import { Component } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { FormBuilder, FormGroup } from '@angular/forms';
import { CommonModule } from '@angular/common';
import { ReactiveFormsModule } from '@angular/forms';

@Component({
  standalone: true,
  selector: 'app-ajouter-client',
  imports: [CommonModule, ReactiveFormsModule],
  templateUrl: './ajouter-client-campagne.component.html',
  styleUrls: ['./ajouter-client-campagne.component.css']
})






export class AjouterClientCampagneComponent {

   form: FormGroup;
  message = '';

  constructor(private fb: FormBuilder, private http: HttpClient) {
    this.form = this.fb.group({
      nom: [''],
      prenom: [''],
      email: [''],
      campagneId: ['']
    });
  }

  submit() {
    const { campagneId, ...client } = this.form.value;
    this.http.post(`http://localhost:9090/api/clients/campagne/${campagneId}`, client)
      .subscribe(() => this.message = '✅ Client ajouté à la campagne');
  }
}
