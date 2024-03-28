package com.cabBooker.cabBookingApplication.driver;

import com.cabBooker.cabBookingApplication.booking.Booking;
import com.cabBooker.cabBookingApplication.booking.BookingNotFoundException;
import com.cabBooker.cabBookingApplication.booking.BookingRepository;
import com.cabBooker.cabBookingApplication.cab.Cab;
import com.cabBooker.cabBookingApplication.cab.CabRepository;
import com.cabBooker.cabBookingApplication.cabAgency.CabAgency;
import com.cabBooker.cabBookingApplication.cabAgency.CabAgencyRepository;
import com.cabBooker.cabBookingApplication.customer.Customer;
import com.cabBooker.cabBookingApplication.review.Review;
import jakarta.validation.constraints.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;


@Service
public class DriverServiceImplementation implements  DriverService{

    @Autowired
    private DriverRepository driverRepository;
    @Autowired
    private CabRepository cabRepository;
    @Autowired
    private CabAgencyRepository cabAgencyRepository;

    @Autowired
    private BookingRepository bookingRepository;

    String msg = "DriverID can't be null";

    /************************************************************************************
     * Method: - registerNewDriver
     *Description: - To register a new car
     * @parameter DriverDto - Driver without any mapped components

     * @returns Driver - returns the new registered Driver
     *Created By - Padma Priya V
     *Created Date - 19-FEB-2024

     ************************************************************************************/
    @Override
    public Driver registerNewDriver(DriverDto driverDto) throws nullFieldException {
        if (driverDto.getDriverAgencyId() == null || driverDto.getDriverAgencyName() == null || driverDto.getDriverEmail() == null || driverDto.getDriverMobileNumber() == null || driverDto.getDriverVehicleNumber() == null || driverDto.getDriverPassword() == null) {
            throw new nullFieldException("Driver details can't be null");
        }
        Driver driver = new Driver();
        driver.setDriverEmail(driverDto.getDriverEmail());
        driver.setDriverAgencyName(driverDto.getDriverAgencyName());
        driver.setDriverAgencyId(driverDto.getDriverAgencyId());
        driver.setDriverPassword(driverDto.getDriverPassword());
        driver.setDriverMobileNumber(driverDto.getDriverMobileNumber());
        driver.setDriverName(driverDto.getDriverName());
        driver.setDriverVehicleNumber(driverDto.getDriverVehicleNumber());
        driver.setLicenseNumber(driverDto.getLicenseNumber());
        driver.setDriverVehicleNumber(driverDto.getDriverVehicleNumber());
        return this.driverRepository.save(driver);
    }

    /************************************************************************************
     * Method: - listALLDrivers
     *Description: - Displays all the drivers

     * @returns Driver - returns list of drivers
     *Created By - Padma Priya V
     *Created Date - 19-FEB-2024

     ************************************************************************************/


    @Override
    public List<Driver> listAllDrivers() throws emptyListException {
        List<Driver> drivers = driverRepository.findAll();
        if (drivers == null) {
            throw new emptyListException("There are no drivers");
        }
        return this.driverRepository.findAll();
    }

    /************************************************************************************
     * Method: - getDriverById
     *Description: - To get the Driver with its ID
     * @parameter Driver ID - An ID of type integer

     * @returns Driver - returns the Driver which has that ID
     *Created By - Padma Priya V
     *Created Date - 19-FEB-2024

     ************************************************************************************/

    @Override
    public Optional<Driver> getDriverById(Integer driverId) throws nullFieldException, noChangeException {
        if (driverId == null) {
            throw new nullFieldException(msg);
        }
        Optional<Driver> driverOpt = driverRepository.findById(driverId);
        if (driverOpt.isEmpty()) {
            throw new noChangeException("No such Id Exists: " + driverId);
        }

        return this.driverRepository.findById(driverId);
    }

