export class Cabs {
    constructor(
       public cabId?:number,
       public cabModel?:string,
       public ac?:boolean,
       public numberOfSeats?:number,
       public vehicleNumber?:number,
       public availability?:boolean,
       public agencyName?:string,
       public cabAgencyId?:number,
       public pickUpPoint?:string,
       public dropPoint?:string,
       public fair?:number
    ){}
}
