import { BehaviorSubject, Observable, tap } from "rxjs";
import { User } from "../model/user.model";
import { HttpClient } from "@angular/common/http";
import { environment } from "src/env/environment";
import { Injectable } from "@angular/core";
import { RegularSeasonSchedule } from "../model/regular-season-schedule.model";

@Injectable({
    providedIn: 'root'
  })
  export class ScheduleManagment {
  
    constructor(private http: HttpClient) { }
  
    generateRegSeasonSchedule(): Observable<RegularSeasonSchedule> {
        return this.http.post<RegularSeasonSchedule>(environment.apiHost + 'schedule/generate',null );
    }

    saveRegSeasonSchedule(schedule: RegularSeasonSchedule): Observable<RegularSeasonSchedule> {
      return this.http.post<RegularSeasonSchedule>(environment.apiHost + 'schedule/save',schedule);
    }

    checkIfScheduleExists(year: Number): Observable<boolean> {
      return this.http.get<boolean>(environment.apiHost + 'schedule/exists/' + year);
    }

    getThisYearSchedule(year: Number): Observable<RegularSeasonSchedule> {
      return this.http.get<RegularSeasonSchedule>(environment.apiHost + 'schedule/get-this-year-schedule/' + year);
    }
}