import { ComponentFixture, TestBed } from '@angular/core/testing';

import { AttachmentEmailComponent } from './attachment-email.component';

describe('AttachmentEmailComponent', () => {
  let component: AttachmentEmailComponent;
  let fixture: ComponentFixture<AttachmentEmailComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [AttachmentEmailComponent]
    })
    .compileComponents();

    fixture = TestBed.createComponent(AttachmentEmailComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
