import { Observable } from "rxjs";
import { HttpClient } from "@angular/common/http";
import { environment } from "src/env/environment";
import { Injectable } from "@angular/core";
import { Match } from "../model/match.model";
import { MatchEvent } from "../model/match-event.model";
import { MatchState } from "../model/match-state.model";
import { PlayerMatchStats } from "../model/player-match-stats.model";

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

    createMatchStateForMatch(matchState: MatchState): Observable<MatchState>{
        return this.http.post<MatchState>(environment.apiHost + 'match-state/addMatchState', matchState)
    }

    createPlayerMatchStats(match: Match): Observable<Match>{
        return this.http.post<Match>(environment.apiHost + 'player-match-stats/createPlayerMatchStats', match)
    }

    updateMatchStateForMatch(matchState: MatchState): Observable<MatchState>{
        return this.http.post<MatchState>(environment.apiHost + 'match-state/addMatchState', matchState)
    }

    getMatchStateByMatchId(matchId: number): Observable<MatchState>{
        return this.http.get<MatchState>(environment.apiHost + `match-state/getByMatchId/${matchId}`)
    }

    getMatchEventsByMatchId(matchId: number): Observable<MatchEvent[]>{
        return this.http.get<MatchEvent[]>(environment.apiHost + `match-event/getAllByMatchId/${matchId}`)
    }

    addEvent(event: MatchEvent): Observable<MatchEvent>{
        return this.http.post<MatchEvent>(environment.apiHost + 'match-event/addEvent', event)
    }

    getPlayersTradionalStatsForTeam(matchId: number, teamId: number): Observable<PlayerMatchStats[]>{
        return this.http.get<PlayerMatchStats[]>(environment.apiHost + `player-match-stats/getAllForTeamOnMatch/${matchId}/${teamId}`)
    }

    getActivePlayersStats(matchId: number, homeRosterId: number, awayRosterId: number): Observable<PlayerMatchStats[]>{
        return this.http.get<PlayerMatchStats[]>(environment.apiHost + `player-match-stats/getActivePlayersOnMatch/${matchId}/${homeRosterId}/${awayRosterId}`)
    }

    updatePlayersTimePlayed(playersStats: PlayerMatchStats[]): Observable<void>{
        return this.http.put<void>(environment.apiHost + `player-match-stats/updatePlayersTimePlayed`, playersStats)
    }

    generatePdf(matchId: number): Observable<Blob>{
        return this.http.get<Blob>(environment.apiHost + `player-match-stats/generatePdf/${matchId}`)
    }

    // addToBench(matchRosterId: number, playerId: number){
    //     return this.http.put<any>(environment.apiHost + `match-roster/addToBench/${matchRosterId}/${playerId}`, undefined);
    // }

    // removeFromBench(matchRosterId: number, playerId: number){
    //     return this.http.put<any>(environment.apiHost + `match-roster/removeFromBench/${matchRosterId}/${playerId}`, undefined);
    // }
}