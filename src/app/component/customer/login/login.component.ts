import { CommonModule, formatDate } from '@angular/common';
import { Component } from '@angular/core';
import { ActivatedRoute, Router, RouterLink } from '@angular/router';
import { CustomerService } from '../../../service/customer.service';

import { HttpClientModule } from '@angular/common/http';
import { FormsModule } from '@angular/forms';
import { Customer } from '../../../model/customer';

@Component({
  selector: 'app-login',
  standalone: true,
  imports: [CommonModule,HttpClientModule,FormsModule,RouterLink],
  templateUrl: './login.component.html',
  styleUrl: './login.component.css'
})
export class LoginComponent {
  id:number=0;
  password:string='';
  errorMessage:string='';
  successMessage:string='';
  
  
  

 
  constructor(private customerService:CustomerService,private router:Router,private route:ActivatedRoute){
    
  }
  

    login():void {
      this.customerService.loginCustomer(this.id,this.password).subscribe({
        next:(data)=>{
          console.log(data)   
          this.successMessage="login successful"   
    this.router.navigateByUrl('/display/'+this.id);
        },
        error:(error)=>{
          console.log(error)
          this.errorMessage="Invalid Id or Password"
      }
    })
      
    }
    clearErrorMessage():void{
      this.errorMessage='';
    }

}
