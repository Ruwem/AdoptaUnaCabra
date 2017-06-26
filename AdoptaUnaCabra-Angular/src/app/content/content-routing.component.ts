import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import { HomeComponent } from "app/content/home/home.component";
import { allNewsComponent } from "app/content/news/allNews/allNews.component";
import { favNewsComponent } from "app/content/news/favNews/favNews.component";
import { ContentComponent } from "app/content/content.component";
import { searchComponent } from "app/content/search/search.component";
import { centerComponent } from "app/content/centers/center/centros.component";
import { centerListComponent } from "app/content/centers/list/listadocentros.component";
import { loginComponent } from "app/content/login/login.component";
import { newsComponent } from "app/content/news/noticias.component";
import { goatProfileComponent } from "app/content/profiles/goat-profile/goat-profile.component";
import { userProfileComponent } from "app/content/profiles/user-profile/user-profile.component";
import { redactNewsComponent } from "app/content/news/redactNews/redactNews.component";
import { ProfileComponent } from "app/content/home/profile/profile.component";
import { newsPageComponent } from "app/content/news/noticiasPage/noticiasPage.component";
import { AddCabraComponent } from "app/content/profiles/user-profile/add-cabra/add-cabra.component";
import { AddImageNewComponent } from "app/content/profiles/user-profile/add-image/addimagenew.component";
import { PaymentComponent } from "app/content/home/payment/payment.component";
import { registerComponent } from "app/content/login/register.component";




const routes: Routes = [
  {
    path: '',
    component: ContentComponent,
    children: [
      {
        path: '',
        component: HomeComponent
      },
      {
        path: 'allnews',
        component: allNewsComponent
      },
      {
        path: 'user/:id/addcabra',
        component: AddCabraComponent
      },
      {
        path: 'favnews',
        component: favNewsComponent
      },
 {
        path: 'allnews/:id',
        component: newsComponent
      },
      {
        path: 'search',
        component: searchComponent
      },
      {
        path: 'center/:id',
        component: centerComponent
      },
      {
        path: 'centerlist',
        component: centerListComponent
      },
      {
        path:'login',
        component: loginComponent
      },
      {
        path: 'goat/:id',
        component: goatProfileComponent
      },
      {
        path: 'user/:id',
        component: userProfileComponent
      },
      {
        path: 'redactNews',
        component: redactNewsComponent
      },
      {
        path: 'buyGoat/:id',
        component: ProfileComponent
      },
      {
        path: 'newsPage',
        component: newsPageComponent
      },
      {
        path: 'addimage',
        component: AddImageNewComponent
      },
      {
        path: 'user/goat/:id/payment',
        component: PaymentComponent
      },
      {
        path: 'register',
        component: registerComponent
      }
    ]
  },
];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule],
})
export class ContentRoutingModule { }

export const routedComponents = [
  HomeComponent,
  allNewsComponent,
  favNewsComponent,
  searchComponent,
  centerComponent,
  centerListComponent,
  loginComponent,
  goatProfileComponent,
  userProfileComponent,
  newsComponent,
  redactNewsComponent,
  ProfileComponent,
  newsPageComponent,
  AddCabraComponent,
  AddImageNewComponent,
  PaymentComponent,
  registerComponent
];
