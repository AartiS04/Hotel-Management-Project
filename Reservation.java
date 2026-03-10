package model;

import java.util.Date;

public class Reservation {
    private final Customer cust;
    private final IRoom room;
    private final Date cid;
    private final Date cod;

    public Reservation(Customer cust, IRoom room, Date cid, Date cod) {
        this.cust = cust;
        this.room = room;
        this.cid = cid;
        this.cod = cod;
    }

    public Customer getCustomer() {
        return cust;
    }
    public IRoom getRoom() {
        return room;
    }
    public Date getCheckInDate() {
        return cid;
    }
    public Date getCheckOutDate() {
        return cod;
    }

    @Override
    public String toString() {
        return "Reservation: " + cust + " | " + room + " | From " + cid + " To " + cod;
    }
}