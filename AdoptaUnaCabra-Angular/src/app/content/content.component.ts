import { Component, OnInit } from '@angular/core';


@Component({
    moduleId: module.id,
    selector: 'auc-content',
    template: '<router-outlet></router-outlet>'
})

export class ContentComponent implements OnInit {

    constructor(){}

    ngOnInit() {}
        


    /*goats: Goat[];
    errorMessage: boolean;
    isLogged: boolean;
    centros: Center[];
    message: string;
    successMessage: boolean;
    user: User;
    
    constructor(private GoatService: GoatService, private loginService: LoginService,
                private CentroService: CenterService, private userService: UserService) {
        this.goats = [];
        this.errorMessage = false;
        this.isLogged = false;
        this.centros = [];
        this.successMessage = false;

        this.addGoats(true);
        this.addCenter(true);
                 }

    ngOnInit() {
        this.isLogged = this.loginService.checkCredentials();
        this.user = this.userService.getUserCompleted();
     }

    ngDoCheck(){
        if (this.isLogged != this.loginService.checkCredentials()) {
            this.ngOnInit;
        }
    }**/

}
