import { Component } from '@angular/core';
import { DriverService } from '../../service/driver.service';
import { Router, RouterLink, RouterLinkActive, RouterModule, RouterOutlet } from '@angular/router';
import { Drivers } from '../../model/drivers';
import { CommonModule, NgFor } from '@angular/common';
import { HttpClientModule } from '@angular/common/http';
import { FormsModule } from '@angular/forms';
import { HomeComponent } from '../home/home.component';
import { RegisterComponent } from '../register/register.component';
import { NavbarComponent } from '../navbar/navbar.component';
import { Cabs } from '../../model/cabs';
import { Bookings } from '../../model/bookings';
import { Reviews } from '../../model/reviews';

@Component({
  selector: 'app-logged-in',
  standalone: true,
  imports: [CommonModule,HttpClientModule,FormsModule,RouterLink,RouterOutlet,RegisterComponent,HomeComponent,RouterLinkActive,RouterModule,NavbarComponent],
  templateUrl: './logged-in.component.html',
  styleUrl: './logged-in.component.css'
})

export class LoggedInComponent {
 id:any=0;
 errorMessage:string="";
 successMessage:string="";
 updateDriver: Drivers = new Drivers();
 cabs:Cabs[]=[];
bookings: Bookings[]=[];
reviews:Reviews[]=[];
newNumber:number=0
isbooked:boolean=false
isPhoneNumberUpdated:boolean=false
newPassword: string="";
  isPasswordUpated: boolean=false;
  agencyName:string="";
  isempty: boolean=false;
  

  constructor(private driverService:DriverService,private router:Router){ 
  this.id = localStorage.getItem("id");
  }

  ngOnInit(): void {
    this.driverService.getDriverById(this.id).subscribe({
    next: (data) => {
    localStorage.setItem("id",this.id);
    this.updateDriver = data;
   console.log(this.updateDriver)
   this.showBookings()
   this.showReviews()
   
    },
  });
}
    


  showBookings() 
 {
    this.driverService.showBookings(this.id!).subscribe({
     next:(data)=>
     {
      console.log(data);
      this.bookings = data;
      this.cabs= data;
     if(data==null)
     {
      this.isempty=true;
     }
     }

    })
  }

  showReviews()
  {
    this.driverService.showReviews(this.id).subscribe({
      next:(data)=>
      {
        console.log(data);
        this.reviews = data;
      }
    })
  }


  showDrivers()
  {
    this.driverService.otherDrivers(this.agencyName).subscribe({
      next:(data)=>
      {
        
        console.log(data);
      
      }
    })
  }

  
}