import { Routes } from '@angular/router';
import { DisplayComponent } from './component/display/display.component';
import { RegisterComponent } from './component/register/register.component';
import { Component } from '@angular/core';
import { HomeComponent } from './component/home/home.component';
import { DashboardComponent } from './component/dashboard/dashboard.component';
import { LoggedInComponent } from './component/logged-in/logged-in.component';
import { NavbarComponent } from './component/navbar/navbar.component';
import { ProfileComponent } from './component/profile/profile.component';

export const routes: Routes = [
    {path: 'home',component: HomeComponent},
    { path: 'register', component: RegisterComponent },
    { path: 'logged-in', component: LoggedInComponent},
    {path: 'navbar', component:NavbarComponent},
    { path: 'profile', component: ProfileComponent, pathMatch: 'full' },
    { path: 'display', component: DisplayComponent },
    { path: 'driver-list', component: DisplayComponent },
    {path: 'dashboard', component:DashboardComponent},
    { path: '', redirectTo: '/home', pathMatch: 'full' },
    
];
