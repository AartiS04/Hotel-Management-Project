package api;

import model.*;
import service.*;

import java.util.*;

public class HotelResource {

    private static final HotelResource instance = new HotelResource();

    private final CustomerService customerService = CustomerService.getInstance();
    private final ReservationService reservationService = ReservationService.getInstance();

    private HotelResource() {}

    public static HotelResource getInstance() {
        return instance;
    }

    public Customer getCustomer(String em) {
        return customerService.getCustomer(em);
    }

    public void createACustomer(String em, String fn, String ln) {
        customerService.addCustomer(fn, ln, em);
    }

    public IRoom getRoom(String rno) {
        return reservationService.getARoom(rno);
    }

    public Reservation bookARoom(String custem, IRoom room, Date cid, Date cod) {
        Customer customer = customerService.getCustomer(custem);
        if (customer == null)
            throw new IllegalArgumentException("Customer not found.");
        return reservationService.reserveARoom(customer, room, cid, cod);
    }

    public Collection<Reservation> getCustomersReservations(String custem) {
        Customer customer = customerService.getCustomer(custem);
        if (customer == null)
            throw new IllegalArgumentException("Customer not found.");
        return reservationService.getcustres(customer);
    }

    public Collection<IRoom> findARoom(Date cid, Date cod) {
        return reservationService.findrooms(cid, cod);
    }
}