package com.cabBooker.cabBookingApplication.admin;

import com.cabBooker.cabBookingApplication.cab.Cab;
import com.cabBooker.cabBookingApplication.cab.CabService;
import com.cabBooker.cabBookingApplication.cab.displayAllCabException;
import com.cabBooker.cabBookingApplication.cabAgency.CabAgency;
import com.cabBooker.cabBookingApplication.cabAgency.CabAgencySerivce;
import com.cabBooker.cabBookingApplication.customer.Customer;
import com.cabBooker.cabBookingApplication.customer.CustomerRepository;
import com.cabBooker.cabBookingApplication.customer.CustomerService;
import com.cabBooker.cabBookingApplication.driver.Driver;
import com.cabBooker.cabBookingApplication.driver.DriverService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.PropertyResourceBundle;

@RestController
public class AdminController {

    @Autowired
    private CabService cabService;

    @Autowired
    private CustomerService customerService;

    @Autowired
    private CabAgencySerivce cabAgencySerivce;

    @Autowired
    private DriverService driverService;

    @Autowired
    private AdminService adminService;



    /** Display all registered customers*/
    @GetMapping("/customers")
    public List<Customer> displayAllCustomers()
    {
        return this.customerService.displayAllCustomers();
    }

    /** Display all registered cabs*/
    @GetMapping("/cabs")
    public List<Cab> displayAllCabs() throws displayAllCabException {
        return this.cabService.displayAllCabs();
    }

    /** Display all registered CabAgencies*/
    @GetMapping("/cabagencies")
    public List<CabAgency> displayCabAgencies()
    {
        return this.cabAgencySerivce.displayCabAgency();
    }

    /** Display all registered drivers*/
    @GetMapping("/drivers")
    public List<Driver> displayAllDriver()
    {
        return this.driverService.displayAllDriver();
    }





}
