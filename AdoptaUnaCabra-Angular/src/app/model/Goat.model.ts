import { User } from './User.model';
import { Centro } from './Centro.model';
import { News } from './News.model';

export interface Goat{
    id?: number;
    nombre: string;
    raza: string;
    nacimiento: Date;
    price: number;
    weight: number;
    sexo: string;
    profileImage: string;
    owner: User;
    followers: User[];
    home: Centro;
    news: News[];
}