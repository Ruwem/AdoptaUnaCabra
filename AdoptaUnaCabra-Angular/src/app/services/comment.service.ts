import { Http, RequestOptions, Headers } from "@angular/http";
import { AuthService } from "app/services/auth.service";
import { Injectable } from "@angular/core";
import 'rxjs/add/operator/toPromise';
import { Comentario } from "app/model/Comentario.model";
import { COMMENT_URL } from "app/paths";

@Injectable()
export class CommentService {

    constructor(
        private http: Http,
        private authService: AuthService
    )   {}

    addComment(comment: Comment, idnoticia: number): Promise<Comment> {
        const headers = new Headers({
            'Authorization': 'Basic ' + this.authService.getCredentials(),
            'Content-Type': 'application/json'
        });
        const options = new RequestOptions({ headers });
        return this.http.post(COMMENT_URL + "news/" + idnoticia, JSON.stringify(comment), options)
            .toPromise()
            .then(response => response.json())
            .catch(error => console.error(error));
    }

}
