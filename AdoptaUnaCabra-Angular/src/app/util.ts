import {environment} from 'environments/environment';

export const BASE_URL = environment.API_URL;

export const LOGIN_URL = BASE_URL + "logIn";
export const USER_URL = BASE_URL + "user";
export const GOAT_URL = BASE_URL + "cabras";
export const NEWS_URL = BASE_URL + "news";
export const COMMENT_URL = BASE_URL + "comment";
export const CENTRO_URL = BASE_URL + "centros";
export const REGISTER_URL = BASE_URL + "register";

export const GOAT_IMG_URL = "./assets/img/imgProfile/GOAT_IMG";
export const CENTRO_IMG_URL = "./assets/img/imgProfile/CENTRO_IMG";
export const NEWS_IMG_URL = "./assets/img/imgProfile/NEWS_IMG";
export const USER_IMG_URL = "./assets/img/imgProfile/USER_IMG";
