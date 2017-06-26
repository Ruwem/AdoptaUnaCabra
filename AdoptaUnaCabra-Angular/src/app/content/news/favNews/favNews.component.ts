import { Component,OnInit } from '@angular/core';
import { Router, ActivatedRoute} from '@angular/router';
import { News } from "app/model/News.model";
import { NewsService } from "app/services/news.service";
import { AuthService } from "app/services/auth.service";

@Component({
  moduleId: module.id,
  selector: 'auc-favnews',
  templateUrl: './favNews.component.html',
  styleUrls: ['../../../../assets/css/adopta-una-cabra.css',
              '../../../../assets/css/vendor/bootstrap/css/bootstrap.css',
              '../../../../assets/css/vendor/font-awesome/css/font-awesome.css'
                ]
})
export class favNewsComponent implements OnInit {

  public news: News[];

  constructor(private router: Router, 
              private activatedRoute: ActivatedRoute,
              private newsService: NewsService,
              private authService: AuthService) { }
  ngOnInit(): void {
    if(!this.authService.isLogged()){
      this.router.navigate(['/allnews'])
    }
    this.newsService.getFavNews()
    .then(news => this.news = news)
  }
}