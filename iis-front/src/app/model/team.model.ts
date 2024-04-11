export interface Team {
    name: string,
    address: string,
    email: string,
    phoneNumber: string,
    city: string,
    country: string,
    playerIds: number[],
    teamManagerId: number,
    coachId: number
}