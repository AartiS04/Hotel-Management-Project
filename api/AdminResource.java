package api;

import model.*;
import service.*;

import java.util.Collection;
import java.util.List;

public class AdminResource {

    private static final AdminResource instance = new AdminResource();

    private final CustomerService customerService = CustomerService.getInstance();
    private final ReservationService reservationService = ReservationService.getInstance();

    private AdminResource() {}

    public static AdminResource getInstance() {
        return instance;
    }

    public Customer getCustomer(String em) {
        return customerService.getCustomer(em);
    }

    public void addRoom(List<IRoom> rooms) {
        for (IRoom r : rooms) {
            reservationService.addRoom(r);
        }
    }

    public Collection<IRoom> getAllRooms() {
        return reservationService.getAllRooms();
    }

    public Collection<Customer> getAllCustomers() {
        return customerService.getAllCustomers();
    }

    public void displayAllReservations() {
        reservationService.printAllReservation();
    }
}