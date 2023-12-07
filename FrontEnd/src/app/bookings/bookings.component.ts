import { Component } from '@angular/core';
import { Booking } from '../model/booking.model';
import { DataService } from '../data.service';
import { Observable } from 'rxjs';
import { ToastrService } from 'ngx-toastr';
import { HttpClient } from '@angular/common/http';
import { ROUTES } from '../config/route-config';
import { Router } from '@angular/router';

@Component({
  selector: 'app-bookings',
  templateUrl: './bookings.component.html',
  styleUrls: ['./bookings.component.css']
})
export class BookingsComponent {

  searchId!: Number | null;
  filteredBookings: Booking[] = [];
  bookings: Booking[] = [];

  constructor(private dataService: DataService, private http: HttpClient, private toastr: ToastrService, private router: Router) { }

  ngOnInit(): void {
    this.getBookings().subscribe(
      (data: Booking[]) => {
        console.log("Bookings: ", data);
        this.bookings = data;
        this.filteredBookings = this.bookings;
      });
    
    this.dataService.currentSearchId.subscribe(searchId => {
      if (searchId == null) {
        this.filteredBookings = this.bookings;
      } else {
        this.filteredBookings = this.bookings.filter(booking => booking.id === searchId);
      }
   });
}

  getBookings() {
    return this.dataService.getBookings();
  }

  onUpdate(id: Number) {
    this.dataService.setMode('update', this.bookings.find(booking => booking.id === id));
    this.router.navigate([ROUTES.createBooking]);
  }

  onDelete(id: Number) {
    if (confirm("Are you sure you want to delete Booking " + id + "?")) {
      this.dataService.deleteBooking(id).subscribe(
        () => {
          this.toastr.success('Booking deleted successfully', 'Success');
          this.refresh();
        },
        error => {
          this.toastr.error('Error occurred while deleting booking', 'Error');
        }
      );
    } else {
      this.toastr.info('Delete cancelled', 'Info');
    }
  }

  refresh() {
    this.getBookings().subscribe(
      (data: Booking[]) => {
        console.log("Bookings: " + data);
        this.bookings = data;
      });
  }
}