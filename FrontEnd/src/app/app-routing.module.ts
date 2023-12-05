import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { MenuComponent } from './menu/menu.component';
import { BookingsComponent } from './bookings/bookings.component';
import { CreateBookingComponent } from './create-booking/create-booking.component';
import { UpdateBookingComponent } from './update-booking/update-booking.component';
import { HttpClientModule } from '@angular/common/http';

const routes: Routes = [
  { path: 'app-bookings', component: BookingsComponent},
  { path: 'bookings/:id', component: BookingsComponent},
  { path: 'app-create-booking', component: CreateBookingComponent},
  { path: 'bookings/update', component: UpdateBookingComponent},
  { path: '', component: MenuComponent}

];

@NgModule({
  imports: [RouterModule.forRoot(routes), HttpClientModule],
  exports: [RouterModule]
})
export class AppRoutingModule {
  
 }
