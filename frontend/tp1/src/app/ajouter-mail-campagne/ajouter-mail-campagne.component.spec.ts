import { ComponentFixture, TestBed } from '@angular/core/testing';

import { AjouterMailCampagneComponent } from './ajouter-mail-campagne.component';

describe('AjouterMailCampagneComponent', () => {
  let component: AjouterMailCampagneComponent;
  let fixture: ComponentFixture<AjouterMailCampagneComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [AjouterMailCampagneComponent]
    })
    .compileComponents();

    fixture = TestBed.createComponent(AjouterMailCampagneComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
