import { Component, OnInit } from '@angular/core';
import { CommonModule } from '@angular/common';
import { FormsModule } from '@angular/forms';
import { Cab } from '../../Model/Cab';
import { ActivatedRoute } from '@angular/router';
import { CabService } from '../../Service/Cab.service';
import { Router } from '@angular/router';
@Component({
  selector: 'app-update-cab',
  standalone: true,
  imports: [CommonModule,FormsModule],
  templateUrl: './update-cab.component.html',
  styleUrl: './update-cab.component.css'
})




export class UpdateCabComponent {
  
  cabId: number=2 ;
  cab: Cab = new Cab();
  message:string="";
  errorMessage:string="";
  constructor( private cabService: CabService,private router:Router,
    private route:ActivatedRoute) {}

  
  updateCabdetails() {
    this.cabId=this.route.snapshot.params['cabId'];
    console.log(this.cabId)
  this.cabService.updateCab(this.cabId, this.cab).subscribe(
    {
    next: (data: any) => {
    console.log(data);
    this.message = "Cab updated";
    this.errorMessage = "";
    this.router.navigateByUrl('displayAll')
  },
  error: (err: { status: string; error: string; }) => {
    console.log(err);
      this.errorMessage="Could't update Cab";
    if (err.status == "0")
      this.errorMessage = "Network error, please try again later.";
    else
      this.errorMessage = err.error;

    this.message = ""; 
  }
}
  );
}

  
  
  
          
  
  //ngOnInit(): void {
    //this.cabId = this.route.snapshot.params['cabId'];

    //this.cabService.getCabById(this.cabId).subscribe(data => {
    //  this.cab = data;
   // }, error => console.log(error));
  //}

  //onSubmit(){
   // this.cabService.updateCab(this.cabId, this.cab).subscribe(data =>
      

   //  {
    //  this.goToAvailablecabs();
    //},
    //(error: any) => console.log(error));
  //}
  

  //goToAvailablecabs(){
    //this.router.navigate(['/cabs']);
  //}
}
