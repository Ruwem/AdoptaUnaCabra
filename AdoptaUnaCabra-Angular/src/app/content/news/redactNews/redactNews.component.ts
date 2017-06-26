import { Component } from '@angular/core';
import { Router, ActivatedRoute } from '@angular/router';
import { LoginService } from "app/services/login.service";
import { AuthService } from "app/services/auth.service";
import { UserService } from "app/services/user.service";
import { NewsService } from "app/services/news.service";
import { Goat } from "app/model/Goat.model";

@Component({
  moduleId: module.id,
  selector: 'auc-redactNews',
  templateUrl: './redactNews.component.html',
  styleUrls: ['../../../../assets/css/adopta-una-cabra.css',
              '../../../../assets/css/vendor/bootstrap/css/bootstrap.css',
              '../../../../assets/css/vendor/font-awesome/css/font-awesome.css'
                ]
})
export class redactNewsComponent {
  constructor(private loginService: LoginService,
      private authService: AuthService,
      private UserService: UserService,
      private NewsService: NewsService,
      private router: Router) { }

 addNew(title: string, description: string, cuerpo: string){
        this.NewsService.addNews(title, description, cuerpo).subscribe(
            user => { this.router.navigate(['/']); },
            error => console.log("Fallo en el registro")
        );
    }
}