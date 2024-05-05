import { Component, OnInit } from '@angular/core';

@Component({
  selector: 'app-semaphore',
  templateUrl: './semaphore.component.html',
  styleUrls: ['./semaphore.component.css']
})
export class SemaphoreComponent implements OnInit {
  countdownMinutes: number = 10;
  countdownSeconds: number = 0;
  countdownInterval: any;
  shotClockSeconds: number = 5;

  isPaused: boolean = true;

  constructor() { }

  ngOnInit(): void {
    this.startCountdown();
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
          }
        } else {
          if (this.shotClockSeconds > 0) this.shotClockSeconds--
          this.countdownSeconds--;
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
}
