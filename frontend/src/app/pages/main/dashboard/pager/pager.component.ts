import { Component, EventEmitter, Input, Output } from "@angular/core";

@Component({
    selector: 'app-pager',
    standalone: true,
    templateUrl: './pager.component.html',
    styleUrl: './pager.component.css'
})
export class PagerComponent {
    @Input({ required: true }) currentPage!: number;
    @Input({ required: true }) totalPages!: number;  
    @Output() pageChange = new EventEmitter();

    get visiblePages() {
        const winSize = 5;

        let start = Math.max(0, this.currentPage - Math.floor(winSize/2));
        let end = start + winSize-1;

        if(end >= this.totalPages) {
            start = Math.max(0, this.totalPages-winSize);
            end = this.totalPages-1;
        }

        return Array.from({ length: end-start+1 },
            (_, i) => start+i
        );
    }

    public goToPage(pageNumber: number) {
        this.pageChange.emit(pageNumber);
    }
}