import { Component,OnInit } from '@angular/core';
import {Router, ActivatedRoute,RouterModule} from '@angular/router';

import { News } from "app/model/News.model";
import { Comentario } from "app/model/Comentario.model";
import { NewsService } from "app/services/news.service";
import { AuthService } from "app/services/auth.service";


@Component({
  moduleId: module.id,
  selector: 'auc-news',
  templateUrl: './noticias.component.html',
  styleUrls: ['../../../assets/css/adopta-una-cabra.css',
              '../../../assets/css/vendor/bootstrap/css/bootstrap.css',
              '../../../assets/css/vendor/font-awesome/css/font-awesome.css'
                ]
})
export class newsComponent implements OnInit {

  public news: News;
  public comments: Comentario[];

  constructor(private route:Router,
              private activatedRoute: ActivatedRoute,
              private newsService: NewsService,
              private authService: AuthService){}
  ngOnInit(): void {
    let id = this.activatedRoute.snapshot.paramMap.get('id');
    this.newsService.getNew(parseInt(id))
    .then( news => {
      this.news = news;
    })
    this.newsService.getComentarios(parseInt(id))
    .then(comments =>this.comments = comments)

  }


 
}
