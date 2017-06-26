import { Injectable } from '@angular/core';
import { Http, Headers, RequestOptions } from '@angular/http';
import 'rxjs/add/operator/toPromise';
import { AuthService } from './auth.service';
import { Center } from "app/model/Center.model";
import { CENTRO_URL } from "app/paths";
import { Observable } from 'rxjs/Observable';

@Injectable()
export class CenterService {

    constructor(
        private http: Http,
        private authService: AuthService) { }

    getCenters(): Promise<Center[]> {
        return this.http.get(CENTRO_URL + "all")
            .toPromise()
            .then(response => response.json())
            .catch(error => console.error(error));
    }

     getCenter(id: number): Promise<Center> {
        return this.http.get(CENTRO_URL + id)
            .toPromise()
            .then(response => response.json())
            .catch(error => console.error(error));
    }

    addCenter(center: Center): Promise<Center> {
        const headers = new Headers({
            'Authorization': 'Basic ' + this.authService.getCredentials(),
            'Content-Type': 'application/json'
        });
        const options = new RequestOptions({ headers });
        return this.http.post(CENTRO_URL, JSON.stringify(center), options)
            .toPromise()
            .then(response => response.json())
            .catch(error => console.error(error));
    }

    deleteCenter(id: number): Promise<any> {
        const headers = new Headers({
            'Authorization': 'Basic ' + this.authService.getCredentials()
        });
        const options = new RequestOptions({ headers });
        return this.http.delete(CENTRO_URL + id, options)
            .toPromise()
            .then(undefined)
            .catch(error => console.error(error));
    }
   
}