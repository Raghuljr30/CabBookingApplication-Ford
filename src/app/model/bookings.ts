export class Bookings {
    
    constructor( 
         public bookingId?:number,
         public customerName?:string,
         public cabNumber?:number,
         public bookingDate?:Date,
         public pickUpLocation?:string,
         public dropLocation?:string){}
}
