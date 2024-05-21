package com.iis.validators;
import com.iis.dtos.MatchDto;
import com.iis.dtos.RegularSeasonScheduleDto;
import com.iis.dtos.TeamDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.*;

@Service
@RequiredArgsConstructor
public class ScheduleValidator {

    public Map<Boolean, String> validateSchedule(RegularSeasonScheduleDto scheduleDto) {
        Map<Boolean, String> validationResults = new LinkedHashMap<>();
        validationResults.put(true, "Schedule is valid");
        if (!checkIfThereAreDuplicatedMatches(scheduleDto)) {
            validationResults.put(false, "There are duplicate matches in the schedule.");
        }
        if (!checkIfMatchDateIsInFuture(scheduleDto)) {
            validationResults.put(false, "There are matches scheduled in the past.");
        }
       /* if (!checkIfThereIsMinRestDayBetweenMatches(scheduleDto)) {
            validationResults.put(false, "A team has less than 3 days between matches.");
        }*/
       /* if (!checkIfTeamHasMultipleMatchesOnSameDate(scheduleDto)) {
            validationResults.put(false, "A team has multiple matches on the same date.");
        }*/


        return validationResults;
    }

    private boolean checkIfTeamHasMultipleMatchesOnSameDate(RegularSeasonScheduleDto scheduleDto) {
        Map<LocalDate, Map<String, Integer>> teamMatchesByDate = new HashMap<>();

        // Iterirajte kroz sve utakmice rasporeda
        for (MatchDto match : scheduleDto.getMatches()) {
            // Izvadite datum odigravanja utakmice
            LocalDate matchDate = match.getMatchDay().toLocalDate();

            // Izvadite timove koji igraju utakmicu
            String homeTeam = match.getHomeTeam().getName();
            String awayTeam = match.getAwayTeam().getName();

            // Proverite da li postoji zapis za taj datum u mapi
            if (!teamMatchesByDate.containsKey(matchDate)) {
                // Ako ne postoji, kreirajte novi zapis u mapi
                teamMatchesByDate.put(matchDate, new HashMap<>());
            }

            // Dodajte ili povećajte broj utakmica za domaći tim
            teamMatchesByDate.get(matchDate).merge(homeTeam, 1, Integer::sum);

            // Dodajte ili povećajte broj utakmica za gostujući tim
            teamMatchesByDate.get(matchDate).merge(awayTeam, 1, Integer::sum);
        }

        // Iterirajte kroz sve datume i proverite da li postoje timovi sa više od jedne utakmice
        for (Map<String, Integer> teams : teamMatchesByDate.values()) {
            for (Map.Entry<String, Integer> entry : teams.entrySet()) {
                if (entry.getValue() > 1) {
                    // Ako tim ima više od jedne utakmice istog datuma, vrati false
                    return false;
                }
            }
        }

        // Ako nijedan tim nema više od jedne utakmice istog datuma, vrati true
        return true;
    }

    private boolean checkIfMatchDateIsInFuture(RegularSeasonScheduleDto scheduleDto) {
        LocalDateTime now = LocalDateTime.now();

        for (MatchDto match : scheduleDto.getMatches()) {
            LocalDateTime matchDay = match.getMatchDay();
            if (matchDay.isBefore(now)) {
                return false; // Ako je datum utakmice u prošlosti, vraćamo false
            }
        }

        return true; // Ako su sve utakmice u budućnosti, vraćamo true
    }

    private boolean checkIfThereIsMinRestDayBetweenMatches(RegularSeasonScheduleDto scheduleDto) {
        int minRestDays = 3; // Minimalni broj dana odmora između utakmica

        Map<String, List<LocalDateTime>> teamMatches = new HashMap<>();

        // Sakupi sve utakmice za svaki tim
        for (MatchDto match : scheduleDto.getMatches()) {
            LocalDateTime matchDay = match.getMatchDay();
            String homeTeam = match.getHomeTeam().getName();
            String awayTeam = match.getAwayTeam().getName();

            teamMatches.computeIfAbsent(homeTeam, k -> new ArrayList<>()).add(matchDay);
            teamMatches.computeIfAbsent(awayTeam, k -> new ArrayList<>()).add(matchDay);
        }

        // Proveri razmak između utakmica za svaki tim
        for (Map.Entry<String, List<LocalDateTime>> entry : teamMatches.entrySet()) {
            List<LocalDateTime> matchDays = entry.getValue();
            Collections.sort(matchDays);

            for (int i = 1; i < matchDays.size(); i++) {
                long daysBetween = Duration.between(matchDays.get(i - 1), matchDays.get(i)).toDays();
                if (daysBetween < minRestDays) {
                    return false;
                }
            }
        }

        return true;
    }


    private boolean checkIfThereAreDuplicatedMatches(RegularSeasonScheduleDto scheduleDto) {
        List<MatchDto> matches = scheduleDto.getMatches();
        Map<String, Integer> matchCountMap = new HashMap<>();

        for (MatchDto match : matches) {
            String matchKey = generateMatchKey(match.getHomeTeam(), match.getAwayTeam());

            // Ako je ključ već u mapi, povećaj broj odigranih mečeva za taj par timova
            if (matchCountMap.containsKey(matchKey)) {
                int count = matchCountMap.get(matchKey);
                matchCountMap.put(matchKey, count + 1);
            } else {
                // Inače, dodaj novi ključ u mapu
                matchCountMap.put(matchKey, 1);
            }
        }

        // Provera da li postoje mečevi koji su odigrani više od jednom
        for (int count : matchCountMap.values()) {
            if (count > 1) {
                return false;
            }
        }

        return true;
    }

    private String generateMatchKey(TeamDto homeTeam, TeamDto awayTeam) {
        // Generisanje ključa na osnovu imena timova
        return homeTeam.getName() + " vs " + awayTeam.getName();
    }
}
