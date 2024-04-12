import { Team } from "./team.model";

export interface Registration {
    id?: number,
    name: string,
    surname: string,
    email: string,
    password: string,
    birthday: string,
    phoneNumber: string,
    city: string,
    country: string,
    jmbg: string,
    role: string,
    height: number,
    weight: number,
    status: string,
    team?: number,
}