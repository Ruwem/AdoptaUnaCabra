import { Injectable } from '@angular/core';
import { Headers, Http, RequestOptions } from '@angular/http';
import 'rxjs/add/operator/toPromise';
import { Subject } from 'rxjs/Subject';


import { AuthService } from './auth.service';

import { User } from '../model/user.model';
import { Goat } from '../model/Goat.model';
import { USER_URL, GOAT_URL, BASE_URL } from "app/paths";
import { News } from "app/model/News.model";

@Injectable()
export class UserService {

    constructor(private http: Http, private authService: AuthService) { }

    // Observable string sources
    private changeAnnouncedSource = new Subject<User>();
    // Observable string streams
    changeAnnounced$ = this.changeAnnouncedSource.asObservable();

    // Service message commands
    announceChange(user: User) {
        this.changeAnnouncedSource.next(user);
    }

    getUsers(): Promise<User[]> {
        return this.http.get(USER_URL)
            .toPromise()
            .then(response => response.json())
            .catch(error => console.error(error));
    }

    getUser(id: number): Promise<User> {
        return this.http.get(USER_URL + id)
            .toPromise()
            .then(response => response.json())
            .catch(error => console.error(error));
    }

    getCabrasFavoritas(id:number): Promise<Goat[]>{
        return this.http.get(USER_URL + id + "/favs")
            .toPromise()
            .then(response => response.json())
            .catch(error => console.error(error))
    }

    getCabras(id:number): Promise<Goat[]>{
        return this.http.get(USER_URL + id + "/goats")
            .toPromise()
            .then(response => response.json())
            .catch(error => console.error(error))
    }

    getNews(id:number): Promise<News[]>{
        return this.http.get(USER_URL + id + "/news")
            .toPromise()
            .then(response => response.json())
            .catch(error => console.error(error))
    }

    newUser(nombre: string, apellido: string, correo:string, password: string) {
        let newUser: User;
        newUser = {nombre: nombre, correo: correo, apellidos:apellido, passwordHash: password, profileImage: null, goats: null, following: null, news: null, comments: null};
        const headers = new Headers({
                'Content-Type': 'application/json',
      //'X-Requested-With': 'XMLHttpRequest'
        });
        return this.http.post(BASE_URL + "register/" ,newUser, headers)
    }
    updateUser(user: User): Promise<User> {
        const headers = new Headers({
            'Authorization': 'Basic ' + this.authService.getCredentials(),
            'Content-Type': 'application/json'
        });
        const options = new RequestOptions({ headers });
        return this.http.put(USER_URL, JSON.stringify(user), options)
            .toPromise()
            .then(response => response.json())
            .catch(error => console.error(error));
    }

    removeUser(id: number): Promise<any> {
        const headers = new Headers({
            'Authorization': 'Basic ' + this.authService.getCredentials()
        });
        const options = new RequestOptions({ headers });
        return this.http.delete(USER_URL + id, options)
            .toPromise()
            .then(undefined)
            .catch(error => console.error(error));
    }


    followGoat(id: number): Promise<User>{
        const headers = new Headers({
            'Authorization': 'Basic ' + this.authService.getCredentials(),
            'Content-Type': 'application/json'
        });
        const options = new RequestOptions({ headers });
        let body;
        return this.http.put(USER_URL + "cabra/" + id + "/follow", body , options)
            .toPromise()
            .then(response => response.json())
            .catch(error => console.error(error));
    }

    unfollowGoat(id: number): Promise<Goat>{
        const headers = new Headers({
            'Authorization': 'Basic ' + this.authService.getCredentials(),
            'Content-Type': 'application/json'
        });
        const options = new RequestOptions({ headers });
        let body;
        return this.http.put(USER_URL + "cabra/" + id + "/unfollow", body,options)
            .toPromise()
            .then(response => response.json())
            .catch(error => console.error(error));       
    }

    purchaseGoat(id: number): Promise<Goat>{
        const headers = new Headers({
            'Authorization': 'Basic ' + this.authService.getCredentials()
        });
        const options = new RequestOptions({ headers });
        let body;
        return this.http.put(USER_URL + "cabra/" + id + "/purchase", body,options)
            .toPromise()
            .then(response => response.json())
            .catch(error => console.error(error));       
    }
    eresElUsuario(id: number): boolean{

        return this.authService.getUser().id === id;
    }

    /**
    uploadFile(formData): Promise<any> {
        const headers = new Headers({
            'Authorization': 'Basic ' + this.authService.getCredentials()
        });
        const options = new RequestOptions({ headers });
        return this.http.post(userStorageUrl, formData, options)
            .toPromise()
            .then(response => response.json())
            .catch(error => console.error(error));
    }*/
}
