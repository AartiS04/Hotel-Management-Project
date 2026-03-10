package service;

import model.*;

import java.util.*;

public class ReservationService {

    private static ReservationService instance = null;

    private Map<String, IRoom> rooms = new HashMap<>();

    private List<Reservation> reservs = new ArrayList<>();

    private ReservationService() {}

    public static ReservationService getInstance() {
        if (instance == null) {
            instance = new ReservationService();
        }
        return instance;
    }

    public boolean addRoom(IRoom room) {

        if (rooms.containsKey(room.getRoomNumber())) {
            return false;
        }

        rooms.put(room.getRoomNumber(), room);
        return true;
    }

    public IRoom getARoom(String rno) {
        return rooms.get(rno);
    }

    public Collection<IRoom> getAllRooms() {
        return rooms.values();
    }

    private void validateDates(Date cin, Date cout) {

        Date tod = new Date();

        if (cin.before(tod)) {
            throw new IllegalArgumentException("Check-in date cannot be in the past.");
        }

        if (!cout.after(cin)) {
            throw new IllegalArgumentException("Checkout date must be after check-in date.");
        }
    }

    public Collection<IRoom> findrooms(Date cin, Date cout) {

        validateDates(cin, cout);

        List<IRoom> availrooms = new ArrayList<>(rooms.values());

        for (Reservation r : reservs) {
            if (r.getCheckInDate().before(cout) && r.getCheckOutDate().after(cin)) {
                availrooms.remove(r.getRoom());
            }
        }
        return availrooms;
    }

    public Collection<IRoom> findrecomrooms(Date cin, Date cout) {

        Calendar cal = Calendar.getInstance();

        cal.setTime(cin);
        cal.add(Calendar.DATE, 7);
        Date newcin = cal.getTime();

        cal.setTime(cout);
        cal.add(Calendar.DATE, 7);
        Date newcout = cal.getTime();

        System.out.println("\nNo rooms available for selected dates.");
        System.out.println("Searching for rooms 7 days later...");

        System.out.println("Recommended dates:");
        System.out.println("Check-in: " + newcin);
        System.out.println("Check-out: " + newcout);

        return findrooms(newcin, newcout);
    }

    public Reservation reserveARoom(Customer cust, IRoom room, Date cin, Date cout) {

        validateDates(cin, cout);

        for (Reservation r : reservs) {
            if (r.getRoom().equals(room) && r.getCheckInDate().before(cout) && r.getCheckOutDate().after(cin)) {
                throw new IllegalArgumentException("Room already booked for these dates.");
            }
        }

        Reservation newReservation = new Reservation(cust, room, cin, cout);
        reservs.add(newReservation);
        return newReservation;
    }

    public Collection<Reservation> getcustres(Customer cust) {

        List<Reservation> custreserv = new ArrayList<>();

        for (Reservation r : reservs) {

            if (r.getCustomer().equals(cust)) {
                custreserv.add(r);
            }
        }

        return custreserv;
    }

    public void printAllReservation() {

        if (reservs.isEmpty()) {
            System.out.println("No reservations found.");
            return;
        }

        for (Reservation r : reservs) {
            System.out.println(r);
        }
    }
}