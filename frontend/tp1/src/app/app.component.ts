import { Component } from '@angular/core';
import { RouterOutlet } from '@angular/router';
import { SidebarComponent } from './components/sidebar/sidebar.component';
import { AjouterContactComponent } from './components/ajouter-contact/ajouter-contact.component';
import { EnvoyerEmailComponent } from './envoyer-email/envoyer-email.component';


@Component({
  selector: 'app-root',
  standalone: true,
  imports: [
    RouterOutlet,
    SidebarComponent,
    AjouterContactComponent,
    EnvoyerEmailComponent
  ],
  templateUrl: './app.component.html',
  styleUrl: './app.component.css'
})
export class AppComponent {}

