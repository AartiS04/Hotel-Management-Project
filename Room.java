package model;

public class Room implements IRoom {
    private final String rno;
    private final Double prc;
    private final RoomType rty;

    public Room(String rno, Double prc, RoomType rty) {
        if (rno == null || rno.isBlank()) {
            throw new IllegalArgumentException("Room number cannot be empty.");
        }
        if (prc < 0) {
            throw new IllegalArgumentException("Price cannot be negative.");
        }
        this.rno = rno;
        this.prc = prc;
        this.rty = rty;
    }

    @Override
    public String getRoomNumber() {
        return rno;
    }

    @Override
    public Double getRoomPrice() {
        return prc;
    }

    @Override
    public RoomType getRoomType() {
        return rty;
    }

    @Override
    public boolean isFree() {
        return prc == 0.0;
    }

    @Override
    public String toString() {
        return String.format("Room %s | %s | Price: $%.2f", rno, rty, prc);
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (!(obj instanceof Room))
            return false;
        Room other = (Room) obj;
        return rno.equals(other.rno);
    }

    @Override
    public int hashCode() {
        return rno.hashCode();
    }
}