    /************************************************************************************
     * Method: - deleteDriverByID
     *Description: - To delete a driver using its ID
     * @parameter Driver ID - ID of type integer of the driver that has to be deleted

     * @returns Driver - returns the deleted Driver
     *Created By - Padma Priya V
     *Created Date - 19-FEB-2024

     ************************************************************************************/

    @Override
    public Driver deleteDriverById(Integer driverId) throws nullFieldException {


        if (driverId == null) {
            throw new nullFieldException(msg);
        }
        Optional<Driver> driverOpt = driverRepository.findById(driverId);
        if (driverOpt.isEmpty())
            throw new nullFieldException("No data found");
        Driver driver = driverOpt.get();
        this.cabRepository.deleteById(driver.getDriverId());
        this.driverRepository.deleteById(driver.getDriverId());
        return null;

    }

    /************************************************************************************
     * Method: - updateDriver
     *Description: - To update a driver
     * @parameter Driver - Driver with new values

     * @returns Driver - returns the new updated Driver
     *Created By - Padma Priya V
     *Created Date - 19-FEB-2024

     ************************************************************************************/

    @Override
    public Driver updateDriver(Integer driverId, Driver newDriver) throws nullFieldException {
        if (driverId == null) {
            throw new nullFieldException(msg);
        }
        Optional<Driver> driverOpt = driverRepository.findById(driverId);
        if (driverOpt.isEmpty()) {
            throw new nullFieldException("No such Driver Exists: " + driverId);
        }
        Driver updateDriver = driverOpt.get();
        updateDriver.setDriverPassword(newDriver.getDriverPassword());
        updateDriver.setDriverAgencyName(newDriver.getDriverAgencyName());
        updateDriver.setDriverMobileNumber(newDriver.getDriverMobileNumber());
        updateDriver.setDriverId(newDriver.getDriverId());
        updateDriver.setDriverEmail(newDriver.getDriverEmail());
        updateDriver.setDriverVehicleNumber(newDriver.getDriverVehicleNumber());
        updateDriver.setLicenseNumber(newDriver.getLicenseNumber());
        updateDriver.setReviews(newDriver.getReviews());
        updateDriver.setDriverName((newDriver.getDriverName()));


        return this.driverRepository.save(updateDriver);

    }
    /************************************************************************************
     * Method: - getDriversByAgencyName
     *Description: - To get the drivers present in a particular agency
     * @parameter AgencyName - Agency Name of type string

     * @returns Driver - returns the drivers that belong to that cab agency
     *Created By - Padma Priya V
     *Created Date - 20-FEB-2024

     ************************************************************************************/

    @Override
    public List<Driver> getDriversByAgencyName(String agencyName) throws emptyListException, nullFieldException {
        if (agencyName == null) {
            throw new nullFieldException("AgencyName can't be null");
        }
        List<Driver> drivers = driverRepository.findByDriverAgencyName(agencyName);
        if (drivers.isEmpty()) {
            throw new emptyListException("No drivers are present in this agency");
        }
        return this.driverRepository.findByDriverAgencyName(agencyName);
    }

    /************************************************************************************
     * Method: - changeAgency
     *Description: - To change the agency of a driver
     * @parameter ID, Agency name, Agency ID - To find the driver by ID and
     * update its Agency name along with its Agency ID

     * @returns Driver - returns the Driver with updated Agency details
     *Created By - Padma Priya V
     *Created Date - 20-FEB-2024

     ************************************************************************************/

    @Override
    public Driver changeAgency(Integer driverId, String driverAgencyName, Integer driverAgencyId) throws noChangeException, nullFieldException {

        if (driverId == null)
            throw new nullFieldException("DriverID can't be null");
        Optional<Driver> driverOpt = this.driverRepository.findById(driverId);
        Driver driver = driverOpt.get();
        if ((driver.getDriverAgencyId().equals(driverAgencyId)) || (driver.getDriverAgencyName().equals(driverAgencyName))) {
            throw new noChangeException("No change detected");
        }
        driver.setDriverAgencyId(driverAgencyId);
        driver.setDriverAgencyName(driverAgencyName);
        return this.driverRepository.save(driver);

    }

