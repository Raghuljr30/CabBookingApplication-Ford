import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { CommonModule } from '@angular/common';
import { Drivers } from '../model/drivers';


@Injectable({
  providedIn: 'root'
})
export class DriverService {

  constructor(private http: HttpClient) { }
  
  public listAllDrivers(): Observable<any> 
  {
    return this.http.get("http://localhost:8080/driver/list");
  }

  public registerNewDriver(newDriver: Drivers):Observable<Drivers>
  {
    return this.http.post(`http://localhost:8080/driver/register`,newDriver);
  }

  public updateAgency(driverId:number,newAgency:String,newAgencyId:number):Observable<Drivers>
  {
    return this.http.patch(`http://localhost:8080/driver/agency/${driverId}/${newAgency}/${newAgencyId}`,Drivers);
  }

  public getDriverById(id:number):Observable<any>
  {
    return this.http.get("http://localhost:8080/driver/id/"+id);
  }

  public loginDriver(id:number,password:string):Observable<Drivers>
  {
    return this.http.post(`http://localhost:8080/driver/login/${id}/${password}`,null);
  }

  public showReviews(id:number):Observable<any>
  {
    return this.http.get("http://localhost:8080/driver/review/"+id);
  }

  
  public showBookings(id:number):Observable<any>
  {
    return this.http.get("http://localhost:8080/driver/bookings/"+id);
  }

  public updatePhNumber(id:number,phNumber:number):Observable<any>
  {
    return this.http.patch(`http://localhost:8080/driver/mobilenumber/${id}/${phNumber}`,{ phoneNumber: phNumber });
  }

  public updatePassword(id:number,password:string):Observable<any>
  {
    return this.http.patch(`http://localhost:8080/driver/password/${id}/${password}`,{ newpassword: password });
  }

  public otherDrivers(agencyName:String):Observable<any>
  {
    return this.http.get("http://localhost:8080/driver/agency/"+agencyName);
  }

}
