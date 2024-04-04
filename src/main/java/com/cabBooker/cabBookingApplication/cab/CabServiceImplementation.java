package com.cabBooker.cabBookingApplication.cab;

import com.cabBooker.cabBookingApplication.cabAgency.CabAgency;
import com.cabBooker.cabBookingApplication.cabAgency.CabAgencyRepository;
import com.cabBooker.cabBookingApplication.driver.Driver;
import com.cabBooker.cabBookingApplication.driver.DriverRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@Service
public class CabServiceImplementation implements CabService{

    @Autowired
    private CabRepository cabRepository;

    @Autowired
    private DriverRepository driverRepository;

    @Autowired
    private CabAgencyRepository cabAgencyRepository;


    @Override
    public Cab registerNewCab(Cabdto newcab) throws CabException {
        //if (newcab.getCabModel() == null || newcab.getAC() == null || newcab.getCabAgencyId() == null || newcab.getVehicleNumber() == null || newcab.getNumberOfSeats() == null || newcab.getPickUpPoint() == null || newcab.getDropPoint() == null || newcab.getAgencyName() == null || newcab.getFair() == null || newcab.getAvailability() == null) {
         //   throw new CabException("Car details cannot be null");
        //}
        Cab cab = new Cab();
        //cab.setCabId(newcab.getCabId());
        cab.setCabAgencyId(newcab.getCabAgencyId());
        cab.setAC(newcab.getAC());
        cab.setVehicleNumber(newcab.getVehicleNumber());
        cab.setAvailability(newcab.getAvailability());
        cab.setNumberOfSeats(newcab.getNumberOfSeats());
        cab.setPickUpPoint(newcab.getPickUpPoint());
        cab.setAgencyName(newcab.getAgencyName());
        cab.setDropPoint(newcab.getDropPoint());
        cab.setFair(newcab.getFair());
        cab.setCabModel(newcab.getCabModel());

        return this.cabRepository.save(cab);


    }


    @Override
    public Cab changecabAgency(Integer cabId, String cabAgency) {
        Optional<Cab> cabOPt=this.cabRepository.findById(cabId);
        Cab cab=cabOPt.get();
        cab.setAgencyName(cabAgency);
        return this.cabRepository.save(cab);
    }

    @Override
    public Cab getCabById(Integer cabId)throws getCabByIdException {
        if(cabId == null)
        {
            throw new getCabByIdException("cabId can't be null");
        }
        Optional<Cab> cabOpt = cabRepository.findById(cabId);
        if(cabOpt.isEmpty())
        {
            throw new getCabByIdException(("No such Id Exists: "+cabId));
        }
        return this.cabRepository.findBycabId(cabId);
    }

    @Override

        public List<Cab> getCabByAgencyName(String agencyName) throws CabException {
            if(agencyName==null){
                throw new CabException("Agency name can't be null");
            }
        List<Cab> cabOpt = cabRepository.findByAgencyName(agencyName);
        if(cabOpt.isEmpty())
        {
            throw new CabException(("No such Agency name Exists"));
        }
            return this.cabRepository.findByAgencyName(agencyName);
        }

    @Override
    public Cab getCabByVehicleNumber(Integer vehicleNumber) throws CabException {
        if(vehicleNumber==null){
            throw new CabException("vehicleNumber can't be null");
        }
        Optional<Cab> cabOpt = Optional.ofNullable(cabRepository.findByVehicleNumber(vehicleNumber));
        if(cabOpt.isEmpty())
        {
            throw new CabException(("No such vehicleNumber Exists"));
        }
        return cabRepository.findByVehicleNumber(vehicleNumber);
    }

