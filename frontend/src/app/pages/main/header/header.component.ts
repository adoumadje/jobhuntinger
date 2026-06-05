import { Component, ElementRef, ViewChild } from "@angular/core";
import { RouterLink, RouterLinkActive } from "@angular/router";

@Component({
    selector: 'app-header',
    standalone: true,
    templateUrl: './header.component.html',
    styleUrl: './header.component.css',
    imports: [RouterLink, RouterLinkActive]
})
export class HeaderComponent {
    @ViewChild('logoutDialog')
    logoutDialog!: ElementRef<HTMLDialogElement>;

    public openLogoutDialog() {
        this.logoutDialog.nativeElement.showModal()
    }

    public closeLogoutDialog() {
        this.logoutDialog.nativeElement.close()
    }

    public onDialogClick(event: MouseEvent): void {
    if (event.target === this.logoutDialog.nativeElement) {
        this.logoutDialog.nativeElement.close();
    }
}

    public logout() {

    }
}