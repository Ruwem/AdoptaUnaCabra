import { Goat } from './Goat.model';
import { News } from './News.model';

export interface Center {
    id?: number;
    profileImage: string;
    nombre: string;
    lugar: string;
    email: string;
    telephone: number;
    news?: News[];
    cabras?: Goat[];
}