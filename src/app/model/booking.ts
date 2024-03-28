export class Booking {
    constructor(public bookingId?:number,public customerName?:string,public cabNumber?:number,public payment?:any, public bookingDate?:Date,public pickUpLocation?:string,public dropLocation?:string )
    {}
}
