export interface Match {
    id: number,
    homeTeamId: number,
    awayTeamId: number,
    homeTeamName: string,
    awayTeamName: string,
    mainRefereeId: number,
    secondRefereeId: number,
    thirdRefereeId: number,
    fourtRefereeId: number,
    matchDay: Date,
    city: string,
    isHighRisk: boolean
}