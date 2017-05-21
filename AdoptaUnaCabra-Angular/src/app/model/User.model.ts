import { Centro } from './Centro.model';
import { News } from './News.model';
import { Goat } from './Goat.model';
import { Comment } from './Comment.model';

export interface User{
    id?: number;
    nombre: string;
    apellidos: string;
    email: string;
    password?: string;
    profileImage: string;
    cabras: Goat[];
    following: Goat[];
    news: News[];
    comments: Comment[];
}