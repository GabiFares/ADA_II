import { ComponentFixture, TestBed } from '@angular/core/testing';

import { HuespedListComponent } from './huesped-list.component';

describe('HuespedListComponent', () => {
  let component: HuespedListComponent;
  let fixture: ComponentFixture<HuespedListComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [HuespedListComponent]
    })
    .compileComponents();

    fixture = TestBed.createComponent(HuespedListComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
