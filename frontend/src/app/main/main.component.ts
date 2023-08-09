import { Component } from '@angular/core';
import { LoginService } from '../service/login.service';

@Component({
  selector: 'app-main',
  templateUrl: './main.component.html',
  styleUrls: ['./main.component.css']
})
export class MainComponent {

  constructor(private loginService: LoginService) {}

  page: String="ngo";

  logout() {
    this.loginService.logout();
  }
}
