export class GoogleAuthService {
    initLogin() {

    }

    async generatePKCE() {
        const encoder = new TextEncoder();
        const randomBytes =  crypto.getRandomValues(new Uint8Array(32));

        const codeVerifier = btoa(String.fromCharCode(...randomBytes))
            .replace(/\+/g, '-').replace(/\//g, '_').replace(/=/g, '');
        
        const digest = await crypto.subtle.digest('SHA-256', encoder.encode(codeVerifier));

        const codeChallenge = btoa(String.fromCharCode(...new Uint8Array(digest)))
            .replace(/\+/g, '-').replace(/\//g, '_').replace(/=/g, '');

        return {codeChallenge, codeVerifier};
    }
}