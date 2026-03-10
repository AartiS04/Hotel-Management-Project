package model;

public class FreeRoom extends Room {
    public FreeRoom(String rno, RoomType rty) {
        super(rno, 0.0, rty);
    }

    @Override
    public String toString() {
        return String.format("Room %s | %s | Price: Free", getRoomNumber(), getRoomType());
    }
}