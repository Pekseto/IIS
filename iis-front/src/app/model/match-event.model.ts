export interface MatchEvent{
    id?: number,
    type: string,
    minute: number,
    second: number,
    perpetratorName: string,
    perpetratorId?: number,
    matchId: number,
    period: number,
}