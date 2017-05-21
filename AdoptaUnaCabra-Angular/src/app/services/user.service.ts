import { Injectable } from '@angular/core';
import { Headers, Http } from '@angular/http';
import { Observable } from 'rxjs/Observable';
import 'rxjs/Rx';
import { USER_URL } from "../util";

import { User } from '../model/User.model';

@Injectable()
export class UserService{
    user: User;
    users: User[];
    authCreds: string;

    constructor(private http: Http){

    }

    setAuthHeaders(authCreds: string) {
        this.authCreds = authCreds;
    }

   
    getUser(id: number) {
        this.authCreds = localStorage.getItem("creds");
        let headers: Headers = new Headers();
        headers.append('Authorization', 'Basic ' + this.authCreds);
        return this.http.get(USER_URL + '/' + id.toString(), { headers: headers })
        .map(response => {
            this.user = response.json();
            return response.json();
        })
        .catch(error => Observable.throw('Server error'));
    }

    getAllUsers() {
        let url = USER_URL + '/all';
        return this.http.get(url)
            .map(response => response.json())
            .catch(error => Observable.throw('Server Error'));
    }

    addUser(user: User) {
        this.authCreds = localStorage.getItem("creds");
        let body = JSON.stringify(user);
        let headers: Headers = new Headers();
        headers.append('Content-Type', 'application/json');
        headers.append('X-Requested-With', 'XMLHttpRequest');
        headers.append('Authorization', 'Basic' + this.authCreds);
        return this.http.post(USER_URL, body, {headers: headers})
            .map(response => response.json())
            .catch(error => Observable.throw('Server error'))
    }

    deleteUser(id: number) {
        this.authCreds = localStorage.getItem("creds");
        let headers: Headers = new Headers();
        headers.append('Content-Type', 'application/json');
        headers.append('X-Requested-With', 'XMLHttpRequest');
        headers.append('Authorization', 'Basic' + this.authCreds);
        return this.http.delete(USER_URL + '/' + id, {headers: headers})
            .map(response => response.json())
            .catch(error => Observable.throw('Server error'))
    }

    updateUser(user: User, current: boolean) {
        this.authCreds = localStorage.getItem("creds");
        let body = JSON.stringify(user);
        let headers: Headers = new Headers();
        headers.append('Content-Type', 'application/json');
        headers.append('X-Requested-With', 'XMLHttpRequest');
        headers.append('Authorization', 'Basic ' + this.authCreds);
        return this.http.put(USER_URL + '/' + user.id, body, { headers: headers })
        .map(response => {
            if (current) {
            this.getUser(user.id).subscribe(
                user => this.user = user,
                error => error
            );
            }
            return response.json();
        })
        .catch(error => Observable.throw('Server error'));
    }
}