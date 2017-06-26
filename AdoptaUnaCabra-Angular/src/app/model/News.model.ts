import { User } from './User.model';
import { Goat } from './Goat.model';
import { Comentario } from './Comentario.model';
import { Center } from "app/model/Center.model";

export interface News{
    id?: number;
    titulo: string;
    descripcion: string;
    cuerpo: string;
    profileImage: string;
    fecha: Date;
    author: User;
    cabras?: Goat[];
    centers?: Center[];
    comentarios?: Comment[];
}