import { Component, HostListener } from '@angular/core';
import { AuthService } from 'app/services/auth.service'

@Component({
  moduleId: module.id,
  selector: 'auc-root',
  template: `<router-outlet></router-outlet>`,
})
export class AppComponent {
  @HostListener('window:onclose') clearAuth() {
        console.log('Cleaning credential authorization.....');
        this.authService.clear();
        localStorage.clear();
    }

    constructor(
        private authService: AuthService
    ) { }


    ngOnInit() {
        this.authService.reloadAuth();
    }

    ngOnDestroy() {
        console.log('Cleaning credential authorization.....');
        this.authService.clear();
        localStorage.clear();
    }
}
