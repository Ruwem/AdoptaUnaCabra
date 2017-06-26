import { Injectable, OnDestroy } from '@angular/core';
import { Http, Headers, RequestOptions } from '@angular/http';
import { Observable } from 'rxjs/Observable';
import 'rxjs/Rx';

import { LOGIN_URL, LOGOUT_URL } from '../paths';

import { User } from '../model/user.model';

import { AuthService } from './auth.service';

@Injectable()
export class LoginService implements OnDestroy {

    ngOnDestroy() {
        localStorage.clear(); // Clear locarStorage onDestroy
    }

    constructor(private http: Http, private authService: AuthService) { }

    logIn(username: string, password: string) {
        this.authService.buildCredentials(username, password);
        const headers = new Headers({
            'Authorization': 'Basic ' + this.authService.getCredentials()
        });
        const options = new RequestOptions({ headers });
        return this.http.get(LOGIN_URL, options).map(
            response => {
                this.authService.buildUser(response.json());
                return response;
            })
            .catch(error => Observable.throw('Server error'));
    }

    logOut() {
        const headers = new Headers({
            'Authorization': 'Basic ' + this.authService.getCredentials()
        });
        const options = new RequestOptions({ headers });
        return this.http.get(LOGOUT_URL, options).map(
            response => {
                localStorage.clear();
                this.authService.clear();
                return response;
            }
        );
    }

}

function utf8_to_b64(str) {
    return btoa(encodeURIComponent(str).replace(/%([0-9A-F]{2})/g, function (match, p1) {
        return String.fromCharCode(<any>'0x' + p1);
    }));
}
