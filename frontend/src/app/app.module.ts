import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';
import { RouterModule, Routes } from '@angular/router';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { LoginComponent } from './login/login.component';
import { SignUpComponent } from './sign-up/sign-up.component';
import { FormsModule } from '@angular/forms'
import { HttpClientModule, HTTP_INTERCEPTORS } from '@angular/common/http';
import { MainComponent } from './main/main.component';
import { authGuard } from './shared/auth.guard';
import { NgoComponent } from './main/ngo/ngo.component';
import { DonationComponent } from './main/donation/donation.component';
import { AuthInterceptor } from './shared/auth.interceptor';
import { NgoService } from './service/ngo.service';

const appRoutes: Routes =[
  { path: '', component: LoginComponent },
  { path: 'signup', component: SignUpComponent },
  { path: 'main', component: MainComponent, canActivate: [authGuard]}
]

@NgModule({
  declarations: [
    AppComponent,
    LoginComponent,
    SignUpComponent,
    MainComponent,
    NgoComponent,
    DonationComponent
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    FormsModule,
    HttpClientModule,
    RouterModule.forRoot(appRoutes)
  ],
  providers: [
    {
      provide: HTTP_INTERCEPTORS,
      useClass: AuthInterceptor,
      multi: true
    },
    NgoService
  ],
  bootstrap: [AppComponent]
})
export class AppModule { }
