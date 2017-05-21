import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';
import { HttpModule, JsonpModule } from '@angular/http';
//import { NgbModule } from '@ng-bootstrap/ng-bootstrap';
//import { DropdownModule } from "@ngx-dropdown";
import { BrowserAnimationsModule } from "@angular/platform-browser/animations";

import { AppComponent } from './app.component';
import { AppRoutingModule } from './app-routing.module';

import { GoatService } from './services/goat.service';
import { CentroService } from './services/centro.service';
import { NewsService } from './services/news.service';
import { UserService } from './services/user.service';

@NgModule({
  declarations: [
    AppComponent
  ],
  imports: [
    BrowserAnimationsModule,
    BrowserModule,
    //DropdownModule,
    FormsModule,
    HttpModule,
    JsonpModule,
    //NgbModule.forRoot(),
    ReactiveFormsModule,
    AppRoutingModule
  ],
  providers: [
    GoatService,
    CentroService,
    NewsService,
    UserService
  ],
  bootstrap: [AppComponent]
})
export class AppModule { }
