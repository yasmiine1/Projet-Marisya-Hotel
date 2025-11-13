import { CanActivate, CanActivateFn, Router } from '@angular/router';
import { AdminService } from './services/Admin.service';
import { Observable } from 'rxjs';
import { Injectable } from '@angular/core';
@Injectable({
  providedIn: 'root'
})
export class AdminAuthGuard implements CanActivate {
  constructor(private authService: AdminService, private router: Router) {}

  canActivate(): Observable<boolean> | Promise<boolean> | boolean {
    if (!this.authService.isLoggedIn()) {
      this.router.navigate(['/admin-login']);
      return false;
    }
    return true;
  }
}
