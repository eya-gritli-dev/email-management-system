import { ComponentFixture, TestBed } from '@angular/core/testing';

import { ModifierCampagneComponent } from './modifier-campagne.component';

describe('ModifierCampagneComponent', () => {
  let component: ModifierCampagneComponent;
  let fixture: ComponentFixture<ModifierCampagneComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [ModifierCampagneComponent]
    })
    .compileComponents();

    fixture = TestBed.createComponent(ModifierCampagneComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
