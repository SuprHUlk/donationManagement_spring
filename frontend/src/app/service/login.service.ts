import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Router } from '@angular/router';

@Injectable({
  providedIn: 'root'
})
export class LoginService {

  path: String = "http://localhost:9090";

  requestHeader = new HttpHeaders(
    {"No-Auth":"True"}
  );

  constructor(private http: HttpClient, private router: Router) { }

  public login(data: any) {
    return this.http.post(this.path+"/authenticate", data, {headers: this.requestHeader});
  }

  public logout() {
    this.clear();
    this.router.navigate(['/']);
  }

  public clear() {
    localStorage.clear();
  }
}
