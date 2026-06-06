import { Component, inject, OnInit, signal } from "@angular/core";
import { PagerComponent } from "./pager/pager.component";
import { RouterLink } from "@angular/router";
import { JobService } from "../service/job.service";
import { JobFilters } from "./interface/job-filter.interface";
import { JobPage } from "./interface/job-page.interface";
import { FormsModule, NgForm } from "@angular/forms";

@Component({
    selector: 'app-dashboard',
    standalone: true,
    templateUrl: './dashboard.component.html',
    styleUrl: './dashboard.component.css',
    imports: [PagerComponent, RouterLink, FormsModule]
})
export class DashboardComponent implements OnInit {
    private jobService = inject(JobService);

    jobPage = signal<JobPage | null>(null);
    jobFilters!: JobFilters;

    ngOnInit(): void {
        this.jobFilters = {
            pageNumber: 0,
            rows: 5
        };
        this.loadJobPage();
    }

    public onPageChange(pageNumber: number) {
        this.jobFilters.pageNumber = pageNumber;
        this.loadJobPage();
    }

    public onApplyFilters(formData:NgForm) {
        const formValues = formData.form.value;
        this.jobFilters = {
            keyword: formValues.keyword || null,
            toDate: formValues.toDate || null,
            pageNumber: 0,
            rows: formValues.rows ? Number(formValues.rows) : 5
        }
        this.loadJobPage();
    }

    private loadJobPage() {
        this.jobService.getJobs(this.jobFilters).subscribe({
            next: (jobPage) => {
                this.jobPage.set(jobPage);
            },
            error: (error) => console.error(error)
        });
    }
}