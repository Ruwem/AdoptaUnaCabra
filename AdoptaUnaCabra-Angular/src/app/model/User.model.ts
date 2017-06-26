import { News } from './News.model';
import { Goat } from './Goat.model';
import { Comentario } from "app/model/Comentario.model";

export interface User{
    id?: number;
    nombre: string;
    apellidos: string;
    correo: string;
    passwordHash: string;
    profileImage: string;
    goats?: Goat[];
    following?: Goat[];
    news?: News[];
    comments?: Comentario[];
}