    /************************************************************************************
     * Method: - changeMobileNumber
     *Description: - To change the mobile number of a driver
     * @parameter Driver ID, Mobile Number - Driver ID that has to be updated with the new mobile number

     * @returns Driver - returns the Driver with new updated mobile number
     *Created By - Padma Priya V
     *Created Date - 21-FEB-2024

     ************************************************************************************/

    @Override
    public Driver changeMobileNumber(Integer driverId, Long driverMobileNumber) throws noChangeException, nullFieldException {

        if (driverId == null)
            throw new nullFieldException("DriverId can't be empty");
        if (driverMobileNumber == null)
            throw new nullFieldException("Driver's Mobile Number can't be empty");
        Optional<Driver> driverOpt = this.driverRepository.findById(driverId);
        Driver driver = driverOpt.get();

        if (driver.getDriverMobileNumber().equals(driverMobileNumber)) {
            throw new noChangeException("No change detected");
        }
        driver.setDriverMobileNumber(driverMobileNumber);
        return this.driverRepository.save(driver);

    }

    /************************************************************************************
     * Method: - changePassword
     *Description: - To change the password of a driver
     * @parameter Driver ID, Password - Driver ID that has to be updated with the new password

     * @returns Driver - returns the Driver with new updated password
     *Created By - Padma Priya V
     *Created Date - 21-FEB-2024

     ************************************************************************************/

    @Override
    public Driver changePassword(Integer driverId, String password) throws noChangeException, nullFieldException {
        Optional<Driver> driverOpt = this.driverRepository.findById(driverId);
        Driver driver = driverOpt.get();
        if (password == null)
            throw new nullFieldException("New password can't be empty");
        if (driver.getDriverPassword().equals(password))
            throw new noChangeException("Enter new password");
        driver.setDriverPassword(password);
        return this.driverRepository.save(driver);

    }
    /************************************************************************************
     * Method: - displayAllDriver
     *Description: - To display all the drivers
     * @parameter  - NIl

     * @returns List of Driver - The drivers list present
     *Created By - Raghul
     *Created Date - 21-FEB-2024

     ************************************************************************************/


    @Override
    public List<Driver> displayAllDriver() {

        return this.driverRepository.findAll();

    }

    /************************************************************************************
     * Method: - loginDriver
     *Description: - To log in a driver
     * @parameter Driver ID, Password - Driver ID that has to be logged in with its password

     * @returns Driver - returns the logged in driver's details
     *Created By - Padma Priya V
     *Created Date - 21-FEB-2024

     ************************************************************************************/

    @Override
    public Driver loginDriver(Integer driverId, String password) {
        Optional<Driver> driverOpt = driverRepository.findById(driverId);
        Driver driver = driverOpt.get();

        if (driver.getDriverPassword().equals(password)) {
            return driver;
        }

        return null;

    }

    /************************************************************************************
     * Method: - getDriversReview
     *Description: - To get the reviews of the driver
     * @parameter Driver ID - Driver ID that got those reviews

     * @returns Driver - returns reviews of that driver
     *Created By - Padma Priya V
     *Created Date - 19-MAR-2024

     ************************************************************************************/


    @Override
    public List<Review> getDriversReview(Integer driverId) {
        Optional<Driver> driverOpt = driverRepository.findById(driverId);
        Driver driver = driverOpt.get();
        List<Review> reviews = driver.getReviews();
        return reviews;

    }

    /************************************************************************************
     * Method: - mapCabAndDriver
     *Description: - To map the driver with their cab
     * @parameter Driver Vehicle number, Cab Vehicle Number - The vehcile number using which the cab and driver will be mapped

     * @returns Cab - returns the Cab that has been mapped with the driver
     *Created By - Padma Priya V
     *Created Date - 19-MAR-2024

     ************************************************************************************/

