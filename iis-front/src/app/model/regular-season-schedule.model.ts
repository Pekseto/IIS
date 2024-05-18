import { MatchSchedule } from "./match-schedule.model";
import { Match } from "./match.model";

export interface RegularSeasonSchedule{
    id: number,
    matches: MatchSchedule[],
    seasonStart: Date,
    seasonEnd: Date,
}