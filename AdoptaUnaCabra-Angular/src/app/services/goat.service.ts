import { Http, RequestOptions, Headers } from "@angular/http";
import { AuthService } from "app/services/auth.service";
import { Injectable } from "@angular/core";
import 'rxjs/add/operator/toPromise';
import { Goat } from "app/model/Goat.model";
import { User } from "app/model/User.model";
import { News } from "app/model/News.model";
import { GOAT_URL, USER_URL } from "../paths";


@Injectable()
export class GoatService {

    constructor(
        private http: Http,
        private authService: AuthService
    )   {}

    getAllGoats(): Promise<Goat[]>{
        return this.http.get(GOAT_URL + "all")
            .toPromise()
            .then(response => response.json())
            .catch(error => console.error(error));
    }

    getGoat(id: number): Promise<Goat> {
        return this.http.get(GOAT_URL + id)
            .toPromise()
            .then(response => response.json())
            .catch(error => console.error(error));
    }
    getOwner(id:number): Promise<User> {
        return this.http.get(GOAT_URL + id + "/owner")
            .toPromise()
            .then(response => response.json())
            .catch(error => console.error(error));
    }
    getNews(id:number): Promise<News[]>{
        return this.http.get(GOAT_URL + id + "/news")
            .toPromise()
            .then(response => response.json())
            .catch(error => console.error(error))
    }
    isFollowed(id: number): Promise<boolean>{
        return this.http.get(GOAT_URL + id + "/isFollowing")
            .toPromise()
            .then(response => response.json())
            .catch(error => console.error(error))
    }
    addGoat(nombre: string, raza: string, sexo: string, nacimiento: string, weight: number) {
        let newGoat : Goat;
        newGoat = {nombre : nombre, raza : raza, nacimiento : nacimiento, price : null, weight : weight, sexo : sexo, profileImage : null }
        const headers = new Headers({
            'Authorization': 'Basic ' + this.authService.getCredentials(),
            'Content-Type': 'application/json'
        });
       
        let user = this.authService.getUser();
        return this.http.post(GOAT_URL, newGoat, headers)
    }

    deleteGoat(id: number): Promise<any> {
        const headers = new Headers({
            'Authorization': 'Basic ' + this.authService.getCredentials()
        });
        const options = new RequestOptions({ headers });
        return this.http.delete(GOAT_URL + id, options)
            .toPromise()
            .then(undefined)
            .catch(error => console.error(error));
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
