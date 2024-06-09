package com.iis.service.impl;

import com.iis.repository.MatchStateRepository;
import com.lowagie.text.*;
import com.lowagie.text.pdf.PdfPCell;
import com.lowagie.text.pdf.PdfPTable;
import com.lowagie.text.pdf.PdfWriter;
import com.iis.dtos.MatchDto;
import com.iis.dtos.PlayerMatchStatsDto;
import com.iis.model.Match;
import com.iis.model.Player;
import com.iis.model.PlayerMatchStats;
import com.iis.repository.MatchRepository;
import com.iis.repository.MatchRosterRepository;
import com.iis.repository.PlayerMatchStatsRepository;
import com.iis.service.PlayerMatchStatsService;
import com.iis.util.Mapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class PlayerMatchStatsServiceImpl implements PlayerMatchStatsService {
    private final PlayerMatchStatsRepository repo;
    private final MatchRosterRepository matchRosterRepo;
    private final MatchRepository matchRepository;
    private final MatchStateRepository matchStateRepository;
    private final Mapper mapper;

    @Override
    public MatchDto CreatePlayerMatchStats(MatchDto matchDto) {
        var match = mapper.map(matchDto, Match.class);
        var players = new ArrayList<Player>();

        players.addAll(match.getHomeRoster().getStartingFive());
        players.addAll(match.getHomeRoster().getBenchPlayers());
        players.addAll(match.getAwayRoster().getStartingFive());
        players.addAll(match.getAwayRoster().getBenchPlayers());

        players.forEach(player -> {

            var playerMatchStats = new PlayerMatchStats();

            playerMatchStats.setPlayer(player);
            playerMatchStats.setTeamId(player.getTeam().getId());
            playerMatchStats.setMatchId(match.getId());

            repo.save(playerMatchStats);
        });

        return matchDto;
    }

    @Override
    public List<PlayerMatchStatsDto> GetAllForTeamOnMatch(long matchId, long teamId) {
        var playersMatchStats = repo.getAllForTeamOnMatch(matchId, teamId);

        return playersMatchStats.stream()
                .map(playerMatchStats -> mapper.map(playerMatchStats, PlayerMatchStatsDto.class))
                .toList();
    }

    @Override
    public List<PlayerMatchStatsDto> GetStatsForActivePlayersOnMatch(long matchId, long homeRosterId, long awayRosterId) {
        var playersMatchStats = repo.getAllForMatch(matchId);
        var homeRoster = matchRosterRepo.getReferenceById(homeRosterId);
        var awayRoster = matchRosterRepo.getReferenceById(awayRosterId);

        var retVal = new ArrayList<PlayerMatchStats>();
        playersMatchStats.forEach(playerMatchStats -> {

            homeRoster.getActiveFive().forEach(player -> {
                if(playerMatchStats.getPlayer().getId() == player.getId()){
                    retVal.add(playerMatchStats);
                }
            });

            awayRoster.getActiveFive().forEach(player -> {
                if(playerMatchStats.getPlayer().getId() == player.getId()){
                    retVal.add(playerMatchStats);
                }
            });
        });

        return retVal.stream()
                .map(pms -> mapper.map(pms, PlayerMatchStatsDto.class))
                .toList();
    }

    @Override
    public void UpdatePlayersTimePlayed(List<PlayerMatchStatsDto> playersStats) {
        playersStats.forEach(playerStats -> {
            repo.updateTimePlayed(playerStats.getMinutesPlayed(), playerStats.getSecondsPlayed(), playerStats.getId());
        });
    }

    @Override
    public byte[] exportPdf(Long matchId) throws IOException, DocumentException {
        var matchFromDb = matchRepository.findById(matchId).orElse(null);
        var matchState = matchStateRepository.getByMatchId(matchId);

        assert matchFromDb != null;

        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        com.lowagie.text.Document document = new com.lowagie.text.Document();

        String filename = LocalDateTime.now().format(DateTimeFormatter.ofPattern("dd-MM-yyyy-HH-mm-ss")) + ".pdf";
        var emptyParagraph = new Paragraph("\n\n");

        PdfWriter.getInstance(document, byteArrayOutputStream);
        document.open();

        Font titleFont = FontFactory.getFont(FontFactory.HELVETICA_BOLD, 24, Font.BOLD);

        Paragraph title = new Paragraph("MATCH NUMBER No." + matchFromDb.getId(), titleFont);
        title.setAlignment(Element.ALIGN_CENTER);
        document.add(title);

        Font matchDataFont = FontFactory.getFont(FontFactory.HELVETICA, 16, Font.NORMAL);
        Paragraph matchData = new Paragraph();
        matchData.setAlignment(Element.ALIGN_CENTER);
        matchData.add(new Chunk("City: " + matchFromDb.getCity() + "\n", matchDataFont));
        matchData.add(new Chunk("Date: " + matchFromDb.getMatchDay().format(DateTimeFormatter.ofPattern("dd-MM-yyyy HH-mm-ss")) + "\n\n", matchDataFont));
        document.add(matchData);
        document.add(emptyParagraph);


        //TEAMS INFO
        PdfPTable teamsInfoTable = new PdfPTable(3);
        teamsInfoTable.setWidthPercentage(100);

        //Team1 name
        PdfPCell leftNameCell = new PdfPCell(new Paragraph(matchFromDb.getHomeTeam().getName()));
        leftNameCell.setHorizontalAlignment(Element.ALIGN_LEFT);
        leftNameCell.setBorder(PdfPCell.NO_BORDER);
        teamsInfoTable.addCell(leftNameCell);
        //Team2 name
        PdfPCell middleCell = new PdfPCell(new Paragraph(matchState.getHomePoints() + ":" + matchState.getAwayPoints()));
        middleCell.setHorizontalAlignment(Element.ALIGN_CENTER);
        middleCell.setBorder(PdfPCell.NO_BORDER);
        teamsInfoTable.addCell(middleCell);
        //Team2 name
        PdfPCell rightNameCell = new PdfPCell(new Paragraph(matchFromDb.getAwayTeam().getName()));
        rightNameCell.setHorizontalAlignment(Element.ALIGN_RIGHT);
        rightNameCell.setBorder(PdfPCell.NO_BORDER);
        teamsInfoTable.addCell(rightNameCell);

        document.add(teamsInfoTable);
        document.add(emptyParagraph);


        //HOME PLAYERS STATS
        PdfPTable homePlayersTable = new PdfPTable(24);
        homePlayersTable.setWidthPercentage(100);
        var homePlayersStats = this.GetAllForTeamOnMatch(matchId, matchFromDb.getHomeTeam().getId());

        for(var playerStats: homePlayersStats){
            var player = playerStats.getPlayer();
            PdfPCell cell = new PdfPCell(new Paragraph(player.getName() + " " + player.getSurname()));
            homePlayersTable.addCell(cell);
            cell = new PdfPCell(new Paragraph(playerStats.getMinutesPlayed() + ":" + playerStats.getSecondsPlayed()));
            homePlayersTable.addCell(cell);
            cell = new PdfPCell(new Paragraph(playerStats.getPoints()));
            homePlayersTable.addCell(cell);
            cell = new PdfPCell(new Paragraph(playerStats.getTwoPM()));
            homePlayersTable.addCell(cell);
            cell = new PdfPCell(new Paragraph(playerStats.getTwoPA()));
            homePlayersTable.addCell(cell);
            cell = new PdfPCell(new Paragraph(playerStats.getTwoPP().toString()));
            homePlayersTable.addCell(cell);
            cell = new PdfPCell(new Paragraph(playerStats.getThreePM()));
            homePlayersTable.addCell(cell);
            cell = new PdfPCell(new Paragraph(playerStats.getThreePA()));
            homePlayersTable.addCell(cell);
            cell = new PdfPCell(new Paragraph(playerStats.getThreePP().toString()));
            homePlayersTable.addCell(cell);
            cell = new PdfPCell(new Paragraph(playerStats.getFreeThrowM()));
            homePlayersTable.addCell(cell);
            cell = new PdfPCell(new Paragraph(playerStats.getFreeThrowA()));
            homePlayersTable.addCell(cell);
            cell = new PdfPCell(new Paragraph(playerStats.getFreeThrowP().toString()));
            homePlayersTable.addCell(cell);
            cell = new PdfPCell(new Paragraph(playerStats.getFreeThrowA()));
            homePlayersTable.addCell(cell);
            cell = new PdfPCell(new Paragraph(playerStats.getOffRebounds()));
            homePlayersTable.addCell(cell);
            cell = new PdfPCell(new Paragraph(playerStats.getDefRebounds()));
            homePlayersTable.addCell(cell);
            var totalRebounds = playerStats.getOffRebounds() + playerStats.getDefRebounds();
            cell = new PdfPCell(new Paragraph(totalRebounds));
            homePlayersTable.addCell(cell);
            cell = new PdfPCell(new Paragraph(playerStats.getAssists()));
            homePlayersTable.addCell(cell);
            cell = new PdfPCell(new Paragraph(playerStats.getSteals()));
            homePlayersTable.addCell(cell);
            cell = new PdfPCell(new Paragraph(playerStats.getTurnovers()));
            homePlayersTable.addCell(cell);
            cell = new PdfPCell(new Paragraph(playerStats.getBlocks()));
            homePlayersTable.addCell(cell);
            cell = new PdfPCell(new Paragraph(playerStats.getBlocksAgainst()));
            homePlayersTable.addCell(cell);
            cell = new PdfPCell(new Paragraph(playerStats.getFoulsCommited()));
            homePlayersTable.addCell(cell);
            cell = new PdfPCell(new Paragraph(playerStats.getFoulsDrawn()));
            homePlayersTable.addCell(cell);
            cell = new PdfPCell(new Paragraph(playerStats.getPIR()));
            homePlayersTable.addCell(cell);
            cell = new PdfPCell(new Paragraph(playerStats.getPlusMinus()));
            homePlayersTable.addCell(cell);
        }
        document.add(homePlayersTable);
        document.add(emptyParagraph);

        //AWAY PLAYERS STATS
        PdfPTable awayPlayersTable = new PdfPTable(24);
        awayPlayersTable.setWidthPercentage(100);
        var awayPlayersStats = this.GetAllForTeamOnMatch(matchId, matchFromDb.getAwayTeam().getId());

        for(var playerStats: awayPlayersStats){
            var player = playerStats.getPlayer();
            PdfPCell cell = new PdfPCell(new Paragraph(player.getName() + " " + player.getSurname()));
            awayPlayersTable.addCell(cell);
            cell = new PdfPCell(new Paragraph(playerStats.getMinutesPlayed() + ":" + playerStats.getSecondsPlayed()));
            awayPlayersTable.addCell(cell);
            cell = new PdfPCell(new Paragraph(playerStats.getPoints()));
            awayPlayersTable.addCell(cell);
            cell = new PdfPCell(new Paragraph(playerStats.getTwoPM()));
            awayPlayersTable.addCell(cell);
            cell = new PdfPCell(new Paragraph(playerStats.getTwoPA()));
            awayPlayersTable.addCell(cell);
            cell = new PdfPCell(new Paragraph(playerStats.getTwoPP().toString()));
            awayPlayersTable.addCell(cell);
            cell = new PdfPCell(new Paragraph(playerStats.getThreePM()));
            awayPlayersTable.addCell(cell);
            cell = new PdfPCell(new Paragraph(playerStats.getThreePA()));
            awayPlayersTable.addCell(cell);
            cell = new PdfPCell(new Paragraph(playerStats.getThreePP().toString()));
            awayPlayersTable.addCell(cell);
            cell = new PdfPCell(new Paragraph(playerStats.getFreeThrowM()));
            awayPlayersTable.addCell(cell);
            cell = new PdfPCell(new Paragraph(playerStats.getFreeThrowA()));
            awayPlayersTable.addCell(cell);
            cell = new PdfPCell(new Paragraph(playerStats.getFreeThrowP().toString()));
            awayPlayersTable.addCell(cell);
            cell = new PdfPCell(new Paragraph(playerStats.getFreeThrowA()));
            awayPlayersTable.addCell(cell);
            cell = new PdfPCell(new Paragraph(playerStats.getOffRebounds()));
            awayPlayersTable.addCell(cell);
            cell = new PdfPCell(new Paragraph(playerStats.getDefRebounds()));
            awayPlayersTable.addCell(cell);
            var totalRebounds = playerStats.getOffRebounds() + playerStats.getDefRebounds();
            cell = new PdfPCell(new Paragraph(totalRebounds));
            awayPlayersTable.addCell(cell);
            cell = new PdfPCell(new Paragraph(playerStats.getAssists()));
            awayPlayersTable.addCell(cell);
            cell = new PdfPCell(new Paragraph(playerStats.getSteals()));
            awayPlayersTable.addCell(cell);
            cell = new PdfPCell(new Paragraph(playerStats.getTurnovers()));
            awayPlayersTable.addCell(cell);
            cell = new PdfPCell(new Paragraph(playerStats.getBlocks()));
            awayPlayersTable.addCell(cell);
            cell = new PdfPCell(new Paragraph(playerStats.getBlocksAgainst()));
            awayPlayersTable.addCell(cell);
            cell = new PdfPCell(new Paragraph(playerStats.getFoulsCommited()));
            awayPlayersTable.addCell(cell);
            cell = new PdfPCell(new Paragraph(playerStats.getFoulsDrawn()));
            awayPlayersTable.addCell(cell);
            cell = new PdfPCell(new Paragraph(playerStats.getPIR()));
            awayPlayersTable.addCell(cell);
            cell = new PdfPCell(new Paragraph(playerStats.getPlusMinus()));
            awayPlayersTable.addCell(cell);
        }
        document.add(awayPlayersTable);

        document.close();

        return byteArrayOutputStream.toByteArray();
    }
}
