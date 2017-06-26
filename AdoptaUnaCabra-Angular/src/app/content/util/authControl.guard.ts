import { Injectable } from '@angular/core';
import { Router, CanActivate } from '@angular/router';
import { AuthService } from '../../services/auth.service';

@Injectable()
export class AuthControl implements CanActivate {

    constructor(private authService: AuthService, private router: Router) { }

    canActivate() {
        console.log('Is user?' + this.authService.isLogged());
        if (this.authService.isLogged()) {
            return true;
        }
        this.router.navigate(['/login']);
        return false;
    }
}
