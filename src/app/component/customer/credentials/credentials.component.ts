import { CommonModule } from '@angular/common';
import { HttpClientModule } from '@angular/common/http';
import { Component } from '@angular/core';
import { FormsModule } from '@angular/forms';
import { ActivatedRoute, RouterLink } from '@angular/router';
import { Customer } from '../../../model/customer';

@Component({
  selector: 'app-credentials',
  standalone: true,
  imports: [CommonModule,HttpClientModule,FormsModule,RouterLink],
  templateUrl: './credentials.component.html',
  styleUrl: './credentials.component.css'
})
export class CredentialsComponent {
  
  
  




}
