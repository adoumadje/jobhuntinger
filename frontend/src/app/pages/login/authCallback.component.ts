import { Component, inject, OnInit } from "@angular/core";
import { ActivatedRoute } from "@angular/router";
import { GoogleAuthService } from "./service/googleAuth.service";

@Component({
    selector: 'app-auth-callback',
    standalone: true,
    template: '<h4>signing you in...</h4>'
})
export class AuthCallBackComponent implements OnInit {
    private route = inject(ActivatedRoute)
    private googleAuthService = inject(GoogleAuthService)

    ngOnInit() {
        const code = this.route.snapshot.queryParamMap.get('code');
        if(code) {
            this.googleAuthService.finalizeLogin(code);
        }
    }
}