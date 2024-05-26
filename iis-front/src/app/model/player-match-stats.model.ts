import { Player } from "./player.model";

export interface PlayerMatchStats{
    id: number,
    player: Player,
    matchId: number,
    teamId: number,

    minutesPlayed: number,
    secondsPlayed: number,
    plusMinus: number,
    pir: number,

    points: number,

    twoPM: number,
    threePM: number,
    freeThrowM: number,

    twoPA: number,
    threePA: number,
    freeThrowA: number,

    twoPP: number,
    threePP: number,
    freeThrowP: number,

    offRebounds: number,
    defRebounds: number,

    assists: number,
    steals: number,
    turnovers: number,

    blocks: number,
    blocksAgainst: number,

    foulsCommited: number,
    foulsDrawn: number,
}