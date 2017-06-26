import { Component, OnInit, Inject, Injectable  } from '@angular/core';
import { Goat } from "app/model/Goat.model";
import {Router, ActivatedRoute} from '@angular/router';
import { AuthService } from "app/services/auth.service";
import { GoatService } from "app/services/goat.service";
import { NewsService } from "app/services/news.service";
import { UserService } from "app/services/user.service";
import { User } from "app/model/user.model";
import { RouterModule } from "@angular/router";
import { DOCUMENT } from '@angular/platform-browser';
import { News } from "app/model/News.model";

@Component({
  moduleId: module.id,
  selector: 'auc-goatProfile',
  templateUrl: './goat-profile.component.html',
  styleUrls: ['../../../../assets/css/adopta-una-cabra.css',
              '../../../../assets/css/vendor/bootstrap/css/bootstrap.css',
              '../../../../assets/css/vendor/font-awesome/css/font-awesome.css'
                ]
})
export class goatProfileComponent implements OnInit {

  public goat: Goat;
  public owner: User;
  public news: Array<News>;
  public hasOwner = false;
  public isOwner = false;
  public isFollowed: boolean;

  constructor( @Inject(DOCUMENT) private document: any,
              private router: Router,
              private activatedRoute: ActivatedRoute,
              private goatService: GoatService,
              private authService: AuthService,
              private newsService: NewsService,
              private userService: UserService
              ){
  
              }

    ngOnInit(): void {
    let id = this.activatedRoute.snapshot.paramMap.get('id');
    this.news = new Array();
    this.goatService.getGoat(parseInt(id))
    .then(goat => this.goat = goat);
    this.goatService.getOwner(parseInt(id))
    .then(owner => this.owner = owner)
    this.goatService.getNews(parseInt(id))
    .then(news => this.news = news)
    if(this.authService.getUser !== null){
    this.goatService.isFollowed(parseInt(id))
      .then(response => {
        this.isFollowed = response
        console.log(response)})
    }

    
  }
  follow():void{
    let id = this.activatedRoute.snapshot.paramMap.get('id');
    this.userService
      .followGoat(parseInt(id))
      .then(() => {
        this.getGoat();
        this.isFollowed = true;
      })
  }
  unfollow():void{
    let id = this.activatedRoute.snapshot.paramMap.get('id');
    this.userService
      .unfollowGoat(parseInt(id))
      .then(() => {
        this.getGoat();
        this.isFollowed = false;
      })
  }

  getGoat() {
    let id = this.activatedRoute.snapshot.paramMap.get('id');    
    this.goatService.getGoat(parseInt(id))
    .then(goat => this.goat = goat);
  }
  
}
