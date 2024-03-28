import { CommonModule } from '@angular/common';
import { HttpClientModule } from '@angular/common/http';
import { Component } from '@angular/core';
import { FormsModule } from '@angular/forms';
import { DriverService } from '../../service/driver.service';
import { Router, RouterLink, RouterLinkActive, RouterModule, RouterOutlet } from '@angular/router';
import { RegisterComponent } from '../register/register.component';

@Component({
  selector: 'app-home',
  standalone: true,
  imports: [CommonModule,HttpClientModule,FormsModule,RouterLink,RouterOutlet,RegisterComponent,HomeComponent,RouterLinkActive,RouterModule],
  templateUrl: './home.component.html',
  styleUrl: './home.component.css'
})
export class HomeComponent {

  errorMessage:string="";
  successMessage:string="";
  password:string="";
  id:any=0;
  constructor(private driverService:DriverService,private router:Router){
    
  }
  
  loginDriver()
  {
    this.driverService.loginDriver(this.id,this.password).subscribe({
      next:(data)=>{
        localStorage.setItem("id",this.id);
        console.log(data)
        console.log("Id "+this.id);
        this.router.navigateByUrl("/logged-in")
      },
      error:(error)=>{
          console.log(error)
          this.errorMessage="Invalid ID or password"
      }   
     })
  }
}
