import { inject } from '@angular/core';
import { CanActivateFn, ActivatedRouteSnapshot, RouterStateSnapshot, Router, UrlTree } from '@angular/router';
import { Observable } from 'rxjs';

export const authGuard: CanActivateFn = (
  route: ActivatedRouteSnapshot,
  state: RouterStateSnapshot)=> {
    const router=inject(Router);
    if(localStorage.getItem("token")===null) {
      router.navigate(['/']);
      return false;
    }
    return true;
  };
