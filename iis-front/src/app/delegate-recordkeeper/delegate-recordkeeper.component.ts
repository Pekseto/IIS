import { Component, OnInit } from '@angular/core';
import { AdministrationService } from '../services/administration.service';
import { FormControl, FormGroup, Validators } from '@angular/forms';

@Component({
  selector: 'app-delegate-recordkeeper',
  templateUrl: './delegate-recordkeeper.component.html',
  styleUrls: ['./delegate-recordkeeper.component.css']
})
export class DelegateRecordkeeperComponent implements OnInit{
  matches: any[] = [];
  recordKeepers: any[] = [];

  constructor(private adminService: AdministrationService) { }

  delegationForm = new FormGroup({
    matchId: new FormControl('', Validators.required),
    recordKeeperId: new FormControl('', Validators.required),
});

  ngOnInit(): void {
    this.adminService.getMatches().subscribe({
      next: (response: any[]) => {
        console.log(response);
        this.matches = response;
      }
    })
    this.adminService.getRecordKeepers().subscribe({
      next: (response: any[]) => {
        console.log(response);
        this.recordKeepers = response;
      }
    })
  }

  delegate(){
    if(this.delegationForm.valid){
      console.log('Validna forma');
      const matchId = this.delegationForm.value.matchId!;
      const recordKeeperId = this.delegationForm.value.recordKeeperId!;
      this.adminService.delegateRecordKeeper(matchId, recordKeeperId).subscribe({
        next: (response: any) => {
          console.log('Uspesno delegirao zapisnicara')
        }
      })
    }
  }

}
