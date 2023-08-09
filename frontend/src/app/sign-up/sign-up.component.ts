import { Component } from '@angular/core';
import { NgForm } from '@angular/forms';
import { SignUpService } from '../service/sign-up.service';
import { Router } from '@angular/router';

@Component({
  selector: 'app-sign-up',
  templateUrl: './sign-up.component.html',
  styleUrls: ['./sign-up.component.css']
})
export class SignUpComponent {

  password: string="";
  rePassword: string="";
  errorMessage: string="";

  constructor(private service: SignUpService, private router: Router) {}

  onSubmit(form: NgForm) {
    if(this.password!=this.rePassword) {
      this.errorMessage="Password does not match";
      return;
    }
    form.value.role.roleName=form.value.role.roleName+"Role";
    form.value.role=[form.value.role];
    console.log(form.value);
    this.service.signup(form.value).subscribe({
      next: (Response: any)=> {
        console.log(Response);
        this.router.navigate(['/']);
      },
      error: (Error: any)=> {
        console.log(Error.error);
        if(Error.error==="Username is not unique") {
          this.errorMessage="Username is not unique";
        }
        else {
          this.errorMessage="An unexpected error occurred!!";
        }
        console.log(Error);
      }
    });
  }

}
