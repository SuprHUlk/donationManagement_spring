import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';

@Injectable({
  providedIn: 'root'
})
export class NgoService {

  path: String = "http://localhost:9090";

  constructor(private http: HttpClient) { }

  public getAllNgos() {
    return this.http.get(this.path+"/getAllNgos");
  }
}
