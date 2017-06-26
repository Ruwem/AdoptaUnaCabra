import { Component, OnInit } from '@angular/core';
import { CenterService } from 'app/services/center.service';
import {Router, ActivatedRoute} from '@angular/router';
import { Center } from "app/model/Center.model";

@Component({
  moduleId: module.id,
  selector: 'auc-centerList',
  templateUrl: './listadocentros.component.html',
  styleUrls: ['../../../../assets/css/adopta-una-cabra.css',
              '../../../../assets/css/vendor/bootstrap/css/bootstrap.css',
              '../../../../assets/css/vendor/font-awesome/css/font-awesome.css'
                ]
})
export class centerListComponent implements OnInit {

  public centers: Center[];
  constructor(private router: Router, activatedRoute: ActivatedRoute, 
                      private centerService: CenterService) { }

  ngOnInit(){
    this.getAllCenters(); 
  }

  getAllCenters(){
      this.centerService.getCenters()
      .then(center => this.centers = center )
  }
}