    @Override
    public Cab changeCabintonewCabAgency(Integer cabId, Integer cabAgencyId,Integer oldCabAgencyId) {

        Optional<Cab> cab=cabRepository.findById(cabId);
        Optional<CabAgency> newCabAgency=cabAgencyRepository.findById(cabAgencyId);
        Optional<CabAgency>oldCabAgency=cabAgencyRepository.findById(oldCabAgencyId);
        CabAgency oldCabAgencyExist=oldCabAgency.get();
        List<Cab> cabList=oldCabAgencyExist.getCabs();


        if(cab.isPresent())
        {
            Cab cabExist=cab.get();
            if(newCabAgency.isPresent())
            {
                CabAgency cabAgencyExist= newCabAgency.get();
                System.out.println(cabAgencyId+"---------------------------------------------------------------");
                cabExist.setCabAgencyId(cabAgencyId);
                cabExist.setAgencyName(cabAgencyExist.getCabAgencyName());
                cabRepository.save(cabExist);
                cabAgencyExist.setCabs(Arrays.asList(cabExist));

                cabList.remove(cabId);
                oldCabAgencyExist.setCabs(cabList);
                cabAgencyRepository.save(oldCabAgencyExist);

                return cabExist;

            }
        }
        return  null;
    }


    @Override
    public Cab updateCab(Integer cabId,Cab updatedcab) throws CabException {
        if(cabId == null)
        {
            throw new CabException("CabId can't be null");
        }

        Optional<Cab> cabopt = cabRepository.findById(cabId);
        if(cabopt.isEmpty()){
            throw new CabException("No such cab exists");
        }
        Cab cab= cabopt.get();
        cab.setCabAgencyId(updatedcab.getCabAgencyId());
        cab.setAC(updatedcab.getAC());
        cab.setVehicleNumber(updatedcab.getVehicleNumber());
        cab.setAvailability(updatedcab.getAvailability());
        cab.setNumberOfSeats(updatedcab.getNumberOfSeats());
        cab.setPickUpPoint(updatedcab.getPickUpPoint());
        cab.setAgencyName(updatedcab.getAgencyName());
        cab.setAgencyName(updatedcab.getAgencyName());
        cab.setDropPoint(updatedcab.getDropPoint());
        cab.setFair(updatedcab.getFair());
        cab.setCabModel(updatedcab.getCabModel());


        return this.cabRepository.save(cab);
    }

    @Override
    public List<Cab> displayAllCabs() throws displayAllCabException{
        List<Cab> cabOptional = cabRepository.findAll();
        if(cabOptional.size()==0){
            throw new displayAllCabException("cab can't be null");
        }
        return this.cabRepository.findAll();
    }

    @Override
    public Cab mapCabAndDriver(Integer vehicleNumber, Integer driverVehiclenumber) {

        Cab cab=this.cabRepository.findByVehicleNumber(vehicleNumber);
        Driver driver=this.driverRepository.findByDriverVehicleNumber(driverVehiclenumber);
        cab.setDriver(driver);
        return this.cabRepository.save(cab);

    }

    @Override
    public Cab deleteCabById(Integer cabId) throws deleteCabByIdException {
        if(cabId==null){
            throw new deleteCabByIdException("cabId can't be null");
        }

        Optional<Cab> cabopt = cabRepository.findById(cabId);

        this.cabRepository.deleteById(cabId);
        return cabopt.get();
    }

    @Override
    public List<Cab> displayAvailableCabs() {
         List<Cab>availableCabs= this.cabRepository.findByAvailability(true);
         System.out.println(availableCabs);
         return availableCabs;
    }

    @Override
    public Cab updateCabById(Integer cabId, String pickUpPoint, String dropPoint) throws updateCabByIdException {
        Cab cab=this.cabRepository.findBycabId(cabId);
        cab.setPickUpPoint(pickUpPoint);
        cab.setDropPoint(dropPoint);
        if(pickUpPoint==null){
            throw new updateCabByIdException("PickupPoint can't be null");
        }
        if(dropPoint==null){
            throw new updateCabByIdException("dropPoint can't be null");
        }
        if(pickUpPoint==null && dropPoint==null){
            throw  new updateCabByIdException("PickupPoint and droppoint can't be null");
        }
        return this.cabRepository.save(cab);
    }






}
