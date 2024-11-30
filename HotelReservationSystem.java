import java.util.ArrayList;
import java.util.Scanner;

// Room class
class Room {    int roomId;
    String type;
    double price;
    boolean isAvailable;

    Room(int roomId, String type, double price) {
        this.roomId = roomId;
        this.type = type;
        this.price = price;
        this.isAvailable = true;
    }

    @Override
    public String toString() {
        return "Room ID: " + roomId + ", Type: " + type + ", Price: $" + price + ", Available: " + (isAvailable ? "Yes" : "No");
    }
}

// Reservation class
class Reservation {
    int reservationId;
    String customerName;
    int roomId;
    String checkInDate;
    String checkOutDate;

    Reservation(int reservationId, String customerName, int roomId, String checkInDate, String checkOutDate) {
        this.reservationId = reservationId;
        this.customerName = customerName;
        this.roomId = roomId;
        this.checkInDate = checkInDate;
        this.checkOutDate = checkOutDate;
    }

    @Override
    public String toString() {
        return "Reservation ID: " + reservationId + ", Customer: " + customerName +
                ", Room ID: " + roomId + ", Check-In: " + checkInDate + ", Check-Out: " + checkOutDate;
    }
}

// Main Hotel Reservation System
public class HotelReservationSystem {
    private static ArrayList<Room> rooms = new ArrayList<>();
    private static ArrayList<Reservation> reservations = new ArrayList<>();
    private static int reservationCounter = 1;

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        initializeRooms();

        while (true) {
            System.out.println("\n--- Hotel Reservation System ---");
            System.out.println("1. View Available Rooms");
            System.out.println("2. Make a Reservation");
            System.out.println("3. View Reservations");
            System.out.println("4. Exit");
            System.out.print("Choose an option: ");
            int choice = scanner.nextInt();
            scanner.nextLine(); // Consume newline

            switch (choice) {
                case 1:
                    viewAvailableRooms();
                    break;
                case 2:
                    makeReservation(scanner);
                    break;
                case 3:
                    viewReservations();
                    break;
                case 4:
                    System.out.println("Thank you for using the Hotel Reservation System!");
                    scanner.close();
                    return;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }
    }

    private static void initializeRooms() {
        rooms.add(new Room(101, "Single", 100.0));
        rooms.add(new Room(102, "Double", 150.0));
        rooms.add(new Room(103, "Suite", 250.0));
        rooms.add(new Room(104, "Deluxe", 300.0));
    }

    private static void viewAvailableRooms() {
        System.out.println("\nAvailable Rooms:");
        for (Room room : rooms) {
            if (room.isAvailable) {
                System.out.println(room);
            }
        }
    }

    private static void makeReservation(Scanner scanner) {
        System.out.println("\nEnter your details to make a reservation:");
        System.out.print("Name: ");
        String customerName = scanner.nextLine();

        System.out.print("Room ID: ");
        int roomId = scanner.nextInt();
        scanner.nextLine(); // Consume newline

        Room selectedRoom = null;
        for (Room room : rooms) {
            if (room.roomId == roomId && room.isAvailable) {
                selectedRoom = room;
                break;
            }
        }

        if (selectedRoom == null) {
            System.out.println("Room not available. Please try again.");
            return;
        }

        System.out.print("Check-In Date (YYYY-MM-DD): ");
        String checkInDate = scanner.nextLine();

        System.out.print("Check-Out Date (YYYY-MM-DD): ");
        String checkOutDate = scanner.nextLine();

        // Mock payment process
        System.out.println("Processing payment of $" + selectedRoom.price + "...");
        System.out.println("Payment successful!");

        // Create reservation
        reservations.add(new Reservation(reservationCounter++, customerName, roomId, checkInDate, checkOutDate));
        selectedRoom.isAvailable = false;

        System.out.println("Reservation successful!");
    }

    private static void viewReservations() {
        System.out.println("\nCurrent Reservations:");
        for (Reservation reservation : reservations) {
            System.out.println(reservation);
        }
    }
}