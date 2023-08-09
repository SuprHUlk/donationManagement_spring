import { Component } from '@angular/core';
import { NgForm } from '@angular/forms';
import { LoginService } from '../service/login.service';
import { Router } from '@angular/router';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent {

  errorMessage: String = "";

  constructor(private loginService: LoginService, private router: Router) {}

  onSubmit(form: NgForm) {
    this.loginService.login(form.value).subscribe({
      next: (Response: any)=> {
        console.log(Response);
        this.setRoles(Response.user.roleName);
        this.setUsername(Response.user.userName);
        this.setToken(Response.jwtToken);
        this.router.navigate(['/main']);
      },
      error: (Error: any)=> {
        console.log(Error);
        this.errorMessage="ERROR";
      }
    });
  }

  public setRoles(roleName: string) {
    localStorage.setItem('roleName', JSON.stringify(roleName));
  }

  public setUsername(userName: string) {
    localStorage.setItem('userName', JSON.stringify(userName));
  }

  public setToken(token: string) {
    localStorage.setItem('token', JSON.stringify(token));
  }

}
