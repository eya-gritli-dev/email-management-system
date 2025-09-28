import { ComponentFixture, TestBed } from '@angular/core/testing';

import { MultipleEmailComponent } from './multiple-email.component';

describe('MultipleEmailComponent', () => {
  let component: MultipleEmailComponent;
  let fixture: ComponentFixture<MultipleEmailComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [MultipleEmailComponent]
    })
    .compileComponents();

    fixture = TestBed.createComponent(MultipleEmailComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
