import { Player } from "./player.model";
import { RecordKeeper } from "./record-keeper.model";
import { Team } from "./team.model";

export interface Match {
    id: number,
    homeTeam: Team,
    awayTeam: Team,
    mainRefereeId: number,
    secondRefereeId: number,
    thirdRefereeId: number,
    fourtRefereeId: number,
    matchDay: Date,
    city: string,
    isHighRisk: boolean,
    recordKeeper: RecordKeeper,
    homeRoster: MatchRoster,
    awayRoster: MatchRoster,
}

export interface MatchRoster {
    id?: number,
    benchPlayers: Player[],
    startingFive: Player[],
    activeFive: Player[],
}