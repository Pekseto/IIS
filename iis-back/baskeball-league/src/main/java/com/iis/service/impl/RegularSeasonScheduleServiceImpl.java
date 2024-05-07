package com.iis.service.impl;

import com.iis.dtos.MatchDto;
import com.iis.dtos.RegularSeasonScheduleDto;
import com.iis.dtos.TeamDto;
import com.iis.model.MatchResult;
import com.iis.model.Team;
import com.iis.repository.MatchResultRepository;
import com.iis.repository.RegularSeasonScheduleRepository;
import com.iis.repository.TeamRepository;
import com.iis.service.RegularSeasonScheduleService;
import com.iis.util.Mapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.*;
import java.time.temporal.TemporalAdjusters;
import java.util.*;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class RegularSeasonScheduleServiceImpl implements RegularSeasonScheduleService {

    private final RegularSeasonScheduleRepository scheduleRepository;
    private final TeamRepository teamRepository;
    private final MatchResultRepository matchResultRepository;
    private final Mapper mapper;
    @Override
    public RegularSeasonScheduleDto generateSchedule() {

        List<Team> teams = teamRepository.findAll();
        if (teams.size() != 14) {
            throw new IllegalStateException("Nije adekvatan broj timova registrovan. Potrebno je registrovati tačno 16 timova.");
        }

        List<MatchResult> results = matchResultRepository.findAll();

        RegularSeasonScheduleDto schedule = generateBasicSchedule(teams);
        if (!validateSchedule(schedule)) {
            throw new IllegalStateException("Raspored nije validan.");
        }
        schedule = adjustScheduleToMinimizeTravel(schedule,mapper.mapList(teams, TeamDto.class));
        if (!validateSchedule(schedule)) {
            throw new IllegalStateException("Raspored nije validan.");
        }
        schedule = adjustScheduleBasedOnPreviousResults(schedule,results,mapper.mapList(teams, TeamDto.class));
        if (!validateSchedule(schedule)) {
            throw new IllegalStateException("Raspored nije validan.");
        }

        return schedule;
    }

    private RegularSeasonScheduleDto generateBasicSchedule(List<Team> teams) {
        // Odabir datuma odigravanja utakmica (npr. svake srede i četvrtka)
        List<LocalDateTime> matchDates = selectMatchDates(teams);

        // Raspoređivanje utakmica na odabrane datume
        List<MatchDto> schedule = scheduleMatches(teams, matchDates);

        // Kreiranje DTO objekta rasporeda i postavljanje parova mečeva
        RegularSeasonScheduleDto scheduleDto = new RegularSeasonScheduleDto();
        scheduleDto.setMatches(schedule);

        return scheduleDto;
    }

    private List<LocalDateTime> selectMatchDates(List<Team> teams) {
        List<LocalDateTime> matchDates = new ArrayList<>();
        LocalDateTime currentYearStart = Year.now().atMonth(Month.NOVEMBER).atDay(1).with(TemporalAdjusters.firstInMonth(DayOfWeek.WEDNESDAY)).atStartOfDay(); // Prva sreda u novembru ove godine


        LocalTime[] matchTimes = {
                LocalTime.of(18, 45),
                LocalTime.of(20, 0),
                LocalTime.of(20, 30),
                LocalTime.of(20, 45)
        };


        for (int i = 0; i < (teams.size() - 1) * 2; i++) {
            LocalDateTime currentDateTime = currentYearStart;
            for (int j = 0; j < 4; j++) { // Prvih 4 utakmice u sredu
                matchDates.add(currentDateTime.with(matchTimes[j]));
            }
            for (int j = 0; j < 3; j++) {
                matchDates.add(currentDateTime.plusDays(1).with(matchTimes[j]));
            }
            currentYearStart = currentYearStart.plusWeeks(1);
        }

        return matchDates;
    }


    private List<MatchDto> scheduleMatches(List<Team> teams, List<LocalDateTime> matchDates) {
        List<MatchDto> matches = new ArrayList<>();
        List<MatchDto> allPossibleMatches = generateAllMatches(teams); // Generišemo sve moguće parove utakmica

        // Proveravamo da li imamo dovoljno termina za sve utakmice
        if (allPossibleMatches.size() > matchDates.size()) {
            throw new IllegalArgumentException("Nedovoljno termina za raspored svih utakmica.");
        }

        // Raspoređujemo utakmice na datume
        for (int i = 0; i < allPossibleMatches.size(); i++) {
            MatchDto match = allPossibleMatches.get(i);
            match.setMatchDay(matchDates.get(i)); // Postavljamo datum za utakmicu
            matches.add(match); // Dodajemo utakmicu u listu
        }

        return matches;
    }

    private List<MatchDto> generateAllMatches(List<Team> teams) {
        List<MatchDto> allMatches = new ArrayList<>();

        // Pozivamo generateMatchesForOneWeek metodu za sve nedelje u sezoni
        for (int i = 0; i < (teams.size() - 1) * 2; i++) {
            allMatches.addAll(generateMatchesForOneWeek(teams,allMatches));
            Collections.rotate(teams, 1);
        }

        return allMatches;
    }
    private List<MatchDto> generateMatchesForOneWeek(List<Team> teams, List<MatchDto> existingMatches) {
        List<MatchDto> matches = new ArrayList<>();
        List<Team> shuffledTeams = new ArrayList<>(teams); // Kopiramo listu timova i nasumično je mešamo
        Collections.shuffle(shuffledTeams);

        // Razdeljujemo timove na domaće i gostujuće
        List<Team> homeTeams = shuffledTeams.subList(0, shuffledTeams.size() / 2);
        List<Team> awayTeams = shuffledTeams.subList(shuffledTeams.size() / 2, shuffledTeams.size());

        // Kreiramo utakmice za svaki par domaćin-gost
        for (int i = 0; i < homeTeams.size(); i++) {
            Team homeTeam = homeTeams.get(i);
            Team awayTeam = awayTeams.get(i);
            MatchDto match = new MatchDto(mapper.map(homeTeam, TeamDto.class), mapper.map(awayTeam, TeamDto.class));

            // Proveravamo da li je ova utakmica već kreirana u okviru nedelje ili cele sezone
            while (isMatchDuplicate(match, matches) || isMatchDuplicate(match, existingMatches)) {
                // Ponovo mešamo timove kako bismo generisali novi par utakmica
                Collections.shuffle(shuffledTeams);
                homeTeams = shuffledTeams.subList(0, shuffledTeams.size() / 2);
                awayTeams = shuffledTeams.subList(shuffledTeams.size() / 2, shuffledTeams.size());
                homeTeam = homeTeams.get(i);
                awayTeam = awayTeams.get(i);
                match = new MatchDto(mapper.map(homeTeam, TeamDto.class), mapper.map(awayTeam, TeamDto.class));
            }

            // Dodajemo utakmicu u listu
            matches.add(match);
        }

        return matches;
    }

    // Funkcija koja proverava da li se utakmica nalazi u listi postojećih utakmica
    private boolean isMatchDuplicate(MatchDto match, List<MatchDto> existingMatches) {
        for (MatchDto existingMatch : existingMatches) {
            if (existingMatch.equals(match)) {
                return true; // Pronađena duplikat utakmica
            }
        }
        return false; // Utakmica nije pronađena u listi
    }


    public static RegularSeasonScheduleDto adjustScheduleToMinimizeTravel(RegularSeasonScheduleDto basicSchedule, List<TeamDto> teams) {
        RegularSeasonScheduleDto adjustedSchedule = new RegularSeasonScheduleDto();

        // Napravi mapu gradova i timova
        Map<String, TeamDto> cityTeamsMap = teams.stream()
                .collect(Collectors.toMap(TeamDto::getCity, team -> team));

        // Sortiraj utakmice po gradovima
        Map<String, List<MatchDto>> cityMatchesMap = new LinkedHashMap<>();
        for (MatchDto match : basicSchedule.getMatches()) {
            String city = match.getCity();
            cityMatchesMap.computeIfAbsent(city, k -> new ArrayList<>()).add(match);
        }

        // Poredjaj gradove po broju utakmica
        List<String> cities = cityMatchesMap.keySet().stream()
                .sorted(Comparator.comparingInt(city -> cityMatchesMap.get(city).size()))
                .collect(Collectors.toList());

        // Prilagodi raspored minimizirajući putovanja
        List<MatchDto> adjustedScheduleMatches = new ArrayList<>();
        for (String city : cities) {
            TeamDto homeTeam = cityTeamsMap.get(city);
            List<MatchDto> matches = cityMatchesMap.get(city);
            for (MatchDto match : matches) {
                adjustedScheduleMatches.add(match);
                TeamDto awayTeam = getNearestTeam(cityTeamsMap, cities, city);
                match.setAwayTeam(awayTeam);
                cityTeamsMap.remove(awayTeam.getCity());
            }
        }

        adjustedSchedule.setMatches(adjustedScheduleMatches);
        return adjustedSchedule;
    }

    private static TeamDto getNearestTeam(Map<String, TeamDto> cityTeamsMap, List<String> cities, String currentCity) {
        for (int i = 1; i < cities.size(); i++) {
            int prevIndex = cities.indexOf(currentCity) - i;
            int nextIndex = cities.indexOf(currentCity) + i;
            if (prevIndex >= 0) {
                String prevCity = cities.get(prevIndex);
                TeamDto nearestTeam = cityTeamsMap.get(prevCity);
                if (nearestTeam != null) {
                    return nearestTeam;
                }
            }
            if (nextIndex < cities.size()) {
                String nextCity = cities.get(nextIndex);
                TeamDto nearestTeam = cityTeamsMap.get(nextCity);
                if (nearestTeam != null) {
                    return nearestTeam;
                }
            }
        }
        return null; // No nearest team found
    }

    public RegularSeasonScheduleDto adjustScheduleBasedOnPreviousResults(RegularSeasonScheduleDto scheduleDto, List<MatchResult> results, List<TeamDto> teams) {
        // Mapa za praćenje broja pobeda timova
        Map<Long, Integer> teamWinsMap = new HashMap<>();

        // Prvo prolazimo kroz rezultate i računamo broj pobeda za svaki tim
        for (MatchResult result : results) {
            if (result.getHomeTeamScore() > result.getAwayTeamScore()) {
                teamWinsMap.put(result.getMatch().getHomeTeam().getId(), teamWinsMap.getOrDefault(result.getMatch().getHomeTeam().getId(), 0) + 1);
            } else {
                teamWinsMap.put(result.getMatch().getAwayTeam().getId(), teamWinsMap.getOrDefault(result.getMatch().getAwayTeam().getId(), 0) + 1);
            }
        }

        // Sortiranje timova po broju pobeda u opadajućem redosledu
        List<TeamDto> sortedTeams = teams.stream()
                .sorted(Comparator.comparingInt(team -> Math.toIntExact(((TeamDto) team).getId())).reversed())
                .collect(Collectors.toList());

        // Prilagođavanje rasporeda na osnovu prethodnih rezultata
        List<MatchDto> adjustedMatches = new ArrayList<>();
        for (int i = 0; i < scheduleDto.getMatches().size(); i++) {
            MatchDto currentMatch = scheduleDto.getMatches().get(i);
            MatchDto nextMatch = i < scheduleDto.getMatches().size() - 1 ? scheduleDto.getMatches().get(i + 1) : null;

            // Ako postoji sledeći meč i postoje timovi sa manje pobeda koji bi igrali uzastopno sa jačim timovima
            if (nextMatch != null && isConsecutiveMatchWithStrongerTeam(currentMatch, nextMatch, teamWinsMap)) {
                // Prilagodi raspored tako da minimizuje problem uzastopnih utakmica između jakih i slabih timova
                adjustScheduleForConsecutiveMatches(currentMatch, nextMatch, sortedTeams, teamWinsMap);
                adjustedMatches.add(nextMatch);
                i++; // Preskačemo sledeći meč jer smo već dodali njegov prilagođeni oblik
            } else {
                adjustedMatches.add(currentMatch);
            }
        }

        // Ažuriranje rasporeda sa prilagođenim mečevima
        scheduleDto.setMatches(adjustedMatches);

        return scheduleDto;
    }

    private boolean isConsecutiveMatchWithStrongerTeam(MatchDto currentMatch, MatchDto nextMatch, Map<Long, Integer> teamWinsMap) {
        int homeTeamWins = teamWinsMap.getOrDefault(currentMatch.getHomeTeam().getId(), 0);
        int awayTeamWins = teamWinsMap.getOrDefault(currentMatch.getAwayTeam().getId(), 0);
        int nextHomeTeamWins = teamWinsMap.getOrDefault(nextMatch.getHomeTeam().getId(), 0);
        int nextAwayTeamWins = teamWinsMap.getOrDefault(nextMatch.getAwayTeam().getId(), 0);

        return (homeTeamWins < nextHomeTeamWins && awayTeamWins < nextAwayTeamWins)
                || (homeTeamWins < nextAwayTeamWins && awayTeamWins < nextHomeTeamWins);
    }

    private void adjustScheduleForConsecutiveMatches(MatchDto currentMatch, MatchDto nextMatch, List<TeamDto> sortedTeams, Map<Long, Integer> teamWinsMap) {
        // Razmena domaćeg i gostujućeg tima u narednom meču kako bi se minimizovao problem uzastopnih utakmica između jakih i slabih timova
        int currentHomeTeamIndex = sortedTeams.indexOf(currentMatch.getHomeTeam());
        int currentAwayTeamIndex = sortedTeams.indexOf(currentMatch.getAwayTeam());
        int nextHomeTeamIndex = sortedTeams.indexOf(nextMatch.getHomeTeam());
        int nextAwayTeamIndex = sortedTeams.indexOf(nextMatch.getAwayTeam());

        // Biramo koji tim će biti domaćin u sledećem meču
        TeamDto newHomeTeam = currentHomeTeamIndex < nextHomeTeamIndex ? nextMatch.getHomeTeam() : nextMatch.getAwayTeam();
        TeamDto newAwayTeam = currentHomeTeamIndex < nextHomeTeamIndex ? nextMatch.getAwayTeam() : nextMatch.getHomeTeam();

        // Postavljanje novih domaćih i gostujućih timova u sledećem meču
        nextMatch.setHomeTeam(newHomeTeam);
        nextMatch.setAwayTeam(newAwayTeam);
    }

    public boolean validateSchedule(RegularSeasonScheduleDto scheduleDto, List<TeamDto> teams) {

        // Provera da li svaki tim igra protiv svakog drugog tima jednom kao domaćin i jednom kao gost
        Set<Long> visitedTeams = new HashSet<>();
        for (MatchDto match : scheduleDto.getMatches()) {
            Long homeTeamId = match.getHomeTeam().getId();
            Long awayTeamId = match.getAwayTeam().getId();

            // Provera za domaćina
            if (visitedTeams.contains(homeTeamId)) {
                return false; // Domaćin je već bio u ovom rasporedu
            }
            visitedTeams.add(homeTeamId);

            // Provera za gosta
            if (visitedTeams.contains(awayTeamId)) {
                return false; // Gost je već bio u ovom rasporedu
            }
            visitedTeams.add(awayTeamId);
        }

        return true; // Raspored je validan
    }

    public boolean validateSchedule(RegularSeasonScheduleDto scheduleDto) {
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
                return false; // Raspored nije validan ako postoje ponavljani mečevi
            }
        }

        return true; // Raspored je validan ako se ne pojavljuju ponavljani mečevi
    }

    private String generateMatchKey(TeamDto homeTeam, TeamDto awayTeam) {
        // Generisanje ključa na osnovu imena timova
        return homeTeam.getName() + " vs " + awayTeam.getName();
    }
}
