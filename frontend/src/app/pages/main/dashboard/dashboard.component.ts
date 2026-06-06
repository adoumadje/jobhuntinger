import { Component, inject, OnInit, signal } from "@angular/core";
import { PagerComponent } from "./pager/pager.component";
import { RouterLink } from "@angular/router";
import { JobService } from "../service/job.service";
import { JobFilters } from "./interface/job-filter.interface";
import { JobPage } from "./interface/job-page.interface";

@Component({
    selector: 'app-dashboard',
    standalone: true,
    templateUrl: './dashboard.component.html',
    styleUrl: './dashboard.component.css',
    imports: [PagerComponent, RouterLink]
})
export class DashboardComponent implements OnInit {
    private jobService = inject(JobService);

    jobPage = signal<JobPage | null>(null);

    ngOnInit(): void {
        const jobFilters: JobFilters = {
            pageNumber: 0,
            rows: 5
        };
        this.jobService.getJobs(jobFilters).subscribe({
            next: (jobPage) => {
                this.jobPage.set(jobPage);
            },
            error: (error) => console.error(error)
        });
    }

    public onPageChange(pageNumber: number) {
        console.log('page change');
    }
}