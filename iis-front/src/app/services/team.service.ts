import { BehaviorSubject, Observable, tap } from "rxjs";
import { User } from "../model/user.model";
import { HttpClient } from "@angular/common/http";
import { environment } from "src/env/environment";
import { Injectable } from "@angular/core";
import { Team } from "../model/team.model";

@Injectable({
  providedIn: 'root'
})
export class TeamService {

  constructor(private http: HttpClient) { }

  register(team: Team): Observable<Team> {
    return this.http.post<Team>(environment.apiHost + 'teams/register', team);
  }

  getAll(): Observable<Team[]> {
    return this.http.get<Team[]>(environment.apiHost + 'teams/getAll');
  }
}
