import { inject, Injectable } from "@angular/core";
import { environment as env } from "../../../../environments/environment";
import { HttpClient, HttpHeaders, HttpParams } from "@angular/common/http";
import { Router } from "@angular/router";

@Injectable({
    providedIn: 'root'
})
export class GoogleAuthService {
    private httpClient = inject(HttpClient)
    private router = inject(Router)

    public async initLogin() {
        const { codeChallenge, codeVerifier } = await this.generatePKCE();
        
        sessionStorage.setItem('pkce_verifier', codeVerifier);

        const params = new URLSearchParams({
            client_id: env.GOOGLE_AUTH_CLIENT_ID,
            redirect_uri: env.GOOGLE_AUTH_REDIRECT_URI,
            response_type: 'code',
            scope: 'openid email profile',
            code_challenge: codeChallenge,
            code_challenge_method: 'S256',
            access_type: 'offline',
        })

        window.location.href = `${env.GOOGLE_AUTHORIZATION_URI}?${params}`;
    }

    public finalizeLogin(code: string) {
        const codeVerifier = sessionStorage.getItem('pkce_verifier')!;

        const body = new HttpParams()
            .set('client_id', env.GOOGLE_AUTH_CLIENT_ID)
            .set('grant_type', 'authorization_code')
            .set('code', code)
            .set('redirect_uri', env.GOOGLE_AUTH_REDIRECT_URI)
            .set('code_verifier', codeVerifier);

        const headers = new HttpHeaders({
            'Content_type': 'application/x-www-form-urlencoded'
        })

        this.httpClient.post(
            env.GOOGLE_TOKEN_URI, 
            body.toString(),
            { headers })
        .subscribe({
            next: (googleTokens) => {
                this.manageTokens(googleTokens);
            },
            error: (err) => console.error(err)
        });
    }

    private async generatePKCE() {
        const encoder = new TextEncoder();
        const randomBytes =  crypto.getRandomValues(new Uint8Array(32));

        const codeVerifier = btoa(String.fromCharCode(...randomBytes))
            .replace(/\+/g, '-').replace(/\//g, '_').replace(/=/g, '');
        
        const digest = await crypto.subtle.digest('SHA-256', encoder.encode(codeVerifier));

        const codeChallenge = btoa(String.fromCharCode(...new Uint8Array(digest)))
            .replace(/\+/g, '-').replace(/\//g, '_').replace(/=/g, '');

        return {codeChallenge, codeVerifier};
    }

    private manageTokens(googleTokens:any) {
        console.log(googleTokens)
        const idToken = googleTokens.id_token;
        this.router.navigate(['/main']);
    }
}