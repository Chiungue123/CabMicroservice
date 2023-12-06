import { Component } from '@angular/core';
import { Booking } from '../model/booking.model';
import { DataService } from '../data.service';
import { Observable } from 'rxjs';
import { ToastrService } from 'ngx-toastr';
import { HttpClient } from '@angular/common/http';

@Component({
  selector: 'app-bookings',
  templateUrl: './bookings.component.html',
  styleUrls: ['./bookings.component.css']
})
export class BookingsComponent {

  bookings: Booking[] = []

  constructor(private dataService: DataService, private http: HttpClient, private toastr: ToastrService) { }

  ngOnInit(): void {
    console.log("Bookings Component: ngOnInit")
    this.getBookings().subscribe(
      (data: Booking[]) => {
        console.log("Bookings: " + data);
        this.bookings = data;
        this.toastr.success('Bookings retrieved successfully', 'Success');
      });
  }
  
  getBookings() {
    console.log("Data Service: Get Bookings")
    return this.dataService.getBookings();
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