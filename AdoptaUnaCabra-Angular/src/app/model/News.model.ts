import { User } from './User.model';
import { Goat } from './Goat.model';
import { Centro } from './Centro.model';
import { Comment } from './Comment.model';

export interface News{
    id?: number;
    titulo: string;
    description: string;
    cuerpo: string;
    profileImage: string;
    fecha: Date;
    author: User;
    cabras: Goat[];
    centros: Centro;
    comments: Comment[];
}