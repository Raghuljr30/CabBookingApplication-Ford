import { CommonModule } from '@angular/common';
import { HttpClientModule } from '@angular/common/http';
import { Component } from '@angular/core';
import { FormsModule } from '@angular/forms';
import { Customer } from '../../../model/customer';
import { CustomerService } from '../../../service/customer.service';
import { Router } from '@angular/router';

@Component({
  selector: 'app-sign-up',
  standalone: true,
  imports: [CommonModule,FormsModule,HttpClientModule],
  templateUrl: './sign-up.component.html',
  styleUrl: './sign-up.component.css'
})
export class SignUpComponent {
  errorMessage:string='';
  successMessage:string='';
  showSuccessMessage=false;
 
  
  showSuccessPopUp:boolean=false;
  customerId:number=0;
  customer:Customer=new Customer();
 
  

  
  newCustomer:Customer=new Customer();
  constructor(private customerService:CustomerService,private router:Router) {}
  
  
  
 
    addNewCustomer(){
      console.log(this.newCustomer);
      this.customerService.registerCustomer(this.newCustomer).subscribe(
        {
          next:(data)=>{
            console.log(data)
            this.successMessage="Registeration  Successful! Please Login to Continue.Your Login ID ";
            alert(this.successMessage+data.id)
            this.router.navigateByUrl( '/login');
            
          },
          error:(err)=>{
            console.log(err)
            this.errorMessage = "Registration Failed!";
        } 
    }

      )
     this.showSuccessMessage=true;

     

     
  
  
  }

  // OnSubmit(){
  //   successsMessage=true;
    
  // }
      
  }

 

