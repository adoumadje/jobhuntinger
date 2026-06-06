import { Routes } from '@angular/router';
import { LoginComponent } from './pages/login/login.component';
import { MainComponent } from './pages/main/main.component';
import { authGuard } from './guards/auth.guard';
import { mainRoutes } from './pages/main/main.routes';
import { NotFoundComponent } from './pages/not-found/not-found.component';

export const routes: Routes = [
    { path: '', redirectTo: '/login', pathMatch: 'full' },
    { path: 'login', component: LoginComponent },
    { 
        path: 'main', 
        component: MainComponent, 
        canMatch: [authGuard],
        children: mainRoutes
    },
    { path: '**', component: NotFoundComponent }
];
