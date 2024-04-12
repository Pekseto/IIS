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
    private apiController: String = 'referee';

    constructor(private http: HttpClient,) {}

    register(user: Referee) : Observable<any> {
        return this.http.post(environment.apiHost + 'referee/register', user);
    } 

    updae(user: Referee) : Observable<any> {
        return this.http.put(environment.apiHost + this.apiController + '/update', user);
    }

    getReferee(id: number) : Observable<any> {
        return this.http.get(environment.apiHost + this.apiController + '/getReferee' + id);
    }
  }