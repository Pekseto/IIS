import { Injectable } from "@angular/core";
import { Registration } from "../model/registration.model";
import { Observable } from "rxjs";
import { HttpClient } from "@angular/common/http";
import { environment } from "src/env/environment";
import { Referee } from "../model/referee.model";

@Injectable({
    providedIn:'root'
  })
  export class RefereeService {
    private apiController: String = 'referee/';

    constructor(private http: HttpClient,) {}

    register(referee: Registration) : Observable<any> {
        return this.http.post(environment.apiHost + 'users/registerReferee', referee);
    } 

    updae(user: Registration) : Observable<any> {
        return this.http.put(environment.apiHost +'users/updateReferee', user);
    }

    getReferee(id: number) : Observable<any> {
        return this.http.get(environment.apiHost  + 'users/getReferee/' + id);
    }

    getAll() : Observable<any> {
        return this.http.get(environment.apiHost + this.apiController + 'getAll');
    }

    getRecommendation() : Observable<any> {
        return this.http.get(environment.apiHost + this.apiController + 'getRecommendation');
    }

    createCertificateRequest(certificate : any) : Observable<any> {
        return this.http.post(environment.apiHost + this.apiController + 'createCertificateRequest', certificate);
    }
  }