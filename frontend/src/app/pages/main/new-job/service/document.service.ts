import { HttpClient } from "@angular/common/http";
import { inject, Injectable } from "@angular/core";
import { environment as env } from "../../../../environments/environment";

@Injectable({
    providedIn: 'root'
})
export class DocumentService {
    private httpClient = inject(HttpClient);

    public upload(document: File,  documentType: string) {
        const url = `${env.BACKEND_BASE_URL}/api/v1/documents`;

        const formData = new FormData();
        formData.append('document', document);
        formData.append('documentType', documentType);
        
        return this.httpClient.post(url, formData, { responseType: 'text' });
    }
}