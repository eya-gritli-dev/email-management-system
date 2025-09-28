import { ComponentFixture, TestBed } from '@angular/core/testing';

import { AjouterClientCampagneComponent } from './ajouter-client-campagne.component';

describe('AjouterClientCampagneComponent', () => {
  let component: AjouterClientCampagneComponent;
  let fixture: ComponentFixture<AjouterClientCampagneComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [AjouterClientCampagneComponent]
    })
    .compileComponents();

    fixture = TestBed.createComponent(AjouterClientCampagneComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
