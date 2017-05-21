import { User } from './User.model';
import { News } from './News.model';

export interface Comment {
    id?: number;
    comentario: string;
    fecha: Date;
    author: User;
    noticia: News;
}