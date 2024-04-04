import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { Cab } from '../Model/Cab';


@Injectable({
  providedIn: 'root'
})
export class CabService {
  getcabById(id: string | null) {
    throw new Error('Method not implemented.');
  }
  
  constructor(private http: HttpClient) { }
  
  public registerNewCab(newcab: Cab): Observable<any> {
    return this.http.post("http://localhost:8080/cab", newcab);
  }
  public displayAllCabs():Observable<any>{
    return this.http.get("http://localhost:8080/cabs/displayall");
  }
  public AvailableCabs():Observable<any> {
    return this.http.get("http://localhost:8080/display/Cabs");
}
public updateCab(cabId: number,cab:Cab):Observable<any>{
  return this.http.put(`http://localhost:8080/cab/${cabId}`, cab);
  
}
public getCabById(cabId: number): Observable<any>{
  return this.http.get("http://localhost:8080/cab/{cabId}");
}

}