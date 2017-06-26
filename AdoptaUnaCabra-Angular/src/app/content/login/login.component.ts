import { Component, OnInit } from '@angular/core';
import { Router } from "@angular/router";
import { User } from "app/model/User.model";
import { LoginService } from "app/services/login.service";
import { AuthService } from "app/services/auth.service";
import { UserService } from "app/services/user.service";



@Component({
  moduleId: module.id,
  selector: 'auc-login',
  templateUrl: './login.component.html',
  styleUrls: ['../../../assets/css/adopta-una-cabra.css',
              '../../../assets/css/vendor/bootstrap/css/bootstrap.css',
              '../../../assets/css/vendor/font-awesome/css/font-awesome.css'
                ]
})
export class loginComponent {

    pass: string;
    users: User[];

    constructor(
      private loginService: LoginService,
      private authService: AuthService,
      private UserService: UserService,
      private router: Router
    ) {}

    logIn(username: string, password: string) {
      this.loginService.logIn(username, password).subscribe(
        user => {
          if (this.authService.isLogged()) {
            this.router.navigate(['/']);
          }
          else {
            console.error('Invalid username or password');
            alert('Invalid username or password');
          }
        },
      );
    }
    

}