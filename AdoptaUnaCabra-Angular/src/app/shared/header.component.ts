import { Component, OnInit, OnDestroy, HostListener } from '@angular/core';

import { Router } from '@angular/router';

import { User } from '../model/user.model';

import { LoginService } from '../services/login.service';
import { AuthService } from '../services/auth.service';
import { UserService } from '../services/user.service';
import { Subscription } from 'rxjs/Subscription';

@Component({
    moduleId: module.id,
    selector: 'auc-header',
    templateUrl: './header.component.html',
    
    styleUrls: ['../../assets/css/vendor/bootstrap/css/bootstrap.css',
                '../../assets/css/vendor/font-awesome/css/font-awesome.css',
                '../../assets/css/adopta-una-cabra.css',
                ]
})

export class HeaderComponent implements OnInit {
    public isCollapsed = true; // !Important to keep menu visible
    user: User;

    subscription : Subscription;

    constructor(private loginService: LoginService, public authService: AuthService, private userService: UserService,
        private router: Router) {
        this.subscription = userService.changeAnnounced$.subscribe(
            user => {
                this.user = user;
            });
    }
    @HostListener('window:resize') setCollapsed() {
        this.isCollapsed = true;
    }
    ngOnInit(): void {
        this.authService.reloadAuth(); // Reload auth.service fields from localStorage
        this.user = this.authService.getUser();
    }

    logOut() {
        this.loginService.logOut().subscribe(
            response => {
                this.router.navigate(['/login']);
            },
        );
    }





}