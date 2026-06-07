export interface JobRegistrationRequest {
    companyName: string;
    companyLogoUrl: string;
    jobTitle: string;
    jobDescriptionText?: string;
    jobDescriptionWebsiteUrl?: string;
    jobDescriptionDocumentUrl?: string;
    resumeUrl: string;
    resumeName: string;
}