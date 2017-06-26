import { Component } from '@angular/core';
import {Router, ActivatedRoute} from '@angular/router';
import { Goat } from "app/model/Goat.model";
import { GoatService } from "app/services/goat.service";

@Component({
  moduleId: module.id,
  selector: 'auc-profile',
  templateUrl: './profile.component.html',
  styleUrls: ['../../../../assets/css/adopta-una-cabra.css',
              '../../../../assets/css/vendor/bootstrap/css/bootstrap.min.css',
              '../../../../assets/css/vendor/font-awesome/css/font-awesome.css'
                ]
})
export class ProfileComponent {
  public goat: Goat;

  constructor(private router: Router, 
              private activatedRoute: ActivatedRoute,
              private goatService: GoatService) { }
  ngOnInit(){
    let id = this.activatedRoute.snapshot.paramMap.get('id');
    this.goatService.getGoat(parseInt(id))
    .then(goat => this.goat = goat);
  }
}