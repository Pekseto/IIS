import { Component } from '@angular/core';
import { RegularSeasonSchedule } from '../model/regular-season-schedule.model';
import { ScheduleManagment } from '../services/schedule-managment.service';
import { Router } from '@angular/router';
import { TeamService } from '../services/team.service';
import { Team } from '../model/team.model';
import {ElementRef, ViewChild } from '@angular/core';

@Component({
  selector: 'app-schedule-managment',
  templateUrl: './schedule-managment.component.html',
  styleUrls: ['./schedule-managment.component.css']
})
export class ScheduleManagmentComponent {
  constructor(
    private scheduleService: ScheduleManagment,
    private router: Router,
    private teamService: TeamService
  ) { }

  regularSeasonSchedule!: RegularSeasonSchedule;
  showSchedule: boolean = false;
  teams: Team[] = [];
  cities: string[] = [];
  activeMatchIndex: number | null = null;
  activeTeamType: 'homeTeam' | 'awayTeam' | null = null;
  activeCityIndex: number | null = null;
  activeDateIndex: number | null = null;
  dropdownPosition = { top: '0px', left: '0px' };
  cityDropdownPosition = { top: '0px', left: '0px' };
  dateDropdownPosition = { top: '0px', left: '0px' };
  newCity = '';
  newDate!: Date;
  updating!: boolean;

  @ViewChild('dropdown', { static: false }) dropdown: ElementRef | undefined;
  @ViewChild('cityDropdown', { static: false }) cityDropdown: ElementRef | undefined;
  @ViewChild('cityDropdown', { static: false }) dateDropdown: ElementRef | undefined;

  ngOnInit(): void {

    this.teamService.getAll().subscribe(data =>{
      this.teams = data;
    })

    const now = new Date();
    this.scheduleService.checkIfScheduleExists(now.getFullYear()).subscribe(
      (exists: boolean) => {
        if (exists) {
          this.scheduleService.getThisYearSchedule(now.getFullYear()).subscribe(
            data => {
                this.regularSeasonSchedule = data;  
                this.showSchedule = true;
                this.regularSeasonSchedule.matches.forEach(match => {
                  if (Array.isArray(match.matchDay)) {
                    const [year, month, day, hour, minute] = match.matchDay;
                    match.matchDay = new Date(year, month - 1, day, hour, minute);
                  }
                  
                  if (!this.cities.includes(match.homeTeam.city)) {
                    this.cities.push(match.homeTeam.city);
                  }

                  
                  if (!this.cities.includes(match.awayTeam.city)) {
                    this.cities.push(match.awayTeam.city);
                  }
                });
            },
            error => {
                console.error('Došlo je do greške prilikom dobavljanja rasporeda:', error);
            }
        );
        }
      },
      (error) => {
        console.error('Greška prilikom provere rasporeda:', error);
      }
    )
  }

  onTeamClick(event: MouseEvent, matchIndex: number, teamType: 'homeTeam' | 'awayTeam') {
    if (!event.target) {
      return;
    }

    if (this.activeMatchIndex === matchIndex && this.activeTeamType === teamType) {
      // Ako je ponovo kliknuto na isti tim, zatvorite pop-up
      this.activeMatchIndex = null;
      this.activeTeamType = null;
    } else {
      this.activeMatchIndex = matchIndex;
      this.activeTeamType = teamType;

      const target = event.target as HTMLElement;
      const rect = target.getBoundingClientRect();
      this.dropdownPosition = {
        top: `${rect.bottom + window.scrollY}px`,
        left: `${rect.left + window.scrollX}px`
      };
    }
  }

  onCityClick(event: MouseEvent, matchIndex: number) {
    if (!event.target) {
      return;
    }

    if (this.activeCityIndex === matchIndex) {
      // Ako je ponovo kliknuto na isti grad, zatvorite pop-up
      this.activeCityIndex = null;
    } else {
      this.activeCityIndex = matchIndex;

      const target = event.target as HTMLElement;
      const rect = target.getBoundingClientRect();
      this.cityDropdownPosition = {
        top: `${rect.bottom + window.scrollY}px`,
        left: `${rect.left + window.scrollX}px`
      };
    }
  }

