import { Injectable } from '@angular/core';
import { Headers, Http } from '@angular/http';
import { Observable } from 'rxjs/Observable';
import 'rxjs/Rx';
import { NEWS_URL } from "../util";

import { News } from '../model/News.model';

@Injectable()
export class NewsService{
    new: News;
    news: News[];
    authCreds: string;

    constructor(private http: Http){
    }

    setAuthHeaders(authCreds: string) {
            this.authCreds = authCreds;
    }

    getAllNews() {
        let url = NEWS_URL + '/all';
        return this.http.get(url)
            .map(response => response.json())
            .catch(error => Observable.throw('Server Error'));
    }

    getNew(id: number) {
        return this.http.get(NEWS_URL + '/' + id.toString())
        .map(response => {
            this.new = response.json();
            return response.json();
        })
        .catch(error => Observable.throw('Server error'));
    }

    getFavNews() {}

    addNew(noticia: News) {
        this.authCreds = localStorage.getItem("creds");
        let body = JSON.stringify(noticia);
        let headers: Headers = new Headers();
        headers.append('Content-Type', 'application/json');
        headers.append('X-Requested-With', 'XMLHttpRequest');
        headers.append('Authorization', 'Basic' + this.authCreds);
        return this.http.post(NEWS_URL, body, {headers: headers})
            .map(response => response.json())
            .catch(error => Observable.throw('Server error'))
    }

    
}