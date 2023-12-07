import { Component, OnDestroy, OnInit } from '@angular/core';
import { DataService } from '../data.service';
import { FormBuilder, FormGroup, FormControl, Validators } from '@angular/forms';
import { ToastrService } from 'ngx-toastr';
import { Router } from '@angular/router';
import { Subscription, debounceTime } from 'rxjs';
import { ROUTES } from '../config/route-config';
import { Booking } from '../model/booking.model';

@Component({
  selector: 'app-create-booking',
  templateUrl: './create-booking.component.html',
  styleUrls: ['./create-booking.component.css']
})
export class CreateBookingComponent implements OnInit {

  sub!: Subscription;
  bookingForm!: FormGroup;
  mode!: String;
  booking!: Booking;

  constructor(private dataService: DataService, 
              private fb: FormBuilder, 
              private toastr: ToastrService,
              private router: Router) { }

  /*
   * ngOnInit() Initializes the booking form
   * Sets up subscriptions to detect changes to the pickUpTime and vehicleType
   * Sets up form controls and validators
   */
  
  ngOnInit(): void {
    this.bookingForm = this.fb.group({ 
        pickUpLocation: ['', Validators.required],
        dropOffLocation: ['', Validators.required],
        pickUpTime: ['', [Validators.required, Validators.pattern('^(0[0-9]|1[0-9]|2[0-3]):[0-5][0-9]$')]],
        fare: ['', Validators.required],
        vehicleType: ['', Validators.required]
    });

    this.setValueChangeSubscriptions();

    this.mode = this.dataService.currentMode;
    if (this.dataService.currentBooking) {
      this.booking = this.dataService.currentBooking;
      this.bookingForm.patchValue({
        pickUpLocation: this.booking.pickUpLocation,
        dropOffLocation: this.booking.dropOffLocation,
        pickUpTime: this.booking.pickUpTime,
        fare: this.booking.fare,
        vehicleType: this.booking.vehicleType
      });
    }
  }

 /*
  * Submits the booking form upon clicking the submit button
  */
  onSubmit() {
    if (this.mode === 'create') {
      this.createBooking();
    } else {
      this.updateBooking();
    }
  }

  createBooking() {
    this.sub = this.dataService.createBooking(this.bookingForm.value).subscribe(
      (data: any) => {
        this.toastr.success('Booking Created Successfully', 'Success');
        this.router.navigate([ROUTES.bookings]);
      },
      (error: any) => {
        this.toastr.error('Error Creating Booking', 'Error');
        this.toastr.error(error.message, 'Error');
      })
  }

  updateBooking(){
    this.sub = this.dataService.updateBooking(this.booking.id, this.bookingForm.value).subscribe(
      (data: any) => {
        this.toastr.success('Booking Updated Successfully', 'Success');
        this.router.navigate([ROUTES.bookings]);
      },
      (error: any) => {
        this.toastr.error('Error Updating Booking', 'Error');
        this.toastr.error(error.message, 'Error');
      })
  }

  /*
   * Calculates the fare based on the pickUpTime and vehicleType using Angular's Change Detection
   */
  calculateFare() {
    const pickUpTime = this.bookingForm.get('pickUpTime')?.value;
    const vehicleType = this.bookingForm.get('vehicleType')?.value;

    // Only calculate fare if both pickUpTime and vehicleType are set properly
    if (pickUpTime && vehicleType) {
      this.sub = this.dataService.calculatePayment(pickUpTime, vehicleType).subscribe(
        (data: String) => {
          this.bookingForm.patchValue({
            fare: data
          });
        },
        (error: any) => {
          this.toastr.error('Error occurred while calculating fare', 'Error');
          this.toastr.error(error.message, 'Error');
      })
    }
  }
 
  /*
   * Sets up subscriptions to detect changes to the pickUpTime and vehicleType
   */
  setValueChangeSubscriptions() {
    // Detect changes to the pickUpTime
    this.bookingForm.get('pickUpTime')?.valueChanges.pipe(
      debounceTime(2000)).subscribe(value => {
        this.calculateFare();
    });

    // Detect changes to the vehicleType
    this.bookingForm.get('vehicleType')?.valueChanges.pipe(
      debounceTime(2000)).subscribe(() => {
        this.calculateFare();
    });
  }

  /*
   * Unsubscribes from the subscription
   */
  ngOnDestroy() {
    if (this.sub) {
      this.sub.unsubscribe();
    }
  }
}
