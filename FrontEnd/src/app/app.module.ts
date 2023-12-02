import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { MenuComponent } from './menu/menu.component';
import { CreateBookingComponent } from './create-booking/create-booking.component';
import { BookingsComponent } from './bookings/bookings.component';
import { UpdateBookingComponent } from './update-booking/update-booking.component';

@NgModule({
  declarations: [
    AppComponent,
    MenuComponent,
    CreateBookingComponent,
    BookingsComponent,
    UpdateBookingComponent
  ],
  imports: [
    BrowserModule,
    AppRoutingModule
  ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule { }
