import { HttpClient, HttpParams } from "@angular/common/http";
import { inject, Injectable } from "@angular/core";
import { JobFilters } from "../dashboard/interface/job-filter.interface";
import { environment as env } from "../../../environments/environment";
import { JobPage } from "../dashboard/interface/job-page.interface";

@Injectable({
    providedIn: 'root'
})
export class JobService {
    private httpClient = inject(HttpClient);

    public getJobs(jobFilters: JobFilters) {
        const url = `${env.BACKEND_BASE_URL}/api/v1/jobs`;

        let params = new HttpParams()
            .set('pageNumber', jobFilters.pageNumber)
            .set('rows', jobFilters.rows);

        if(jobFilters.keyword) {
            params.set('keyword', jobFilters.keyword);
        }

        if(jobFilters.toDate) {
            params.set('toDate', jobFilters.toDate);
        }

        return this.httpClient.get<JobPage>(url, { params });
    }
}