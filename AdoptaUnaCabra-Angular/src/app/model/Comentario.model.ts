import { User } from './User.model';
import { News } from './News.model';

export interface Comentario {
    id?: number;
    comentario: string;
    fecha: Date;
    author: User;
    noticia: News;
}