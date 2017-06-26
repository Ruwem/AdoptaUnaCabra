import { Component } from '@angular/core';
import { Goat } from "app/model/Goat.model";
import { Router, ActivatedRoute } from "@angular/router";
import { GoatService } from "app/services/goat.service";
import { LoginService } from "app/services/login.service";
import { AuthService } from "app/services/auth.service";
import { UserService } from "app/services/user.service";
import { User } from "app/model/user.model";

@Component({
  moduleId: module.id,
  selector: 'auc-payment',
  templateUrl: './payment.component.html',
  styleUrls: ['../../../../assets/css/adopta-una-cabra.css',
              '../../../../assets/css/vendor/bootstrap/css/bootstrap.css',
              '../../../../assets/css/vendor/font-awesome/css/font-awesome.css'
                ]
})
export class PaymentComponent {
  public goat: Goat;
  public user: User;

  constructor(private router: Router,
              private activatedRoute:ActivatedRoute,
              private goatService: GoatService,
              private userService: UserService,
              private authService: AuthService,
              private loginService: LoginService){}

  ngOnInit() {
  }

  buyGoat():void {
    let user = this.authService.getUser();
    let id = this.activatedRoute.snapshot.paramMap.get('id');
    this.userService
      .purchaseGoat(parseInt(id))
      .then(() => {
        this.getGoat();
        this.router.navigate(['/user', user.id])
      })
      .catch(( ) => {
        this.router.navigate(['/login'])
      })

  }

  getGoat(){
     let id = this.activatedRoute.snapshot.paramMap.get('id');    
     this.goatService.getGoat(parseInt(id))
     .then(goat => this.goat = goat);
  }
}
