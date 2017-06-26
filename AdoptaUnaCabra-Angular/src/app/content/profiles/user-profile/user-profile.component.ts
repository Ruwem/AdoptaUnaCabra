import { Component } from '@angular/core';
import { User } from "app/model/user.model";
import { ActivatedRoute } from "@angular/router";
import { AuthService } from "app/services/auth.service";
import { UserService } from "app/services/user.service";
import { Goat } from "app/model/Goat.model";
import { News } from "app/model/News.model";

@Component({
  moduleId: module.id,
  selector: 'auc-userProfile',
  templateUrl: './user-profile.component.html',
  styleUrls: ['../../../../assets/css/adopta-una-cabra.css',
              '../../../../assets/css/vendor/bootstrap/css/bootstrap.css',
              '../../../../assets/css/vendor/font-awesome/css/font-awesome.css'
                ]
})
export class userProfileComponent {
  public user: User;
  public favs: Goat[];
  public cabras: Goat[];
  public news: News[];
  constructor(private activatedRoute: ActivatedRoute,
              private userService: UserService,
              private authService: AuthService){}
  ngOnInit(){
    let id = this.activatedRoute.snapshot.paramMap.get('id');
    this.userService.getUser(parseInt(id))
    .then(user => this.user = user);

    this.userService.getCabrasFavoritas(parseInt(id))
    .then(goats => this.favs = goats);

    this.userService.getCabras(parseInt(id))
    .then(goats => this.cabras = goats);

    this.userService.getNews(parseInt(id))
    .then(news => this.news = news)
  }
}
