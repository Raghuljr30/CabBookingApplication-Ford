import { Component } from '@angular/core';
import { Drivers } from '../../model/drivers';
import { DriverService } from '../../service/driver.service';
import { CommonModule } from '@angular/common';
import { FormsModule } from '@angular/forms';
import { HttpClientModule } from '@angular/common/http';
import { Router } from '@angular/router';


@Component({
  selector: 'app-display',
  standalone: true,
  imports: [CommonModule,FormsModule,HttpClientModule],
  templateUrl: './display.component.html',
  styleUrl: './display.component.css'
})
export class DisplayComponent {

  drivers:Drivers[]=[];
  constructor(private driverService:DriverService, private router:Router){
    this.driverService.listAllDrivers().subscribe(
      {
        next:(data)=>{
          console.log(data);
          this.drivers = data;
        },
        error:(error)=>{
          console.log(error);
        },
        complete:()=>{
          console.log("Completed data loading");
        }
      }
    )
  }

 


}
