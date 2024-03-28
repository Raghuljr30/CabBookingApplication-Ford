import { CommonModule } from '@angular/common';
import { HttpClientModule } from '@angular/common/http';
import { Component } from '@angular/core';
import { FormsModule } from '@angular/forms';
import { RouterLink, RouterOutlet, RouterLinkActive, RouterModule, Router } from '@angular/router';
import { HomeComponent } from '../home/home.component';
import { RegisterComponent } from '../register/register.component';
import { Drivers } from '../../model/drivers';
import { DriverService } from '../../service/driver.service';

@Component({
  selector: 'app-navbar',
  standalone: true,
  imports: [CommonModule,HttpClientModule,FormsModule,RouterLink,RouterOutlet,RegisterComponent,HomeComponent,RouterLinkActive,RouterModule],
  templateUrl: './navbar.component.html',
  styleUrl: './navbar.component.css'
})

export class NavbarComponent {
  updateDriver: Drivers = new Drivers();
  id: any=0;
  name: any ="";

  constructor(private driverService:DriverService, private router:Router){
    this.id = localStorage.getItem("id");
    
  }
 
  ngOnInit(): void {
    this.driverService.getDriverById(this.id).subscribe({
      next: (data) => {
      localStorage.setItem("id",this.id);
    this.updateDriver = data;
    this.name = this.updateDriver.driverName;
    },
  });
}


  logoutDisplay(){
    if(confirm("Click Ok to confirm logout")) {
    this.router.navigateByUrl("/")

    }
  }

  profile(){
    this.router.navigateByUrl("profile")

  }
}
