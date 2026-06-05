import { Component } from "@angular/core";
import { PagerComponent } from "./pager/pager.component";
import { RouterLink } from "@angular/router";

@Component({
    selector: 'app-dashboard',
    standalone: true,
    templateUrl: './dashboard.component.html',
    styleUrl: './dashboard.component.css',
    imports: [PagerComponent, RouterLink]
})
export class DashboardComponent {

}