export class Cab {

    constructor(
        public cabId?: number,
        public cabModel?: string,
        public AC?: boolean,
        public numberOfSeats?: number,
        public vehicleNumber?: number,
        public availability?: boolean,
        public agencyName?:string,
        public pickUpPoint?:string,
        public dropPoint?:string,
        public fair?:number,
        public cabAgencyId?:number,
        
    ) { }
}
