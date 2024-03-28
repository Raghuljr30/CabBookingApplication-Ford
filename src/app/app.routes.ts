import { Routes } from '@angular/router';

import { DisplayCustomerComponent } from './component/customer/display-customer/display-customer.component';

import { SignUpComponent } from './component/customer/sign-up/sign-up.component';
import { LoginComponent } from './component/customer/login/login.component';
import { CredentialsComponent } from './component/customer/credentials/credentials.component';


export const routes: Routes = [
    {path:'',redirectTo:'/login',pathMatch:'full'},
    // {path:'credential',component:CredentialsComponent},
    
    {path:'login',component:LoginComponent},  
    {path:'signUp',component:SignUpComponent},
    {path:'display/:id',component:DisplayCustomerComponent}
   
    
    
    
];
