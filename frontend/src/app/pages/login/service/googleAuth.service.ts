import { inject, Injectable } from "@angular/core";
import { environment as env } from "../../../../environments/environment";
import { Router } from "@angular/router";

declare const google: any;

@Injectable({
    providedIn: 'root'
})
export class GoogleAuthService {
    private router = inject(Router)

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
        console.log(response);
        const googleIdToken = response.credential;
        sessionStorage.setItem('google_id_token', googleIdToken)
        // Todo: get or create user from resource server
        this.router.navigate(['/main'])
    }
}