
import { AfterViewInit, Component, inject, OnInit } from '@angular/core';
import { GoogleAuthService } from './service/googleAuth.service';

@Component({
    selector: 'app-login',
    standalone: true,
    templateUrl: './login.component.html',
    styleUrl: './login.component.css',
    imports: []
})
export class LoginComponent implements AfterViewInit {
    private googleAuthService = inject(GoogleAuthService)

    ngAfterViewInit() {
        setTimeout(() => {
            this.googleAuthService.initialize();
            this.googleAuthService.renderButton('googleBtn');
        });
    }
}