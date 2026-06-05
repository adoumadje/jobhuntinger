import { Component, ElementRef, inject, OnInit, signal, ViewChild } from "@angular/core";
import { RouterLink, RouterLinkActive } from "@angular/router";
import { UserService } from "../../../services/user.service";
import { User } from "../../../interfaces/user.interface";
import { GoogleAuthService } from "../../login/service/googleAuth.service";

@Component({
    selector: 'app-header',
    standalone: true,
    templateUrl: './header.component.html',
    styleUrl: './header.component.css',
    imports: [RouterLink, RouterLinkActive]
})
export class HeaderComponent implements OnInit {
    @ViewChild('logoutDialog')
    logoutDialog!: ElementRef<HTMLDialogElement>;

    private userService = inject(UserService);
    private googleAuthService = inject(GoogleAuthService);

    user = signal<User | null>(null);

    ngOnInit(): void {
        this.userService.getUser().subscribe({
            next: (user) => {
                this.user.set(user);
            },
            error: (error) => console.error(error)
        })
    }

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

    public logout(email: string) {
        this.userService.cleanUp();
        this.googleAuthService.logout(email);
    }
}