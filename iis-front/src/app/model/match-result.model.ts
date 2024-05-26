import { Match } from "./match.model";

export interface MatchResult{
    id?: number,
    match: Match,
    homeTeamScore: number,
    awayTeamScore: number,
}