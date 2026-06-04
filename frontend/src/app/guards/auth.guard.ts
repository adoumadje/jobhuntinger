import { CanMatchFn, Router } from "@angular/router";
import { environment as env } from "../environments/environment";
import { inject } from "@angular/core";
import { HttpClient, HttpHeaders } from "@angular/common/http";
import { User } from "../interfaces/user.interface";
import { catchError, map, of } from "rxjs";

export const authGuard : CanMatchFn = () => {
    const router = inject(Router);
    const httpClient = inject(HttpClient);
    const token = sessionStorage.getItem(env.TOKEN_NAME);

    if(!token) {
        return router.createUrlTree(['/login']);
    }

    const url = `${env.BACKEND_BASE_URL}/api/v1/users/me`;
    const headers = new HttpHeaders({
        'Authorization': `Bearer ${token}`
    });

    return httpClient.get<User>(url, { headers }).pipe(
        map(() => true),
        catchError((error) => {
            console.error(error);
            return of(router.createUrlTree(['/login']));
        })
    );
}