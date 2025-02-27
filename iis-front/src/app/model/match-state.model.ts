export interface MatchState {
    id?: number,
    matchId: number,
    homePoints: number,
    awayPoints: number,
    firstHalfTimeoutsHome: number,
    secondHalfTimeoutsHome: number,
    firstHalfTimeoutsAway: number,
    secondHalfTimeoutsAway: number,
    firstQuarterFoulsHome: number,
    secondQuarterFoulsHome: number,
    thirdQuarterFoulsHome: number,
    fourthQuarterFoulsHome: number,
    firstQuarterFoulsAway: number,
    secondQuarterFoulsAway: number,
    thirdQuarterFoulsAway: number,
    fourthQuarterFoulsAway: number,
    minute: number,
    second: number,
    quarter: number,
    finished: boolean,
}