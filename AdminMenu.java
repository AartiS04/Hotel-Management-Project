package ui;

import api.AdminResource;
import model.*;

import java.util.List;
import java.util.Scanner;

public class AdminMenu {

    private static final Scanner sc = new Scanner(System.in);
    private static final AdminResource adm = AdminResource.getInstance();

    public static void start() {
        while (true) {
            System.out.println("\n--- Admin Menu ---");
            System.out.println("1. See all Customers");
            System.out.println("2. See all Rooms");
            System.out.println("3. See all Reservations");
            System.out.println("4. Add a Room");
            System.out.println("5. Back to Main Menu");

            try {
                int ch = Integer.parseInt(sc.nextLine());

                switch (ch) {
                    case 1 -> showAllCustomers();
                    case 2 -> showAllRooms();
                    case 3 -> showAllReservations();
                    case 4 -> addRoom();
                    case 5 -> {
                        return;
                    }
                    default -> System.out.println("Invalid option.");
                }
            } catch (Exception e) {
                System.out.println("Invalid input. Try again.");
            }
        }
    }

    private static void showAllCustomers() {
        System.out.println("\n--- All Customers ---");
        adm.getAllCustomers().forEach(System.out::println);
    }

    private static void showAllRooms() {
        System.out.println("\n--- All Rooms ---");
        adm.getAllRooms().forEach(System.out::println);
    }

    private static void showAllReservations() {
        System.out.println("\n--- All Reservations ---");
        adm.displayAllReservations();
    }

    private static void addRoom() {
        try {
            System.out.print("Enter room number: ");
            String rno = sc.nextLine();

            System.out.print("Enter price: ");
            double p = Double.parseDouble(sc.nextLine());

            System.out.print("Enter room type (SINGLE/DOUBLE): ");
            RoomType ty = RoomType.valueOf(sc.nextLine().toUpperCase());

            IRoom room;
            if (p == 0.0) {
                room = new FreeRoom(rno, ty);
            } else {
                room = new Room(rno, p, ty);
            }

            adm.addRoom(List.of(room));

            System.out.println("Room added successfully.");

        } catch (Exception e) {
            System.out.println("Error adding room: " + e.getMessage());
        }
    }
}