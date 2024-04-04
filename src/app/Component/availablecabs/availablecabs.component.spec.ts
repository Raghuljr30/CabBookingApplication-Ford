import { ComponentFixture, TestBed } from '@angular/core/testing';

import { AvailablecabsComponent } from './availablecabs.component';

describe('AvailablecabsComponent', () => {
  let component: AvailablecabsComponent;
  let fixture: ComponentFixture<AvailablecabsComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [AvailablecabsComponent]
    })
    .compileComponents();
    
    fixture = TestBed.createComponent(AvailablecabsComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
