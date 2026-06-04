import { Component } from "@angular/core";
import { PagerComponent } from "./pager/pager.component";

@Component({
    selector: 'app-dashboard',
    standalone: true,
    templateUrl: './dashboard.component.html',
    styleUrl: './dashboard.component.css',
    imports: [PagerComponent]
})
export class DashboardComponent {

}