    @Override
    public Cab mapCabAndDriver(Integer vehicleNumber, Integer driverVehiclenumber) {

        Cab cab=this.cabRepository.findByVehicleNumber(vehicleNumber);
        Driver driver=this.driverRepository.findByDriverVehicleNumber(driverVehiclenumber);
        cab.setDriver(driver);
        return this.cabRepository.save(cab);

    }

    /************************************************************************************
     * Method: - mapCabAgencyAndCabUsingID
     *Description: - To map the Cab Agency along with its Cab using its ID
     * @parameter Agency ID - Cab Agency ID to map with Cab

     * @returns CabAgency - returns the Cab Agency that is associated with this agency
     *Created By - Padma Priya V
     *Created Date - 19-MAR-2024

     ************************************************************************************/
    @Override
    public CabAgency mapCabAgencyAndCabUsingId(Integer cabAgencyId) throws nullFieldException {

        Optional<CabAgency> cabagency = this.cabAgencyRepository.findById(cabAgencyId);
        List<Cab> cabs = this.cabRepository.findByCabAgencyId(cabAgencyId);

        if (cabagency.isPresent()) {
            CabAgency cabAgency = cabagency.get();
            cabAgency.setCabs(cabs);
            cabAgencyRepository.save(cabAgency);
            return cabagency.get();
        } else throw new nullFieldException("Not present");
    }

    /************************************************************************************
     * Method: - getDriverBookings
     *Description: - To get the bookings of the driver
     * @param driverId- ID to find its bookings

     * @returns List of Bookings - The list of bookings that is associated with the driver
     *Created By - Padma Priya V
     *Created Date - 19-MAR-2024

     ************************************************************************************/
    @Override
    public List<Booking> getDriversBookings(Integer driverId) throws nullFieldException {
        Optional<Driver> driverOpt = driverRepository.findById(driverId);
        Driver driver = driverOpt.get();
        Cab cab= cabRepository.findByVehicleNumber(driver.getDriverVehicleNumber());
        List<Booking> bookings = bookingRepository.findAll();
        Integer cabId= bookings.get(0).getCab().getCabId();
        List<Booking> filteredBookings = bookings.stream()
                        .filter(booking -> booking.getCab().getCabId() != null && booking.getCab().getCabId().equals(cabId))
                        .collect(Collectors.toList());
        return filteredBookings;
    }
    /************************************************************************************
     * Method: - changeMail
     *Description: - To change the Mail ID of a driver
     * @param driverId, mail - Driver ID that has to be updated with the new mail ID

     * @return Driver - returns the Driver with new updated Mail ID
     *Created By - Padma Priya V
     *Created Date - 19-FEB-2024

     ************************************************************************************/
    @Override
    public Driver changeMail(Integer driverId, String mail)throws nullFieldException,noChangeException {
        Optional<Driver> driverOpt = this.driverRepository.findById(driverId);
        Driver driver = driverOpt.get();
        if (mail == null)
            throw new nullFieldException("New MailID can't be empty");
        if (driver.getDriverEmail().equals(mail))
            throw new noChangeException("Enter new mailId");
        driver.setDriverPassword(mail);
        return this.driverRepository.save(driver);

    }

    /************************************************************************************
     * Method: - changeName
     *Description: - To change the name of a driver
     * @parameter Driver ID, new Name - Driver ID that has to be updated with the new name

     * @returns Driver - returns the Driver with new updated name
     *Created By - Padma Priya V
     *Created Date - 21-FEB-2024

     ************************************************************************************/

    @Override
    public Driver changeName(Integer driverId, String name) throws nullFieldException, noChangeException {
        Optional<Driver> driverOpt = this.driverRepository.findById(driverId);
        Driver driver = driverOpt.get();
        if (name == null)
            throw new nullFieldException("New Name can't be empty");
        if (driver.getDriverEmail().equals(name))
            throw new noChangeException("Enter new Name");
        driver.setDriverName(name);
        return this.driverRepository.save(driver);

    }

}


