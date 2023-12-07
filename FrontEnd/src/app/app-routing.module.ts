import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { BookingsComponent } from './bookings/bookings.component';
import { CreateBookingComponent } from './create-booking/create-booking.component';
import { UpdateBookingComponent } from './update-booking/update-booking.component';
import { HttpClientModule } from '@angular/common/http';
import { ROUTES } from './config/route-config';

const routes: Routes = [
  { path: ROUTES.bookings, component: BookingsComponent },
  { path: ROUTES.bookingDetail, component: BookingsComponent },
  { path: ROUTES.createBooking, component: CreateBookingComponent },
  { path: ROUTES.updateBooking, component: UpdateBookingComponent }
];

@NgModule({
  imports: [RouterModule.forRoot(routes), HttpClientModule],
  exports: [RouterModule]
})
export class AppRoutingModule {
  
 }
