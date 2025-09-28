import { Component } from '@angular/core';
import { CommonModule } from '@angular/common';
import { FormsModule } from '@angular/forms';
import { HttpClient } from '@angular/common/http';

@Component({
  selector: 'app-multiple-email',
  standalone: true,
  imports: [CommonModule, FormsModule],
  templateUrl: './multiple-email.component.html',
  styleUrls: ['./multiple-email.component.css']
})
export class MultipleEmailComponent {
  email = {
    subject: '',
    text: ''
  };
  successMsg = '';
  errorMsg = '';

  constructor(private http: HttpClient) {}

  onSubmit() {
    this.successMsg = '';
    this.errorMsg = '';
   this.http.post<any>('http://localhost:9090/send-to-all', this.email)
  .subscribe({
    next: (res) => {
      this.successMsg = res.message || '✅ Email envoyé avec succès !✔️';
    },
    error: () => {
      this.errorMsg = '❌ Erreur lors de l’envoi.';
    }
  });

  }
}
