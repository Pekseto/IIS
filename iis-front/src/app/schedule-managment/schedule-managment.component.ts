import { Component } from '@angular/core';
import { RegularSeasonSchedule } from '../model/regular-season-schedule.model';
import { ScheduleManagment } from '../services/schedule-managment.service';
import { Router } from '@angular/router';

@Component({
  selector: 'app-schedule-managment',
  templateUrl: './schedule-managment.component.html',
  styleUrls: ['./schedule-managment.component.css']
})
export class ScheduleManagmentComponent {
  constructor(
    private scheduleService: ScheduleManagment,
    private router: Router
  ) { }

  regularSeasonSchedule!: RegularSeasonSchedule;
  showSchedule: boolean = false;

  ngOnInit(): void {
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
