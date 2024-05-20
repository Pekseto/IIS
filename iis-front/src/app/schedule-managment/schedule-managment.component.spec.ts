import { ComponentFixture, TestBed } from '@angular/core/testing';

import { ScheduleManagmentComponent } from './schedule-managment.component';

describe('ScheduleManagmentComponent', () => {
  let component: ScheduleManagmentComponent;
  let fixture: ComponentFixture<ScheduleManagmentComponent>;

  beforeEach(() => {
    TestBed.configureTestingModule({
      declarations: [ScheduleManagmentComponent]
    });
    fixture = TestBed.createComponent(ScheduleManagmentComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
