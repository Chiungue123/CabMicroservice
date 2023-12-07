import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http'
import { BehaviorSubject, Observable } from 'rxjs';
import { Booking } from './model/booking.model';
import { environment } from '../environments/environment';

@Injectable({
  providedIn: 'root'
})
export class DataService {
  
  constructor(private http: HttpClient) { }

  createBooking(booking: Booking){
    console.log("Data Service: Create Booking. URL: " + environment.host + "/bookings/create")
    return this.http.post<Booking>(environment.host + '/bookings/create', booking)
  }

  getBookings(): Observable<Booking[]> {
    console.log("Data Service: Get Bookings. URL: " + environment.host + "/bookings")
    return this.http.get<Booking[]>(environment.host + '/bookings')
  }

  getBookingById(id: Number): Observable<Booking> {
    console.log("Data Service: Get Booking, id: " + id)
    return this.http.get<Booking>(environment.host + '/bookings/' + id)
  }

  updateBooking(id: Number, booking: Booking){
    console.log("Data Service: Update Booking, id: " + id)
    return this.http.put<Booking>(environment.host + '/bookings/update/' + id, booking)
  }

  deleteBooking(id: Number){
    console.log("Data Service: Delete Booking, id: " + id)
    return this.http.delete<Booking>(environment.host + '/bookings/delete/' + id)
  }

  calculatePayment(pickUpTime: String, vehicleType: String) {
    console.log("Data Service: Calculating Payment. URL: " + environment.host + '/bookings/calculatePayment/' + pickUpTime)
    console.log("Pick Up Time: " + pickUpTime)
    console.log("Vehicle Type: " + vehicleType)
    return this.http.get<String>(environment.host + '/bookings/payment/' + pickUpTime + '/' + vehicleType)
  }

  currentMode!: 'create' | 'update' | 'create'; // Default to create mode if not set
  currentBooking!: Booking | null; // Default to null if not set

  // Sets the current mode for update or create booking
  setMode(mode: 'create' | 'update', booking?: Booking) {
    this.currentMode = mode;
    this.currentBooking = booking || null;
  }

  // Sets the current user id for search
  private searchIdSource = new BehaviorSubject<number | null>(null);
    currentSearchId = this.searchIdSource.asObservable(); // Observable for search id

  changeSearchId(id: number | null) {
    this.searchIdSource.next(id);
  }

}