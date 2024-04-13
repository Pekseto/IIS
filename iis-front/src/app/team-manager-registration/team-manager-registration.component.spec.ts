import { ComponentFixture, TestBed } from '@angular/core/testing';

import { TeamManagerRegistrationComponent } from './team-manager-registration.component';

describe('TeamManagerRegistrationComponent', () => {
  let component: TeamManagerRegistrationComponent;
  let fixture: ComponentFixture<TeamManagerRegistrationComponent>;

  beforeEach(() => {
    TestBed.configureTestingModule({
      declarations: [TeamManagerRegistrationComponent]
    });
    fixture = TestBed.createComponent(TeamManagerRegistrationComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
