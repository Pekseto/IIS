import { HttpClient } from "@angular/common/http";
import { Injectable } from "@angular/core";
import { Observable } from "rxjs";
import { environment } from "src/env/environment";

@Injectable({
    providedIn: 'root'
  })
export class MatchService {
    private apiController: String = 'match/';

    constructor(private http: HttpClient,) {}

    setRefereeTeam(refereeTeam: any): Observable<any> {
        return this.http.post(environment.apiHost + this.apiController + 'setRefereeTeam', refereeTeam);
    }

    getAll() : Observable<any> {
        return this.http.get(environment.apiHost + this.apiController + 'getAll');
    }
    
    getMatch(id: number) : Observable<any> {
        return this.http.get(environment.apiHost + this.apiController + 'getMatch/' + id)
    }
}