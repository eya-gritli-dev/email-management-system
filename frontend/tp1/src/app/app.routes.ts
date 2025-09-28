import { Routes } from '@angular/router';
import { AjouterContactComponent } from './components/ajouter-contact/ajouter-contact.component';
import { EnvoyerEmailComponent } from './envoyer-email/envoyer-email.component';
import { HistoriqueComponent } from './components/historique/historique.component';
import { DashboardComponent } from './components/dashboard/dashboard.component';
import { AjouterMailCampagneComponent } from './ajouter-mail-campagne/ajouter-mail-campagne.component';
import { AjouterClientCampagneComponent } from './ajouter-client-campagne/ajouter-client-campagne.component';
import { AjouterCampagneComponent } from './ajouter-campagne/ajouter-campagne.component';
import { VisualiserCampagneComponent } from './visualiser-campagne/visualiser-campagne.component';
import { ModifierCampagneComponent } from './modifier-campagne/modifier-campagne.component';
export const routes: Routes = [
  { path: 'ajouter-contact', component: AjouterContactComponent },
  { path: '', redirectTo: 'ajouter-contact', pathMatch: 'full' },
  { path: 'envoyer-email', component: EnvoyerEmailComponent },
    { path: 'historique', component: HistoriqueComponent },
     { path: 'dashboard', component: DashboardComponent },
     {path:'ajouter-mail-campagne',component: AjouterMailCampagneComponent},
     {path: 'ajouter-client-campagne', component: AjouterClientCampagneComponent},
     {path:'ajouter-campagne', component: AjouterCampagneComponent },
     {path: 'visualiser-campagne',component: VisualiserCampagneComponent},
     {path:'modifier-campagne',component: ModifierCampagneComponent}

];
