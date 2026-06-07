import { Component, inject, signal } from "@angular/core";
import { FormsModule, NgForm } from "@angular/forms";
import { DocumentService } from "./service/document.service";
import { DocumentType } from "./enums/document-type.enum";
import { forkJoin, of, switchMap } from "rxjs";
import { JobRegistrationRequest } from "./interfaces/job-registration-request.interface";
import { JobService } from "../service/job.service";
import { Router } from "@angular/router";

@Component({
    selector: 'app-new-job',
    standalone: true,
    templateUrl: './new-job.component.html',
    styleUrl: './new-job.component.css',
    imports: [FormsModule]
})
export class NewJobComponent {
    private documentService = inject(DocumentService);
    private jobService = inject(JobService);
    private router = inject(Router);

    protected resume?:File;
    protected descriptionDocument?:File;
    protected resumeMissing = false;
    protected descriptionDocumentMissing = false;
    protected descriptionMissing = signal<boolean>(false);

    protected requiredFieldsMissing = signal<boolean>(false);
    protected registering = signal<boolean>(false);

    protected serverError = signal<string | null>(null);

    public onResumeChange(event: Event) {
        const input = event.target as HTMLInputElement;
        if(input.files?.length) {
            this.resume = input.files[0];
            this.resumeMissing = false;
        }
    }

    public onDescriptionTextOrUrlChange(event: Event) {
        const input = event.target as HTMLInputElement | HTMLTextAreaElement;
        if(input.value) {
            this.descriptionMissing.set(false);
        }
    }

    public onDescriptionDocumentChange(event: Event) {
        const input = event.target as HTMLInputElement;
        if(input.files?.length) {
            this.descriptionDocument = input.files[0];
            this.descriptionDocumentMissing = false;
            this.descriptionMissing.set(false);
        }
    }

    public clearServerError(): void {
        this.serverError.set(null);
    }

    public onSubmit(formData: NgForm) {
        if(!this.validateForm(formData)) return;

        this.registering.set(true);

        const resumeUpload$ = this.documentService.upload(this.resume!, DocumentType.RESUME);
        const descDocUpload$ = this.descriptionDocument 
                ? this.documentService.upload(this.descriptionDocument, DocumentType.JOB_DESCRIPTION)
                : of(undefined);

        forkJoin({
            resume: resumeUpload$,
            doc: descDocUpload$
        }).pipe(
            switchMap((result) => {
                const formValue = formData.form.value;
                const jobRegistrationReq: JobRegistrationRequest = {
                    companyName: formValue.companyName,
                    companyLogoUrl: formValue.companyLogoUrl,
                    jobTitle: formValue.jobTitle,
                    jobDescriptionText: formValue.jobDescriptionText || null,
                    jobDescriptionWebsiteUrl: formValue.jobDescriptionWebsiteUrl || null,
                    jobDescriptionDocumentUrl: result.doc,
                    resumeName: this.resume!.name,
                    resumeUrl: result.resume
                }
                return this.jobService.registerJob(jobRegistrationReq);
            })
        ).subscribe({
            next: () => {
                this.registering.set(false);
                this.router.navigate(['/main/dashboard']);
            },
            error: (error) => {
                console.error(error);
                this.serverError.set(error.message);
                this.registering.set(false);
            }
        });
    }

    private validateForm(formData: NgForm): boolean {
        if(!this.validateRequiredFields(formData)) return false;

        if(!this.resume) {
            this.resumeMissing = true;
            return false;
        }

        if(!this.descriptionDocument) {
            this.descriptionDocumentMissing = true;
        }

        const formValues = formData.form.value;

        const hasText = !!formValues.jobDescriptionText?.trim();
        const hasUrl = !!formValues.jobDescriptionUrl?.trim();
        const hasFile = !!this.descriptionDocument;

        if(!hasText && !hasUrl && !hasFile) {
            this.descriptionMissing.set(true);
            return false;
        }

        return true;
    }

    private validateRequiredFields(formData: NgForm): boolean {
        const formValues = formData.form.value;

        const missCompanyName = !formValues.companyName?.trim();
        const missCompanyLogoUrl = !formValues.companyLogoUrl?.trim();
        const missJobTitle = !formValues.jobTitle?.trim();

        if(missCompanyName || missCompanyLogoUrl || missJobTitle) {
            this.requiredFieldsMissing.set(true);
            return false;
        }

        this.requiredFieldsMissing.set(false);
        return true;

    }
}