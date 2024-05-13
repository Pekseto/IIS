import { ComponentFixture, TestBed } from '@angular/core/testing';

import { RefereeTeamPickComponent } from './referee-team-pick.component';

describe('RefereeTeamPickComponent', () => {
  let component: RefereeTeamPickComponent;
  let fixture: ComponentFixture<RefereeTeamPickComponent>;

  beforeEach(() => {
    TestBed.configureTestingModule({
      declarations: [RefereeTeamPickComponent]
    });
    fixture = TestBed.createComponent(RefereeTeamPickComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
