import { Component, OnInit } from '@angular/core';
import { AuthService } from '../services/auth.service';
import { User } from '../model/user.model';
import { RefereeService } from '../services/referee.service';
import { CertificateService } from '../services/certificate.service';

@Component({
  selector: 'app-certificates',
  templateUrl: './certificates.component.html',
  styleUrls: ['./certificates.component.css']
})
export class CertificatesComponent implements OnInit {
  user!: User;
  certificates: any;
  file!: File;
  certificateName: string = '';
  certificateDate: Date = new Date();
  fileName!: string

  constructor(private authService: AuthService,
    private refereeService: RefereeService,
    private certificateService: CertificateService
  ) {
  }

  ngOnInit(): void {
    this.authService.getUser().subscribe(user => {
      this.user = user;
    })
    this.reloadTable();
  }

  reloadTable() {
    if(this.user.role == 'LEAGUE_ADMIN')
      {
        this.getCertificateRequest()
      }
      else {
        this.getMyCertificate(this.user.id)
      }
  }

  fileSelected(event : any) {
    this.file = event.target.files[0];
    this.fileName = this.file.name;
  }

  addCertificate() {
    const formData = new FormData();

    formData.append("uploadFile", this.file)
    formData.append("name", this.certificateName)
    formData.append("date", this.certificateDate.toISOString())
    formData.append("refereeId", this.user.id.toString())

    this.refereeService.createCertificateRequest(formData).subscribe({
      next: () => {

      }
    })
  }

  getMyCertificate(userId: number) {
    this.certificateService.getMyCertificates(userId).subscribe({
      next: (response) => {
        this.certificates = response;
      }
    }
  );
  }

  getCertificateRequest() {
    this.certificateService.getCertificateRequest().subscribe({
      next: (response) => {
        this.certificates = response
      }
    }
  );  
  }

  changeStatus(certificateId: number, status: string) {
    let obj = {
      certificationId: certificateId,
      certificationStatus: status
    }

    this.certificateService.changeRequestStatus(obj).subscribe({
      next: (response) => {
        this.reloadTable()
      }
    })
  }
}
