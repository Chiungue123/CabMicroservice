import { Component } from '@angular/core';
import { Booking } from '../model/booking.model';
import { DataService } from '../data.service';
import { Observable } from 'rxjs';
import { HttpClient } from '@angular/common/http';

@Component({
  selector: 'app-bookings',
  templateUrl: './bookings.component.html',
  styleUrls: ['./bookings.component.css']
})
export class BookingsComponent {

  bookings: Booking[] = []

  constructor(private dataService: DataService, private http: HttpClient) { }

  ngOnInit(): void {
    console.log("Bookings Component: ngOnInit")
    this.getBookings().subscribe((data: Booking[]) => {
      console.log(data);
      this.bookings = data;
    });
  }
  
  getBookings() {
    console.log("Data Service: Get Bookings")
    return this.dataService.getBookings();
  }
}