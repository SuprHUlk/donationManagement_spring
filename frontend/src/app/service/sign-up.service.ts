import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';

@Injectable({
  providedIn: 'root'
})
export class SignUpService {

  path: String = "http://localhost:9090";

  requestHeader = new HttpHeaders(
    {"No-Auth":"True"}
  );

  constructor(private http: HttpClient) { }

  public signup(data: any) {
    return this.http.post(this.path+"/registerNewUser", data, {headers: this.requestHeader});
  }

}
