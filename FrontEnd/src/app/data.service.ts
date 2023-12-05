import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http'
import { Observable } from 'rxjs';
import { Booking } from './model/booking.model';

@Injectable({
  providedIn: 'root'
})
export class DataService {

  constructor(private http: HttpClient) { }

  createBooking(booking: Booking){
    console.log("Data Service: Create Booking")
    return this.http.post<Booking>('http://localhost:8765/bookings/create', booking)
  }

  getBookings(): Observable<Booking[]> {
    console.log("Data Service: Get Bookings")
    return this.http.get<Booking[]>('http://localhost:8765/bookings')
  }

  getBookingById(id: Number): Observable<Booking> {
    console.log("Data Service: Get Booking, id: " + id)
    return this.http.get<Booking>('http://localhost:8765/bookings/${id}')
  }

  updateBooking(booking: Booking){
    console.log("Data Service: Update Booking, id: " + booking.id)
    return this.http.post<Booking>('http://localhost:8765/bookings/update', booking)
  }

  deleteBooking(id: Number){
    console.log("Data Service: Delete Booking, id: " + id)
    return this.http.post<Booking>('http://localhost:8765/bookings/delete', id)
  }

}