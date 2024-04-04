import { Component } from "@angular/core";

import { CommonModule } from '@angular/common';


import { FormsModule } from '@angular/forms';
import { Router } from '@angular/router';

import { Cab } from '../../Model/Cab';
import { CabService } from "../../Service/Cab.service";

@Component({
    selector: 'app-displayAll',
    standalone: true,
    imports: [CommonModule],
    templateUrl: './displayAll.component.html',
    styleUrl:'./displayAll.component.css'
    
  })

  export class DisplayAllComponent{
    cabs: Cab[] = [];

    message: string = "";
    errorMessage: string = "";
  
    constructor(private cabService: CabService, private Router: Router) {
  
      this.cabService.displayAllCabs()
      .subscribe(
        {
          next: (data: any) => {
            console.log(data);
            this.cabs = data;
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
 

  //displayCab() -> update button 

  //updateCab()-> update-> dispaly()