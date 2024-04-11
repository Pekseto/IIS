export interface Team {
    id?: number,
    name: string,
    address: string,
    email: string,
    phoneNumber: string,
    city: string,
    country: string,
    playerIds: number[],
    teamManagerId: number | null,
    coachId: number | null
}