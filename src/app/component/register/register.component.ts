import { Component } from '@angular/core';
import { Drivers } from '../../model/drivers';
import { FormsModule } from '@angular/forms';
import { HttpClientModule } from '@angular/common/http';
import { CommonModule } from '@angular/common';
import { DriverService } from '../../service/driver.service';
import { Router, RouterLink, RouterLinkActive, RouterModule, RouterOutlet } from '@angular/router';
import { HomeComponent } from '../home/home.component';
import { NavbarComponent } from '../navbar/navbar.component';

@Component({
  selector: 'app-register',
  standalone: true,
  imports: [CommonModule,HttpClientModule,FormsModule,RouterLink,RouterOutlet,RegisterComponent,HomeComponent,RouterLinkActive,RouterModule,NavbarComponent],
  templateUrl: './register.component.html',
  styleUrl: './register.component.css'
})
export class RegisterComponent {
  newDriver: Drivers = new Drivers();
  errorMessage: string = "";
  success:string="";
  selectedAgency: string="";
  selectedAgencyId: number=0;
  isRegistered:boolean=false;

  constructor(private driverService: DriverService,private router: Router){}

  addNewDriver(){
    console.log(this.newDriver);
 
    this.driverService.registerNewDriver(this.newDriver).subscribe(
      {
        next:(data)=>{
          
          console.log(data)   
          this.success = "Welcome "+data.driverName+"!\n Kindly note your Login ID: " + data.driverId 
          this.isRegistered=true
        },
        
        error:(err: { error: string; })=>{
          console.log(err);
          console.log(err.error);
          this.errorMessage=err.error;
          this.success="";
          
        }
      }
    );
    
  }
  redirect()
  {
    this.router.navigateByUrl("/home")
  }


  updateOtherField(): void {
    this.selectedAgencyId = this.selectedAgency === 'Ola' ? 1 : this.selectedAgency === 'Uber' ? 2 :this.selectedAgency === 'Rapido' ? 3 :this.selectedAgency === 'Namma Yatra' ? 4 :this.selectedAgency === 'CabKaro' ? 5 : this.selectedAgency === 'UberLite' ? 6 : this.selectedAgency === 'Yatri Sathi' ? 7: 0  ;
    this.newDriver.driverAgencyId=this.selectedAgencyId;
    this.newDriver.driverAgencyName=this.selectedAgency;
    
  }
  
   
  }

