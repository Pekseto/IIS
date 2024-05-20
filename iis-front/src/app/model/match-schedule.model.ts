import { Team } from "./team.model";

export interface MatchSchedule {
    id: number,
    homeTeam: Team,
    awayTeam: Team,
    mainRefereeId: number,
    secondRefereeId: number,
    thirdRefereeId: number,
    fourtRefereeId: number,
    matchDay: Date,
    city: string,
    isHighRisk: boolean
}