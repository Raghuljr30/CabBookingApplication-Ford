import { CommonModule } from '@angular/common';
import { HttpClientModule } from '@angular/common/http';
import { Component } from '@angular/core';
import { FormsModule } from '@angular/forms';
import { Router } from '@angular/router';

import { CabService } from '../../Service/Cab.service';
import { Cab } from '../../Model/Cab';
@Component({
  selector: 'app-register',
  standalone: true,
  imports: [CommonModule,FormsModule,HttpClientModule],
  templateUrl: './register.component.html',
  styleUrl: './register.component.css'
})
export class RegisterComponent {
  newCab: Cab = new Cab();
  message: string = "";
  errorMessage: string = "";
  AC:boolean=false


  constructor(private cabservice: CabService, private router: Router)  { }

  registerNewUser() {
    console.log(this.newCab);
    this.cabservice.registerNewCab(this.newCab)
      .subscribe(
        {
          next: (data: any) => {
            console.log(data);
            this.message = "Cab added";
            this.errorMessage = "";
          },
          error: (err: { status: string; error: string; }) => {
            console.log(err);
              this.errorMessage="Could't add Cab";
            if (err.status == "0")
              this.errorMessage = "Network error, please try again later.";
            else
              this.errorMessage = err.error;

            this.message = ""; 
          }
        }
      );
      
  }


  
             
           

}
