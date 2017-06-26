import { Component, OnInit } from '@angular/core';
import { Router } from "@angular/router";
import { User } from "app/model/User.model";
import { LoginService } from "app/services/login.service";
import { AuthService } from "app/services/auth.service";
import { UserService } from "app/services/user.service";



@Component({
  moduleId: module.id,
  selector: 'auc-register',
  templateUrl: './register.component.html',
  styleUrls: ['../../../assets/css/adopta-una-cabra.css',
              '../../../assets/css/vendor/bootstrap/css/bootstrap.css',
              '../../../assets/css/vendor/font-awesome/css/font-awesome.css'
                ]
})
export class registerComponent {

    pass: string;

    constructor(
      private loginService: LoginService,
      private authService: AuthService,
      private UserService: UserService,
      private router: Router
    ) {}

    register(name: string, surname: string, correo: string, passwordHash: string){
        this.UserService.newUser(name,correo,surname,passwordHash).subscribe(
            user => { this.router.navigate(['/']); },
            error => console.log("Fallo en el registro")
        );
    }
}