import { Component, OnInit } from '@angular/core';
import {Router, ActivatedRoute} from '@angular/router';

import { Goat } from "app/model/Goat.model";
import { GoatService } from "app/services/goat.service";
import { Center } from "app/model/Center.model";
import { CenterService } from "app/services/center.service";
import { AuthService } from "app/services/auth.service";

@Component({
  moduleId: module.id,
  selector: 'auc-home',
  templateUrl: './home.component.html',
  styleUrls: ['../../../assets/css/adopta-una-cabra.css',
              '../../../assets/css/vendor/bootstrap/css/bootstrap.css',
              '../../../assets/css/vendor/font-awesome/css/font-awesome.css'
                ]
})
export class HomeComponent {
    public goats: Array<Goat>;
    public centers: Center[];
  constructor(private router: Router, activatedRoute: ActivatedRoute, 
              private goatService: GoatService,
              private centerService: CenterService,
              private authService: AuthService) { }

  ngOnInit(){
    this.getGoatsWithoutOwner(); 
    this.get3Centers();
    this.goats = new Array();
    this.centers = new Array();
  }

  getGoatsWithoutOwner(){
      this.goatService.getAllGoats()
      .then(goats => 
      goats.forEach(goat => {
        if(goat.owner === null){
          this.goats.push(goat);
        }
      }))

  }


   get3Centers(){
     let numeros = new Array();
    do{
        let i = Math.floor(Math.random() * 8) + 1;
        if (!numeros.includes(i)){  
          this.centerService.getCenter(i)
          .then(center => this.centers.push(center));
          numeros.push(i);
          console.log(i);
        }
    }while(numeros.length !== 3);
  }
   gotoSearch(search: string) {
    this.router.navigate(['/search'])
  }
}
