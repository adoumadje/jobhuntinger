import { Routes } from '@angular/router';
import { LoginComponent } from './pages/login/login.component';
import { MainComponent } from './pages/main/main.component';
import { AuthCallBackComponent } from './pages/login/authCallback.component';

export const routes: Routes = [
    { path: '', redirectTo: '/login', pathMatch: 'full' },
    { path: 'login', component: LoginComponent },
    { path: 'auth-callback', component: AuthCallBackComponent },
    { path: 'main', component: MainComponent }
];
