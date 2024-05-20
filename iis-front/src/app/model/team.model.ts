import { Player } from "./player.model"

export interface Team {
    id?: number,
    name: string,
    address: string,
    email: string,
    phoneNumber: string,
    city: string,
    country: string,
    players?: Player[],
    playerIds?: number[],
    teamManagerId?: number ,
    coachId?: number
}