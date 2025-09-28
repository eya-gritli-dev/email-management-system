import { ComponentFixture, TestBed } from '@angular/core/testing';

import { SimpleEmailComponent } from './simple-email.component';

describe('SimpleEmailComponent', () => {
  let component: SimpleEmailComponent;
  let fixture: ComponentFixture<SimpleEmailComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [SimpleEmailComponent]
    })
    .compileComponents();

    fixture = TestBed.createComponent(SimpleEmailComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
