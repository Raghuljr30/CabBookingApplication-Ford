import { CommonModule } from '@angular/common';
import { Component } from '@angular/core';
import { RouterLinkActive, RouterModule, RouterOutlet } from '@angular/router';
import { RegisterComponent } from "./Component/register/register.component";
import { FormsModule } from '@angular/forms';
import { routes } from './app.routes';
import { NgModel } from '@angular/forms';
import { DisplayAllComponent } from './Component/displayAll/displayAll.component';
import { AvailablecabsComponent } from './Component/availablecabs/availablecabs.component';
import { UpdateCabComponent } from './Component/update-cab/update-cab.component';
@Component({
    selector: 'app-root',
    standalone: true,
    templateUrl: './app.component.html',
    styleUrl: './app.component.css',
    imports: [RouterOutlet, CommonModule, RouterLinkActive, RouterModule, RegisterComponent,DisplayAllComponent,AvailablecabsComponent,UpdateCabComponent],
})
export class AppComponent {
  title = 'cab-app';
}
