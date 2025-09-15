import { ComponentFixture, TestBed } from '@angular/core/testing';

import { HuespedFormComponent } from './huesped-form.component';

describe('HuespedFormComponent', () => {
  let component: HuespedFormComponent;
  let fixture: ComponentFixture<HuespedFormComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [HuespedFormComponent]
    })
    .compileComponents();

    fixture = TestBed.createComponent(HuespedFormComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
