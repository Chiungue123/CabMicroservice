import { Component, OnDestroy, OnInit } from '@angular/core';
import { DataService } from '../data.service';
import { FormBuilder, FormGroup, FormControl } from '@angular/forms';
import { ToastrService } from 'ngx-toastr';
import { Router } from '@angular/router';
import { Subscription } from 'rxjs';

@Component({
  selector: 'app-create-booking',
  templateUrl: './create-booking.component.html',
  styleUrls: ['./create-booking.component.css']
})
export class CreateBookingComponent implements OnInit {

  sub!: Subscription;
  bookingForm!: FormGroup;

  constructor(private dataService: DataService, 
              private fb: FormBuilder, 
              private toastr: ToastrService,
              private router: Router) { }

  ngOnInit(): void {
    console.log("Create Booking Component: ngOnInit")
    this.bookingForm = this.fb.group({ 
        pickUpLocation: [''],
        dropOffLocation: [''],
        pickUpTime: [''],
        fare: [''],
        vehicleTpe: ['']
    });
  }

  createBooking() {
    console.log("Create Booking Component: Booking Form Submitted")
    this.sub = this.dataService.createBooking(this.bookingForm.value).subscribe(
      (data: any) => {
        this.toastr.success('Booking created successfully', 'Success');
        this.router.navigate(['/app-bookings']);
      },
      (error: any) => {
        this.toastr.error('Error occurred while creating booking', 'Error');
      })
  }

  /*ngOnDestroy() {
    this.sub.unsubscribe();
  }*/

}
