import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { Customer } from '../model/customer';
import { Review } from '../model/review';


@Injectable({
  providedIn: 'root'
})
export class CustomerService {


  constructor(private http:HttpClient) { }
  public ViewCustomers():Observable<any>{
    return this.http.get("http://localhost:8080/viewAllCustomer")
    
  }
  
  public registerCustomer(newCustomer:Customer):Observable<any>{
    return this.http.post("http://localhost:8080/customer/register",newCustomer);
  }
  public loginCustomer(id:number,password:string):Observable<any>{
    return this.http.get(`http://localhost:8080/customer/login/${id}/${password}`);
  }
  public viewCustomer(id:number):Observable<Customer>
  {
    return this.http.get("http://localhost:8080/customer/"+id);
  }
      public deleteCustomer(id:number):Observable<any>{
    return this.http.delete("http://localhost:8080/customer/"+id);
  }
  public updateCustomerMobile(customerDto:any):Observable<any>{
    return this.http.patch(`http://localhost:8080/customer/mobile`,customerDto);
  }
  public updateCustomerPassword(customerDto:any):Observable<any>{
    return this.http.patch(`http://localhost:8080/customer/password`,customerDto);
  }

  public bookingsOfCustomer(id:number):Observable<any>{
    return this.http.get("http://localhost:8080/booking/customer/"+id);
  }
  public  customerReviews(customerId:number,bookingId:number,review:string):Observable<any>{
    return this.http.patch(`http://localhost:8080/review/booking/${customerId}/${bookingId}/${review}`,null);

}
}