import { Component } from '@angular/core';
import { Router, ActivatedRoute } from '@angular/router';
import { User } from "app/model/user.model";
import { Goat } from "app/model/Goat.model";
import { UserService } from "app/services/user.service";
import { GoatService } from "app/services/goat.service";
import { AuthService } from "app/services/auth.service";

@Component({
  moduleId: module.id,
  selector: 'auc-search',
  templateUrl: './search.component.html',
  styleUrls: ['../../../assets/css/adopta-una-cabra.css',
              '../../../assets/css/vendor/bootstrap/css/bootstrap.css',
              '../../../assets/css/vendor/font-awesome/css/font-awesome.css'
                ]
})
export class searchComponent {
  public users: User[];
  public goats: Goat[];
  public search: string;
  public visibleG: boolean;
  public visibleU: boolean;

  constructor(private router: Router, activatedRoute: ActivatedRoute,
              private userService: UserService,
              private goatService: GoatService,
              private authService: AuthService) {
                this.visibleG = false;
                this.visibleU = false
   }

   ngOnInit(){
    this.getUserSearched(this.search);
    this.getGoatSearched(this.search);
    this.users = new Array(); 
    this.goats = new Array();
  }

  getUserSearched(search: string) {
    this.userService.getUsers()
    .then(users =>
    users.forEach(user => {
      if(search == user.nombre) {
        this.users.push(user);
        this.visibleU = true
      }
    }))
  }

  getGoatSearched(search: string) {
    this.goatService.getAllGoats()
    .then(goats =>
    goats.forEach(goat => {
      if(search == goat.nombre) {
        this.goats.push(goat);
        this.visibleG = true;
      }
    }))
  }
}