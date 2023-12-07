import { Component, OnDestroy, OnInit } from '@angular/core';
import { DataService } from '../data.service';
import { FormBuilder, FormGroup, FormControl, Validators } from '@angular/forms';
import { ToastrService } from 'ngx-toastr';
import { Router } from '@angular/router';
import { Subscription, debounceTime } from 'rxjs';
import { ROUTES } from '../config/route-config';

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

  /*
   * ngOnInit() Initializes the booking form
   * Sets up subscriptions to detect changes to the pickUpTime and vehicleType
   * Sets up form controls and validators
   */
  
  ngOnInit(): void {
    console.log("Create Booking Component: ngOnInit")
    this.bookingForm = this.fb.group({ 
        pickUpLocation: ['', Validators.required],
        dropOffLocation: ['', Validators.required],
        pickUpTime: ['', [Validators.required, Validators.pattern('^(0[0-9]|1[0-9]|2[0-3]):[0-5][0-9]$')]],
        fare: ['', Validators.required],
        vehicleType: ['', Validators.required]
    });

    this.setValueChangeSubscriptions();
  }

 /*
  * Submits the booking form upon clicking the submit button
  */
  createBooking() {
    console.log("Create Booking Component: Booking Form Submitted")
    this.sub = this.dataService.createBooking(this.bookingForm.value).subscribe(
      (data: any) => {
        this.toastr.success('Booking created successfully', 'Success');
        this.router.navigate([ROUTES.bookings]);
      },
      (error: any) => {
        this.toastr.error('Error occurred while creating booking', 'Error');
        this.toastr.error(error.message, 'Error');
      })
  }

  /*
   * Calculates the fare based on the pickUpTime and vehicleType using Angular's Change Detection
   */
  calculateFare() {
    console.log("Create Booking Component: calculateFare")
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
        this.toastr.info('Pick Up Time Change Detected', 'Info');
        this.calculateFare();
    });

    // Detect changes to the vehicleType
    this.bookingForm.get('vehicleType')?.valueChanges.pipe(
      debounceTime(2000)).subscribe(() => {
        this.toastr.info('Vehichle Type Change Detected', 'Info');
        this.calculateFare();
    });
  }

  /*
   * Unsubscribes from the subscription
   */
  ngOnDestroy() {
    console.log("Create Booking Component: ngOnDestroy")
    if (this.sub) {
      this.sub.unsubscribe();
    }
  }
}
