import { CommonModule, formatDate } from '@angular/common';
import { HttpClientModule } from '@angular/common/http';
import { ActivatedRoute, Router } from '@angular/router';
import { Component } from '@angular/core';
import { FormsModule } from '@angular/forms';
import { Customer } from '../../../model/customer';
import { CustomerService } from '../../../service/customer.service';
import { Booking } from '../../../model/booking';

@Component({
  selector: 'app-display-customer',
  standalone: true,
  imports: [HttpClientModule, CommonModule, FormsModule],
  templateUrl: './display-customer.component.html',
  styleUrl: './display-customer.component.css'
})
export class DisplayCustomerComponent {

  updateMobile:boolean=false;
  updatePassword:boolean=false;
  viewCustomerProfile:boolean=false;
  viewCustomerBookings:boolean=true;
  customerReview:boolean=false;
  customerPayment:boolean=false;

  customerBooking:Booking[]=[];
  successMessage:string='';
  currentDate: string = formatDate(new Date(), 'yyyy-MM-dd', 'en-US');
  
  
 


  customerId?: number = 0;
  selectedCustomer:Customer= new Customer();
  customerNewMobileNumber: number = 0;
  updateMobileButtonClicked: boolean = false;
  customerNewPassword:string='';
  updatePasswordButtonClicked: boolean = false;
  postReviewClicked:boolean=false;
  maskedPassword:string='';
  bookingId?:number=0;
  review?:string="";
  
 

  constructor(private customerService: CustomerService,private router:Router,private activatedRouter:ActivatedRoute) {
   this.customerId=parseInt(this.activatedRouter.snapshot.paramMap.get("id")!);
    
    
    this.customerService.viewCustomer(this.customerId).subscribe({
      next:(data)=>{
        console.log(data);
        
        this.selectedCustomer=data
        this.viewBooking();
        
      }
    
    })

    
  }

    

  loadAllCustomers() {
    this.customerService.ViewCustomers().subscribe(
      {
        next: (data) => {
          console.log(data);
          this.customers = data;
          if (this.selectedCustomer.password!=undefined)
        this.maskedPassword='*'.repeat(this.selectedCustomer.password.length)
        },
        complete: () => {
          console.log("completed");
        }
      }

    )
  }
  


  customers: Customer[] = [];
  deleteCustomer(id?: number) {
    console.log(id);
    if (id != undefined)
      this.customerService.deleteCustomer(id).subscribe({
        next: (data) => {
          console.log(data);
          this.loadAllCustomers();
          this.router.navigateByUrl("signUp")
        },
        error: (err) => {
          console.log(err);
        }
      })
  }

  updateMobileNumberClicked(id?:number) {
    this.viewCustomerProfile=false;
  this.updateMobile=true;
  this.updatePassword=false;
  
  this.viewCustomerBookings=false;
  this.customerReview=false;
  this.customerPayment=false;
    console.log("button clicked");
    this.customerId=id;
    this.updateMobileButtonClicked = true;

  }
  updateMobileNumber() {
    console.log("mobile number updated")
    
    
    let customerDto = { id: this.customerId!, mobileNumber: this.customerNewMobileNumber! };
    console.log(customerDto);
    this.customerService.updateCustomerMobile(customerDto).subscribe({
      next: (data) => {
        console.log(data);
        this.loadAllCustomers();
      }

    })
  }
  updatePasswordClicked(password:string) {
    this.viewCustomerProfile=false;
    this.updateMobile=false;
    this.updatePassword=true;
    
    this.viewCustomerBookings=false;
    this.customerReview=false;
    this.customerPayment=false;
    console.log("button clicked for password update");
    this.customerNewPassword = password;
    this.updateMobileButtonClicked=false;
    this.updatePasswordButtonClicked = true;
  }
  updateCustomerPassword() {
  
    
    // const customerId = this.customer.id!;
    let customerDto={id:this.customerId!,password:this.customerNewPassword!};
    console.log(customerDto)
    this.customerService.updateCustomerPassword(customerDto).subscribe({
      next: (data) => {
        console.log(data);
     
        
        this.loadAllCustomers();
      },
      error: (err) => {
        console.log(err);

      }
    })

  }
  loggingOff(){
    console.log("loggingOFf")
    this.router.navigateByUrl("login");
  }

viewProfile(customerId?:number){
  console.log("profile of customer")
  this.viewCustomerProfile=true;
  this.updateMobile=false;
  this.updatePassword=false;
  
  this.viewCustomerBookings=false;
  this.customerReview=false;
  this.customerPayment=false;
  
  
 
    this.customerId = customerId;
    this.customerService.viewCustomer(customerId!)
      .subscribe(customer => {
        this.selectedCustomer = customer;
        if (this.selectedCustomer.password!=undefined)
        this.maskedPassword='*'.repeat(this.selectedCustomer.password.length)
      });
    
  
}

postReview(){
  this.updateMobileButtonClicked=false;
  this.updatePasswordButtonClicked=false;
  this.postReviewClicked=true;
  this.customerReview=true;
  this.viewCustomerProfile=false;
  this.viewCustomerBookings=false;

  

}
 submitReview(){
  
  console.log("review for booking");
  this.customerService.customerReviews(this.selectedCustomer.id!,this.bookingId!,this.review!).subscribe({
    next:(data)=>{
      console.log(data)
      this.successMessage= "Your review has been submitted successfully";
      
    },
    error:(err)=>{
      console.log(err)
    }
  })
}

viewBooking(){
  this.viewCustomerProfile=false;
  this.updateMobile=false;
  this.updatePassword=false;
  
  this.viewCustomerBookings=true;
  this.customerReview=false;
  this.customerPayment=false;

  this.customerService.bookingsOfCustomer(this.selectedCustomer.id!).subscribe({
    next:(data)=>{
      console.log(data)
      this.customerBooking=data;
    },
    error:(err)=>{
      console.log(err);

    }

  })
}







}
