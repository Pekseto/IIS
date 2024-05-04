import { Observable } from "rxjs";
import { HttpClient } from "@angular/common/http";
import { environment } from "src/env/environment";
import { Injectable } from "@angular/core";
import { Match } from "../model/match.model";

@Injectable({
    providedIn: 'root'
  })
  export class RecordKeeperService {
  
    constructor(private http: HttpClient) { }
  
    getRecordKeeper(id: number): Observable<any> {
        return this.http.get<any>(environment.apiHost + 'record-keeper/get/' + id);
    }

    save(user: any) : Observable<any>{
        return this.http.put<any>(environment.apiHost + 'record-keeper/edit', user);
    }

    getMatch(matchId: number): Observable<any>{
        return this.http.get<any>(environment.apiHost + `match/getById/${matchId}`);
    }

    createMatchRostersForMatch(match: Match): Observable<Match>{
        return this.http.post<Match>(environment.apiHost + 'match-roster/createMatchRostersForMatch', match)
    }

    // addToBench(matchRosterId: number, playerId: number){
    //     return this.http.put<any>(environment.apiHost + `match-roster/addToBench/${matchRosterId}/${playerId}`, undefined);
    // }

    // removeFromBench(matchRosterId: number, playerId: number){
    //     return this.http.put<any>(environment.apiHost + `match-roster/removeFromBench/${matchRosterId}/${playerId}`, undefined);
    // }
}