import { Component } from '@angular/core';
import { CenterService } from 'app/services/center.service';
import {Router, ActivatedRoute} from '@angular/router';
import { Center } from "app/model/Center.model";
import { LoginService } from "app/services/login.service";
import { AuthService } from "app/services/auth.service";

@Component({
  moduleId: module.id,
  selector: 'auc-center',
  templateUrl: './centros.component.html',
  styleUrls: ['../../../../assets/css/adopta-una-cabra.css',
              '../../../../assets/css/vendor/bootstrap/css/bootstrap.css',
              '../../../../assets/css/vendor/font-awesome/css/font-awesome.css'
                ]
})
export class centerComponent{
  public center: Center;
  constructor(private activatedRoute: ActivatedRoute,
              private CenterService: CenterService,
              private authService: AuthService){}
  ngOnInit(){
    let id = this.activatedRoute.snapshot.paramMap.get('id');
    this.CenterService.getCenter(parseInt(id))
    .then(center => this.center = center);
  }
  }
