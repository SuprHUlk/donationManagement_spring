import { HttpErrorResponse, HttpEvent, HttpHandler, HttpInterceptor, HttpRequest } from "@angular/common/http";
import { Observable, catchError, throwError } from "rxjs";
import { Router } from "@angular/router";
import { Injectable } from "@angular/core";

@Injectable()

export class AuthInterceptor implements HttpInterceptor {
  constructor(private router: Router) {}
  intercept(req: HttpRequest<any>, next: HttpHandler): Observable<HttpEvent<any>> {
    if(req.headers.get("No-Auth")==="True") {
      return next.handle(req.clone());
    }
    const token=localStorage.getItem('token')!.replace(/"/g, '');
    req=this.addToken(req, token);
    return next.handle(req)
    .pipe(
      catchError(
        (err: HttpErrorResponse)=> {
          console.log(err.status);
          if(err.status===401) {
            this.router.navigate(['/']);
          }
          return throwError("Wrong");
        }
      )
    );
  }

  private addToken(request: HttpRequest<any>, token: String | null) {
    return request.clone(
      {
        setHeaders: {
          Authorization: "Bearer "+token
        }
      }
    )
  }

}
