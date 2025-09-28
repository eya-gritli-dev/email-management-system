import { ComponentFixture, TestBed } from '@angular/core/testing';

import { VisualiserCampagneComponent } from './visualiser-campagne.component';

describe('VisualiserCampagneComponent', () => {
  let component: VisualiserCampagneComponent;
  let fixture: ComponentFixture<VisualiserCampagneComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [VisualiserCampagneComponent]
    })
    .compileComponents();

    fixture = TestBed.createComponent(VisualiserCampagneComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
