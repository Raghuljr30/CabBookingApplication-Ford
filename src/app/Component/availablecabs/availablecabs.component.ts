import { Component } from '@angular/core';
import { Cab } from '../../Model/Cab';
import { CommonModule } from '@angular/common';
import { CabService } from '../../Service/Cab.service';
import { Router } from '@angular/router';

@Component({
  selector: 'app-availablecabs',
  standalone: true,
  imports: [CommonModule],
  templateUrl: './availablecabs.component.html',
  styleUrl: './availablecabs.component.css'
})
export class AvailablecabsComponent {
updateCab(cabId: number|undefined) {

this.Router.navigate(["update-cab" ,cabId]);
}

  cabs: Cab[] = [];

    message: string = "";
    errorMessage: string = "";
  
    constructor(private cabService: CabService, private Router: Router) {
  
      this.cabService.AvailableCabs()
      .subscribe(
        {
          next: (data: any) => {
            console.log(data);
            this.cabs = data;
            console.log("success");
          },
          error: (err: any) => {
            console.log(err);
            this.errorMessage = "Could't Load cabs";
            this.message = "";
          },
          complete: () => {
            console.log("Server completed sending data.");
  
          }
        }
      )
      
      
      
      
        
}
}




