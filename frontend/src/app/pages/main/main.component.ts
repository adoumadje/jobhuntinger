import { Component } from "@angular/core";
import { HeaderComponent } from "./header/header.component";

@Component({
    selector: 'app-main',
    standalone: true,
    templateUrl: './main.component.html',
    imports: [HeaderComponent]
})
export class MainComponent {

}