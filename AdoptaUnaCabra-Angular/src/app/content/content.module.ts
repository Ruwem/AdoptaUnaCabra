// Third party imports
import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { FormsModule } from '@angular/forms';
import { DropdownModule } from 'ngx-dropdown';
import { CarouselModule, TabsModule, CollapseModule, ButtonsModule } from 'ngx-bootstrap';

// App imports
import { ContentRoutingModule, routedComponents } from "app/content/content-routing.component";
import { ContentComponent } from "app/content/content.component";
import { HeaderComponent } from "app/shared/header.component";
import { FooterComponent } from "app/shared/footer.component";
import { allNewsComponent } from "app/content/news/allNews/allNews.component";
import { favNewsComponent } from "app/content/news/favNews/favNews.component";
import { AuthService } from "app/services/auth.service";
import { CenterService } from "app/services/center.service";
import { CommentService } from "app/services/comment.service";
import { GoatService } from "app/services/goat.service";
import { LoginService } from "app/services/login.service";
import { UserService } from "app/services/user.service";
import { NewsService } from "app/services/news.service";
import { userProfileComponent } from "app/content/profiles/user-profile/user-profile.component";
import { newsComponent } from "app/content/news/noticias.component";



@NgModule({
    imports: [
        CommonModule,
        FormsModule,
        DropdownModule,
        CarouselModule.forRoot(),
        TabsModule.forRoot(),
        CollapseModule.forRoot(),
        ButtonsModule.forRoot(),
        ContentRoutingModule,
    ],
    exports: [
        ContentComponent,
        routedComponents,
        ContentRoutingModule
    ],
    declarations: [
        ContentComponent,
        HeaderComponent,
        FooterComponent,
        routedComponents,
        favNewsComponent,
        allNewsComponent,
        userProfileComponent,
        newsComponent
    ],
    providers: [
        AuthService,
        CenterService,
        CommentService,
        GoatService,
        LoginService,
        NewsService,
        UserService
    ],
})
export class ContentModule { }