import { ComponentFixture, TestBed } from '@angular/core/testing';

import { HuespedDetailComponent } from './huesped-detail.component';

describe('HuespedDetailComponent', () => {
  let component: HuespedDetailComponent;
  let fixture: ComponentFixture<HuespedDetailComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [HuespedDetailComponent]
    })
    .compileComponents();

    fixture = TestBed.createComponent(HuespedDetailComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
