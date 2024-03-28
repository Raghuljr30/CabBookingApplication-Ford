import { CommonModule } from '@angular/common';
import { HttpClientModule } from '@angular/common/http';
import { Component, OnInit } from '@angular/core';
import { FormsModule } from '@angular/forms';
import { ActivatedRoute, Router, RouterLink, RouterLinkActive, RouterModule, RouterOutlet } from '@angular/router';
import { RegisterComponent } from '../register/register.component';
import { HomeComponent } from '../home/home.component';
import { DriverService } from '../../service/driver.service';
import { Drivers } from '../../model/drivers';

@Component({
  selector: 'app-dashboard',
  standalone: true,
  imports: [CommonModule,HttpClientModule,FormsModule,RouterLink,RouterOutlet,RegisterComponent,HomeComponent,RouterLinkActive,RouterModule],
  templateUrl: './dashboard.component.html',
  styleUrl: './dashboard.component.css'
})
export class DashboardComponent{

errorMessage:string="";
successMessage:string="";
agencyId: any=0;
agencyname: any="";
id: any=0;
driver:Drivers=new Drivers();
updateDriver: Drivers = new Drivers();
activatedRoute: any;
  
  constructor(private driverService:DriverService,private router:Router,private route: ActivatedRoute){
  //   this.id=this.activatedRoute.snapshot.paramMap.get('id');

  this.id = localStorage.getItem("id");
  console.log(this.id);
  }


  ngOnInit(): void {
    this.driverService.getDriverById(this.id).subscribe({
    next: (data) => {
    console.log(data);
    this.updateDriver = data;
    this.agencyId = this.updateDriver.driverAgencyId;
    this.agencyname = this.updateDriver.driverAgencyName;

    console.log(this.agencyId);
    },
  });
}

  logoutDisplay(){
    if(confirm("Click Ok to confirm logout")) {
      console.log("Implement delete functionality here");
    this.router.navigateByUrl("/")

    }
  }

 
 

changeAgency(driverId:number,newAgency:string,newAgencyId:number) {
  this.driverService.updateAgency(this.id,this.agencyId,this.agencyname).subscribe({    
    next: (data) => {
      console.log(data);
      this.updateDriver = data;
      this.updateDriver.driverAgencyId= newAgencyId;
      this.updateDriver.driverAgencyName=newAgency;
  
      console.log(this.updateDriver);
    },
    error:(error) => {
      console.error('Error updating password:', error);
    }
  });
}

 
updateAgencyDetails() {
throw new Error('Method not implemented.');
}
updatePassword() {
throw new Error('Method not implemented.');
}

}
