import { Component } from '@angular/core';
import { DataService } from '../data.service';

@Component({
  selector: 'app-create-booking',
  templateUrl: './create-booking.component.html',
  styleUrls: ['./create-booking.component.css']
})
export class CreateBookingComponent {

  constructor(private dataService: DataService) { }

  ngOnInit(): void {
    console.log("Create Booking Component: ngOnInit")
  }

  createBooking(bookingForm: any) {
    console.log("Create Booking Component: Create Booking")
    console.log(bookingForm.value)
    this.dataService.createBooking(bookingForm.value).subscribe((data: any) => {
      console.log(data)
    })
  }

}
