import { Component } from '@angular/core';
import { ActivatedRoute, Router } from "@angular/router";
import { UserService } from "app/services/user.service";
import { GoatService } from "app/services/goat.service";
import { Goat } from "app/model/Goat.model";
import { AuthService } from "app/services/auth.service";

@Component({
  moduleId: module.id,
  selector: 'auc-addCabra',
  templateUrl: './add-cabra.component.html',
  styleUrls: ['../../../../../assets/css/adopta-una-cabra.css',
              '../../../../../assets/css/vendor/bootstrap/css/bootstrap.css',
              '../../../../../assets/css/vendor/font-awesome/css/font-awesome.css'
                ]
})
export class AddCabraComponent {
    addAlert: any;
  goats: Goat[];

  constructor(private router: Router,
              private activatedRoute: ActivatedRoute,
              private userService: UserService,
              private goatService: GoatService,
              private authService: AuthService) {
                
              }

  ngOnInit() {}

  newGoat(nombre: string, raza: string, sexo: string, nacimiento: string, weight: number){
    this.goatService.addGoat(nombre, raza, sexo, nacimiento, weight).subscribe(
      goat => { this.router.navigate(['/']);},
      error => console.log("Fallo al añadir cabra")
    );
  }

  }

