import { Component, inject, Input, OnInit, signal } from "@angular/core";
import { Job } from "./interfaces/job.interface";
import { JobService } from "../service/job.service";
import { Router } from "@angular/router";

enum JobDescriptionTab {
    TEXT = 'TEXT',
    LINK = 'LINK',
    DOCUMENT = 'DOCUMENT'
}

@Component({
    selector: 'app-job-details',
    standalone: true,
    templateUrl: './job-details.component.html',
    styleUrl: './job-details.component.css'
})
export class JobDetailsComponent implements OnInit {
    private jobService = inject(JobService);
    private router = inject(Router);

    protected readonly JobDescriptionTab = JobDescriptionTab;
    protected activeTab = JobDescriptionTab.TEXT

    @Input({ required: true }) jobPublicId!: string;
    
    job = signal<Job | null>(null);

    ngOnInit(): void {
        this.jobService.getJobDetails(this.jobPublicId).subscribe({
            next: (job) => {
                this.job.set(job);
            },
            error: (error) => {
                console.error(error);
                this.router.navigate(['/404']);
            }
        });
    }

    protected selectTab(tab: JobDescriptionTab) {
        this.activeTab = tab;
    }
}