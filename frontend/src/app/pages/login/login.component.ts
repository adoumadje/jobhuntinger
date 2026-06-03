
import { Component, inject } from '@angular/core';
import { GoogleAuthService } from './service/googleAuth.service';

@Component({
    selector: 'app-login',
    standalone: true,
    templateUrl: './login.component.html',
    styleUrl: './login.component.css',
    imports: []
})
export class LoginComponent {
    private googleAuthService = inject(GoogleAuthService);

    public onClick() {
        this.googleAuthService.initLogin();
    }
}