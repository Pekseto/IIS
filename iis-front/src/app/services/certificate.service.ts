import { HttpClient } from "@angular/common/http";
import { Injectable } from "@angular/core";
import { Observable } from "rxjs";
import { environment } from "src/env/environment";

@Injectable({
    providedIn:'root'
  })
export class CertificateService {
    private apiController = 'certification/';

    constructor(private http: HttpClient) {}

    getCertificateRequest() : Observable<any> {
        return this.http.get(environment.apiHost + this.apiController + 'getAllRequests');
    }

    getMyCertificates(userId : number) : Observable<any> {
        return this.http.get(environment.apiHost + this.apiController + 'getAllCertificates/' + userId);
    }

    changeRequestStatus(request : any) : Observable<any> {
        return this.http.post(environment.apiHost + this.apiController + 'changeStatus', request);
    }
}