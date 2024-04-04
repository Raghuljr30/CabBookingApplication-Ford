import { Routes } from '@angular/router';
import { RegisterComponent } from './Component/register/register.component';
import { DisplayAllComponent } from './Component/displayAll/displayAll.component';
import { AvailablecabsComponent } from './Component/availablecabs/availablecabs.component';
import { UpdateCabComponent } from './Component/update-cab/update-cab.component';

export const routes: Routes = [
    {path: 'register', component:RegisterComponent},
    {path: 'displayAll' ,component:DisplayAllComponent},
    {path: 'availablecabs',component:AvailablecabsComponent},
  // {path: '', redirectTo:'register', pathMatch:'full'}
    {path: 'update-cab/:cabId',component:UpdateCabComponent},
];
