import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http'
import { Observable } from 'rxjs';
import { Booking } from './model/booking.model';
import { environment } from '../environments/environment';

@Injectable({
  providedIn: 'root'
})
export class DataService {

  constructor(private http: HttpClient) { }

  createBooking(booking: Booking){
    console.log("Data Service: Create Booking")
    return this.http.post<Booking>(environment.host + 'bookings/create', booking)
  }

  getBookings(): Observable<Booking[]> {
    console.log("Data Service: Get Bookings. URL: " + environment.host + "/bookings")
    return this.http.get<Booking[]>(environment.host + '/bookings')
  }

  getBookingById(id: Number): Observable<Booking> {
    console.log("Data Service: Get Booking, id: " + id)
    return this.http.get<Booking>(environment.host + '/bookings/' + id)
  }

  updateBooking(booking: Booking){
    console.log("Data Service: Update Booking, id: " + booking.id)
    return this.http.put<Booking>(environment.host + '/bookings/update', booking)
  }

  deleteBooking(id: Number){
    console.log("Data Service: Delete Booking, id: " + id)
    return this.http.delete<Booking>(environment.host + '/bookings/delete/' + id)
  }

}