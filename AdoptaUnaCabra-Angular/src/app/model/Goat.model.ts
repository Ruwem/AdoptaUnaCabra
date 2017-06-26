import { User } from './User.model';
import { Center } from './Center.model';
import { News } from './News.model';

export interface Goat{
    id?: number;
    nombre: string;
    raza: string;
    nacimiento: string;
    price?: number;
    weight: number;
    sexo: string;
    profileImage: string;
    owner?: User;
    followers?: User[];
    home?: Center;
    news?: News[];
}