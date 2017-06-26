import { Injectable } from '@angular/core';
import { Http, Headers, RequestOptions } from '@angular/http';
import 'rxjs/add/operator/toPromise';
import { NEWS_URL } from '../paths';
import { News } from '../model/news.model';
import { AuthService } from './auth.service';
import { Goat } from "app/model/Goat.model";
import { Comentario } from "app/model/Comentario.model";


@Injectable()
export class NewsService {

    constructor(
        private http: Http,
        private authService: AuthService) { }

    getNews(): Promise<News[]> {
        return this.http.get(NEWS_URL + "/all")
            .toPromise()
            .then(response => response.json())
            .catch(error => console.error(error));
    }

     getNew(id: number): Promise<News> {
        return this.http.get(NEWS_URL + id)
            .toPromise()
            .then(response => response.json())
            .catch(error => console.error(error));
    }
    getComentarios(id:number): Promise<Comentario[]>{
        return this.http.get(NEWS_URL + id + "/comments")
        .toPromise()
        .then(response => response.json())
        .catch(error => console.error(error))
    }

    addNews(title, description, cuerpo) {
        let nuevaNoticia : News;
        nuevaNoticia = { titulo: title, descripcion: description, cuerpo: cuerpo, profileImage: null, fecha: null, author: this.authService.getUser(), cabras: null,centers:null, comentarios: null }
        const headers = new Headers({
            'Authorization': 'Basic ' + this.authService.getCredentials(),
            'Content-Type': 'application/json'
        });
        return this.http.post(NEWS_URL, nuevaNoticia, headers)
    }

    deleteNews(id: number): Promise<any> {
        const headers = new Headers({
            'Authorization': 'Basic ' + this.authService.getCredentials()
        });
        const options = new RequestOptions({ headers });
        return this.http.delete(NEWS_URL + id, options)
            .toPromise()
            .then(undefined)
            .catch(error => console.error(error));
    }

	getFavNews(): Promise<News[]> {
        const headers = new Headers({
            'Authorization': 'Basic ' + this.authService.getCredentials()
        });
        const options = new RequestOptions({ headers });
        return this.http.get(NEWS_URL + "noticiasfav", options )
            .toPromise()
            .then(response => response.json())
            .catch(error => console.error(error));
    }
    
}