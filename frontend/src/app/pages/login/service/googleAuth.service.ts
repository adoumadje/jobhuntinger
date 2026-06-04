import { inject, Injectable } from "@angular/core";
import { environment as env } from "../../../environments/environment";
import { Router } from "@angular/router";
import { HttpClient } from "@angular/common/http";

declare const google: any;

@Injectable({
    providedIn: 'root'
})
export class GoogleAuthService {
    private router = inject(Router);
    private httpClient = inject(HttpClient);

    public initialize() {
        google.accounts.id.initialize({
            client_id: env.GOOGLE_AUTH_CLIENT_ID,
            callback: this.handleCredentialResponse.bind(this)
        });
    }

    public renderButton(elementId: string) {
        google.accounts.id.renderButton(
            document.getElementById(elementId),
            { theme: 'outline', size: 'medium' }
        );
    }

    private handleCredentialResponse(response: any) {
        const googleIdToken = response.credential;
        sessionStorage.setItem(env.TOKEN_NAME, googleIdToken)
        this.router.navigate(['/main']);
    }
}