import { HttpClient, HttpHeaders } from "@angular/common/http";
import { inject, Injectable } from "@angular/core";
import { environment as env } from "../environments/environment";
import { User } from "../interfaces/user.interface";
import { Router } from "@angular/router";
import { Observable } from "rxjs";

@Injectable({
    providedIn: 'root'
})
export class UserService {
    private httpClient = inject(HttpClient);
    private router = inject(Router)

    public getUser(): Observable<User> {
        const url = `${env.BACKEND_BASE_URL}/api/v1/users/me`;
        return this.httpClient.get<User>(url);
    }

    public cleanUp() {
        sessionStorage.clear();
    }
}