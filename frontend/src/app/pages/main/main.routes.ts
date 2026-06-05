import { Routes } from '@angular/router';
import { DashboardComponent } from './dashboard/dashboard.component';
import { AboutComponent } from './about/about.component';
import { NewJobComponent } from './new-job/new-job.component';
import { JobDetailsComponent } from './job-details/job-details.component';

export const mainRoutes: Routes = [
    { path: '', redirectTo: 'dashboard', pathMatch: 'full' },
    { path: 'dashboard', component: DashboardComponent },
    { path: 'new-job', component: NewJobComponent },
    { path: 'about', component: AboutComponent },
    { path: 'job-details/:jobPublicId', component: JobDetailsComponent }
];
