import { Observable } from "rxjs";
import { HttpClient } from "@angular/common/http";
import { environment } from "src/env/environment";
import { Injectable } from "@angular/core";

@Injectable({
    providedIn: 'root'
  })
  export class RecordKeeperService {
  
    constructor(private http: HttpClient) { }
  
    getRecordKeeper(id: number): Observable<any> {
        return this.http.get<any>(environment.apiHost + 'record-keeper/get/' + id);
    }

    save(user: any) : Observable<any>{
        return this.http.put<any>(environment.apiHost + 'record-keeper/edit', user);
    }
}