  onDateClick(event: MouseEvent, matchIndex: number) {
    if (!event.target) {
      return;
    }

    if (this.activeDateIndex === matchIndex) {
      this.activeDateIndex = null;
    } else {
      this.activeDateIndex = matchIndex;

      const target = event.target as HTMLElement;
      const rect = target.getBoundingClientRect();
      this.dateDropdownPosition = {
        top: `${rect.bottom + window.scrollY}px`,
        left: `${rect.left + window.scrollX}px`
      };
    }
  }

  onTeamSelect(team: any) {
    if (this.activeMatchIndex !== null && this.activeTeamType !== null) {
      this.regularSeasonSchedule.matches[this.activeMatchIndex][this.activeTeamType] = team;
      this.activeMatchIndex = null;
      this.activeTeamType = null;
      this.updating = true;
    }
  }

  onCitySelect(city: string) {
    if (this.activeCityIndex !== null) {
      this.regularSeasonSchedule.matches[this.activeCityIndex].homeTeam.city = city;
      this.activeCityIndex = null;
      this.newCity = '';
      this.updating = true;
    }
  }

  onDateSelect(date: Date) {
    if (this.activeDateIndex !== null) {
      this.regularSeasonSchedule.matches[this.activeDateIndex].matchDay = date;
      this.activeDateIndex = null;
      this.updating = true;
    }
  }

  addNewCity() {
    if (this.newCity) {
      if (this.activeCityIndex !== null) {
        this.regularSeasonSchedule.matches[this.activeCityIndex].homeTeam.city = this.newCity;
        this.activeCityIndex = null;
        this.updating = true;
      }
    }
  }

  addNewDate() {
    if (this.newDate) {
      if (this.activeDateIndex !== null) {
        this.regularSeasonSchedule.matches[this.activeDateIndex].matchDay = this.newDate;
        this.activeCityIndex = null;
        this.updating = true;
      }
    }
  }

  generateSchedule(): void {
    this.showSchedule = true;
    this.scheduleService.generateRegSeasonSchedule().subscribe(data => {
      this.regularSeasonSchedule = data;
      this.regularSeasonSchedule.matches.forEach(match => {
        if (Array.isArray(match.matchDay)) {
          const [year, month, day, hour, minute] = match.matchDay;
          match.matchDay = new Date(year, month - 1, day, hour, minute);
        }
      });
    })
  }

  saveSchedule(): void {
    this.showSchedule = true;
    const now = new Date();
    
    this.scheduleService.checkIfScheduleExists(now.getFullYear()).subscribe(
      (exists: boolean) => {
        if (!exists) {
          this.scheduleService.saveRegSeasonSchedule(this.regularSeasonSchedule).subscribe(
            data => {
              alert('The schedule has been successfully saved!');  // Alert for successful save
            },
            error => {
              console.error('Error occurred while saving the schedule:', error);
              if (error.status === 409) {
                alert(`Error occurred while saving the schedule: ${error.error}`);
              } else {
                alert('An error occurred while saving the schedule. Please try again.');
              }
            }
          );
        } else if (exists && this.updating) {
          this.scheduleService.saveRegSeasonSchedule(this.regularSeasonSchedule).subscribe(
            data => {
              alert('The schedule has been successfully updated!');  // Alert for successful update
            },
            error => {
              console.error('Error occurred while updating the schedule:', error);
              if (error.status === 409) {
                alert(`Error occurred while updating the schedule: ${error.error}`);
              } else {
                alert('An error occurred while updating the schedule. Please try again.');
              }
            }
          );
        } else {
          alert('The schedule for this season has already been created and saved.');
        }
      },
      error => {
        console.error('Error occurred while checking the schedule:', error);
        alert('An error occurred while checking the schedule. Please try again.');
      }
    );
  }
}
