import { BehaviorSubject, Observable, tap } from "rxjs";
import { User } from "../model/user.model";
import { HttpClient } from "@angular/common/http";
import { environment } from "src/env/environment";
import { Injectable } from "@angular/core";
import { Registration } from "../model/registration.model";

@Injectable({
    providedIn: 'root'
  })
  export class AdministrationService {
  
    constructor(private http: HttpClient) { }
  
    getAdmin(id: number): Observable<Registration> {
        return this.http.get<Registration>(environment.apiHost + 'users/getAdmin/' + id);
    }

    save(user: Registration) : Observable<Registration>{
        return this.http.put<Registration>(environment.apiHost + 'users/edit', user);
    }

    //PROMENITI KAD SE UVEDE KONCEPT KOLA DA DOBAVI UTAKMICE SA ODREDJENOG KOLA
    getMatches(): Observable<any[]>{
        return this.http.get<any[]>(environment.apiHost + 'match/getAll');
    }

    getRecordKeepers(): Observable<any[]>{
        return this.http.get<any[]>(environment.apiHost + 'record-keeper/getAll');
    }

    delegateRecordKeeper(matchId: string, recordKeeperId: string): Observable<any>{
        return this.http.get<any>(environment.apiHost + `match/delegateRecordKeeper/${matchId}/${recordKeeperId}`)
    }
}