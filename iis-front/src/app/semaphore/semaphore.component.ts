import { Component, EventEmitter, Input, OnChanges, OnInit, Output, SimpleChanges } from '@angular/core';
import { MatchState } from '../model/match-state.model';

@Component({
  selector: 'app-semaphore',
  templateUrl: './semaphore.component.html',
  styleUrls: ['./semaphore.component.css']
})
export class SemaphoreComponent implements OnInit, OnChanges {
  @Output() matchStateUpdated = new EventEmitter<MatchState>();
  @Output() saveMatchState = new EventEmitter<null>();
  @Input() matchStateInput?: MatchState;
  matchState?: MatchState;
  countdownInterval: any;
  shotClockSeconds: number = 24;
  isPaused: boolean = true;

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
        if (this.matchState?.second === 0) {
          if (this.matchState?.minute === 0) {
            // Timer has reached 0
            if (this.matchState?.quarter < 4) {
              this.isPaused = true
              this.shotClockSeconds = 24
              this.matchState!.minute = 10
              this.matchState!.second = 0
              this.matchState!.quarter++
              this.matchStateUpdated.emit(this.matchState)
              this.saveMatchState.emit()
            }
            else this.isPaused = true
          } else {
            if (this.shotClockSeconds > 0) this.shotClockSeconds--
            this.matchState!.minute--;
            this.matchState!.second = 59;
            this.matchStateUpdated.emit(this.matchState)
          }
        } else {
          if (this.shotClockSeconds > 0) this.shotClockSeconds--
          this.matchState!.second--;
          this.matchStateUpdated.emit(this.matchState)
        }
      }
    }, 1000);

  }

  changeCountdownState() {
    this.isPaused = !this.isPaused
    if(this.isPaused) this.saveMatchState.emit()
  }

  resetShotClock() {
    this.shotClockSeconds = 24;
  }

  onQuarterChange() {
    this.matchStateUpdated.emit(this.matchState)
  }
}
