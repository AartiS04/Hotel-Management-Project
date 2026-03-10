package ui;

import api.*;
import model.*;
import service.ReservationService;

import java.text.SimpleDateFormat;
import java.util.*;

public class MainMenu {

    private static final Scanner sc = new Scanner(System.in);
    private static final HotelResource hotelres = HotelResource.getInstance();
    private static final AdminResource adminres = AdminResource.getInstance();
    private static final ReservationService reservserv = ReservationService.getInstance();

    public static void start() {
        seedData();

        while (true) {
            try {
                System.out.println("\n1. Create Account");
                System.out.println("2. Find and Reserve Room");
                System.out.println("3. View My Reservations");
                System.out.println("4. Admin Menu");
                System.out.println("5. Exit");

                int ch = Integer.parseInt(sc.nextLine());

                switch (ch) {
                    case 1 -> createAccount();
                    case 2 -> findAndReserveRoom();
                    case 3 -> viewReservations();
                    case 4 -> AdminMenu.start();
                    case 5 -> System.exit(0);
                    default -> System.out.println("Invalid option.");
                }

            } catch (Exception e) {
                System.out.println("Error: " + e.getMessage());
            }
        }
    }

    private static void createAccount() {
        try {
            System.out.print("Email: ");
            String em = sc.nextLine();

            System.out.print("First name: ");
            String fn = sc.nextLine();

            System.out.print("Last name: ");
            String ln = sc.nextLine();

            hotelres.createACustomer(em, fn, ln);

            System.out.println("Account created successfully.");

        } catch (Exception e) {
            System.out.println("Failed to create account: " + e.getMessage());
        }
    }

    private static void findAndReserveRoom() {

        try {

            SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
            df.setLenient(false);

            System.out.print("Enter check-in date (yyyy-MM-dd): ");
            Date cid = df.parse(sc.nextLine());

            System.out.print("Enter check-out date (yyyy-MM-dd): ");
            Date cod = df.parse(sc.nextLine());

            Collection<IRoom> rooms = hotelres.findARoom(cid, cod);

            if (rooms.isEmpty()) {

                System.out.println("No rooms available for selected dates.");

                Collection<IRoom> recmded = reservserv.findrecroom(cid, cod);

                if (!recmded.isEmpty()) {
                    System.out.println("Recommended rooms:");
                    recmded.forEach(System.out::println);
                }

                return;
            }

            System.out.println("Available Rooms:");
            rooms.forEach(System.out::println);

            System.out.print("Do you want to book a room? (yes/no): ");
            String ans = sc.nextLine();

            if (!ans.equalsIgnoreCase("yes"))
                return;

            System.out.print("Enter your registered email: ");
            String em = sc.nextLine();

            if (hotelres.getCustomer(em) == null) {
                System.out.println("Email not registered. Please create an account first.");
                return;
            }

            System.out.print("Enter room number to book: ");
            String rno = sc.nextLine();

            IRoom room = reservserv.getARoom(rno);

            if (room == null) {
                System.out.println("Invalid room number.");
                return;
            }

            hotelres.bookARoom(em, room, cid, cod);

            System.out.println("Reservation successful.");

        } catch (Exception e) {
            System.out.println("Reservation failed: " + e.getMessage());
        }
    }

    private static void viewReservations() {

        try {
            System.out.print("Enter your email: ");
            String email = sc.nextLine();

            Collection<Reservation> res =
                    hotelres.getCustomersReservations(email);

            if (res.isEmpty()) {
                System.out.println("No reservations found.");
                return;
            }

            res.forEach(System.out::println);

        } catch (Exception e) {
            System.out.println("Error retrieving reservations: " + e.getMessage());
        }
    }

    private static void seedData() {

        adminres.addRoom(List.of(
                new Room("101", 120.0, RoomType.SINGLE),
                new Room("102", 150.0, RoomType.DOUBLE),
                new FreeRoom("103", RoomType.SINGLE)
        ));
    }
}