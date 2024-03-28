import { CommonModule } from '@angular/common';
import { Component } from '@angular/core';
import { Router, RouterLinkActive, RouterModule, RouterOutlet } from '@angular/router';
import { routes } from './app.routes';
import { NgModel } from '@angular/forms';
import { HomeComponent } from "./component/home/home.component";
import { FormsModule } from '@angular/forms';
import { DisplayComponent } from "./component/display/display.component";

@Component({
    selector: 'app-root',
    standalone: true,
    templateUrl: './app.component.html',
    styleUrl: './app.component.css',
    imports: [RouterOutlet, CommonModule, RouterLinkActive, RouterModule, HomeComponent, DisplayComponent]
})
export class AppComponent {

  title = 'my-cabApp';
}

