import { Component, EventEmitter, Input, OnChanges, OnInit, Output, SimpleChanges } from '@angular/core';
import { MatchState } from '../model/match-state.model';

@Component({
  selector: 'app-semaphore',
  templateUrl: './semaphore.component.html',
  styleUrls: ['./semaphore.component.css']
})
export class SemaphoreComponent implements OnInit, OnChanges {
  @Output() quarterUpdated = new EventEmitter<number>();
  @Output() timeUpdated = new EventEmitter<number[]>();
  @Input() matchStateInput?: MatchState;
  matchState?: MatchState;
  countdownMinutes: number = 10;
  countdownSeconds: number = 0;
  countdownInterval: any;
  shotClockSeconds: number = 24;
  isPaused: boolean = true;
  selectedQuarter: number = 1;

  constructor() { }

  ngOnInit(): void {
    this.startCountdown();
  }

  ngOnChanges(changes: SimpleChanges): void {
    this.matchState = this.matchStateInput
  }

  startCountdown() {
    this.countdownInterval = setInterval(() => {
      if (!this.isPaused) {
        if (this.countdownSeconds === 0) {
          if (this.countdownMinutes === 0) {
            clearInterval(this.countdownInterval);
            // Timer has reached 0
          } else {
            if (this.shotClockSeconds > 0) this.shotClockSeconds--
            this.countdownMinutes--;
            this.countdownSeconds = 59;
            this.timeUpdated.emit([this.countdownMinutes, this.countdownSeconds])
          }
        } else {
          if (this.shotClockSeconds > 0) this.shotClockSeconds--
          this.countdownSeconds--;
          this.timeUpdated.emit([this.countdownMinutes, this.countdownSeconds])
        }
      }
    }, 1000);

  }

  changeCountdownState() {
    this.isPaused = !this.isPaused
  }

  resetShotClock(){
    this.shotClockSeconds = 24;
  }

  onQuarterChange(){
    this.quarterUpdated.emit(this.selectedQuarter)
  }
}
