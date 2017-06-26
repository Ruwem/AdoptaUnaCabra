import { Component } from '@angular/core';
import { Router, ActivatedRoute } from '@angular/router';
import { News } from "app/model/News.model";
import { NewsService } from "app/services/news.service";
import { AuthService } from "app/services/auth.service";

@Component({
  moduleId: module.id,
  selector: 'auc-allnews',
  templateUrl: './allNews.component.html',
  styleUrls: ['../../../../assets/css/adopta-una-cabra.css',
              '../../../../assets/css/vendor/bootstrap/css/bootstrap.css',
              '../../../../assets/css/vendor/font-awesome/css/font-awesome.css'
                ]
})
export class allNewsComponent {
      public news: News[];
      constructor(private router: Router, activatedRoute: ActivatedRoute, 
                      private newsService: NewsService,
                      private authService: AuthService) { }

      ngOnInit(){
          this.getAllNews(); 
  }

      getAllNews(){
          this.newsService.getNews()
          .then(news => this.news = news )
  }

}
