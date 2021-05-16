import { ComponentFixture, TestBed } from '@angular/core/testing';

import { PayementAffichageComponent } from './payement-affichage.component';

describe('PayementAffichageComponent', () => {
  let component: PayementAffichageComponent;
  let fixture: ComponentFixture<PayementAffichageComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ PayementAffichageComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(PayementAffichageComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
