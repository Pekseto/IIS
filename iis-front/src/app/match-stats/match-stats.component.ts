import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { RecordKeeperService } from '../services/record-keeper.service';
import { PlayerMatchStats } from '../model/player-match-stats.model';
import { MatchResult } from '../model/match-result.model';
import { MatchState } from '../model/match-state.model';
import { Match } from '../model/match.model';

@Component({
  selector: 'app-match-stats',
  templateUrl: './match-stats.component.html',
  styleUrls: ['./match-stats.component.css']
})
export class MatchStatsComponent implements OnInit {
  matchState?: MatchState;
  match?: Match;
  homePlayersTraditionalStats?: PlayerMatchStats[];
  awayPlayersTraditionalStats?: PlayerMatchStats[];

  constructor(
    private route: ActivatedRoute,
    private service: RecordKeeperService,
  ) { }

  ngOnInit(): void {
    this.route.params.subscribe(params => {
      const matchId = params['matchId']

      this.service.getMatchStateByMatchId(matchId).subscribe({
        next: (response) => {
          this.matchState = response

          
        }
      })

      this.service.getMatch(matchId).subscribe({
        next: response => {
          this.match = response
          this.service.getPlayersTradionalStatsForTeam(matchId, this.match!.homeTeam.id!).subscribe({
            next: response => {
              this.homePlayersTraditionalStats = response;
            }
          })
          this.service.getPlayersTradionalStatsForTeam(matchId, this.match!.awayTeam.id!).subscribe({
            next: response => {
              this.awayPlayersTraditionalStats = response;
            }
          })
        }
      })

    })
  }

  generatePdf(){
    this.service.generatePdf(this.match!.id).subscribe({
      next: response => {
        const blobUrl = window.URL.createObjectURL(response);
        const anchor = document.createElement('a');
        anchor.href = blobUrl;
        anchor.download = 'matchReport.pdf';
        anchor.click();
        window.URL.revokeObjectURL(blobUrl);
      },
      error: err => {
        console.error('Error downloading the PDF', err);
      }
    })
  }


}
