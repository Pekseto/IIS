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
    }
  }

  onCitySelect(city: string) {
    if (this.activeCityIndex !== null) {
      this.regularSeasonSchedule.matches[this.activeCityIndex].homeTeam.city = city;
      this.activeCityIndex = null;
      this.newCity = '';
    }
  }

  onDateSelect(date: Date) {
    if (this.activeDateIndex !== null) {
      this.regularSeasonSchedule.matches[this.activeDateIndex].matchDay = date;
      this.activeDateIndex = null;
    }
  }

  addNewCity() {
    if (this.newCity) {
      if (this.activeCityIndex !== null) {
        this.regularSeasonSchedule.matches[this.activeCityIndex].homeTeam.city = this.newCity;
        this.activeCityIndex = null;
      }
    }
  }

  addNewDate() {
    if (this.newDate) {
      if (this.activeDateIndex !== null) {
        this.regularSeasonSchedule.matches[this.activeDateIndex].matchDay = this.newDate;
        this.activeCityIndex = null;
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
        if (exists == false) {
          this.scheduleService.saveRegSeasonSchedule(this.regularSeasonSchedule).subscribe(
            data => {
                alert('Raspored je uspešno sačuvan!');  // Dodavanje alert-a za uspešno sačuvanje
            },
            error => {
                console.error('Došlo je do greške prilikom čuvanja rasporeda:', error);
                alert('Došlo je do greške prilikom čuvanja rasporeda. Molimo pokušajte ponovo.');  // Alert za grešku
            }
        );
        } else {
          alert('Raspored za ovu sezonu je vec napravljen i sacuvan.');
        }
      },
      (error) => {
        console.error('Greška prilikom provere rasporeda:', error);
      }
    )
  }
}
