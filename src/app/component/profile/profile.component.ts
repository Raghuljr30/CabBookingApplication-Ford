import { Component } from '@angular/core';
import { DriverService } from '../../service/driver.service';
import { Router, RouterLink, RouterLinkActive, RouterModule, RouterOutlet } from '@angular/router';
import { Drivers } from '../../model/drivers';
import { CommonModule } from '@angular/common';
import { HttpClientModule } from '@angular/common/http';
import { FormsModule } from '@angular/forms';
import { HomeComponent } from '../home/home.component';
import { NavbarComponent } from '../navbar/navbar.component';
import { RegisterComponent } from '../register/register.component';

@Component({
  selector: 'app-profile',
  standalone: true,
  imports: [CommonModule,HttpClientModule,FormsModule,RouterLink,RouterOutlet,RegisterComponent,HomeComponent,RouterLinkActive,RouterModule,NavbarComponent],
  templateUrl: './profile.component.html',
  styleUrl: './profile.component.css'
})
export class ProfileComponent {


   id:any=0;
 errorMessage:string="";
 successMessage:string="";
 updateDriver: Drivers = new Drivers();
newNumber:number=0
isPhoneNumberUpdated:boolean=false
newPassword:string="";
isPasswordUpated: boolean=false;
isPassword:boolean=false;
isNumber: boolean=false;
isUpdateClicked:boolean=false
isAgency:boolean=false
selectedAgencyId:number=0;
selectedAgency:string=""


  constructor(private driverService:DriverService,private router:Router){ 
    this.id = localStorage.getItem("id");
    }

    ngOnInit(): void {
      this.driverService.getDriverById(this.id).subscribe({
      next: (data) => {
      localStorage.setItem("id",this.id);
      this.updateDriver = data;
     console.log(this.updateDriver)

      },
    });
  }

  Agency() {
    this.isAgency=true
    }
    

  update() {
    this.isUpdateClicked=true
    }

  
  Number(){
    this.isNumber=true
    // this.updateNumber()
    this.isPhoneNumberUpdated=false
  }

  Password(){
    this.isPassword=true
    // this.updatePassword()
    
  }


  updateNumber()
  {
    this.driverService.updatePhNumber(this.id,this.newNumber).subscribe({
      next:(data)=>
      { 
        this.isPhoneNumberUpdated=true
        localStorage.setItem("id",this.id);
        console.log(this.id)
        console.log(data);
        this.updateDriver=data;
        alert("Your mobile number has been successfully updated")
        this.isNumber=false;
  
      }
    })
  }

  updatePassword()
  {
    this.driverService.updatePassword(this.id,this.newPassword).subscribe({
      next:(data)=>
      {
        this.isPassword=true
        this.isPasswordUpated=true
        localStorage.setItem("id",this.id);
        console.log(data);
        this.updateDriver=data;
        alert("Your password has been successfully updated")
      }
    })
  }

  updateAgency()
  {
    this.selectedAgencyId = this.selectedAgency === 'Ola' ? 1 : this.selectedAgency === 'Uber' ? 2 :this.selectedAgency === 'Rapido' ? 3 :this.selectedAgency === 'Namma Yatra' ? 4 :this.selectedAgency === 'CabKaro' ? 5 : this.selectedAgency === 'UberLite' ? 6 : this.selectedAgency === 'Yatri Sathi' ? 7: 0  ;
    this.updateDriver.driverAgencyId=this.selectedAgencyId;
    this.updateDriver.driverAgencyName=this.selectedAgency;
    alert("Your Agency has been successfully updated")
  }

}
