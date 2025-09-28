import { Component, ViewEncapsulation } from '@angular/core';

@Component({
  selector: 'app-sidebar',
  standalone: true,
  templateUrl: './sidebar.component.html',
  styleUrls: ['./sidebar.component.css'],
  encapsulation: ViewEncapsulation.None // si vous voulez que le style soit global
})
export class SidebarComponent {
  onLinkClick(linkName: string) {
    console.log(`${linkName} a été cliqué`);
    // Vous pouvez également exécuter d'autres actions ici
  }
}
