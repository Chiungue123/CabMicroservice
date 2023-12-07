import { Component } from '@angular/core';
import { DataService } from '../data.service';
import { ROUTES } from '../config/route-config';
import { Router } from '@angular/router';
import { FormBuilder, FormControl, FormGroup } from '@angular/forms';

@Component({
  selector: 'app-menu',
  templateUrl: './menu.component.html',
  styleUrls: ['./menu.component.css']
})
export class MenuComponent {

  form!: FormGroup;
  id!: Number;

  constructor(private dataService: DataService, private router: Router, private fb: FormBuilder) { }

  ngOnInit(): void {
    this.form = this.fb.group({
      search: [''],
    });
    
    this.form.get('search')?.valueChanges.subscribe((id) => {
      this.onSearchId(id);
    });
  }
  
  onCreate() {
    this.dataService.setMode('create');
    this.router.navigate([ROUTES.createBooking]);
  }

  onSearchId(value: number | null) {
    if (value == null) {
        this.dataService.changeSearchId(null); // Reset the search ID when input is cleared
        this.router.navigate([ROUTES.bookings]);
    } else {
        this.dataService.changeSearchId(value);
        this.router.navigate([ROUTES.bookings]);
    }
  }
}
