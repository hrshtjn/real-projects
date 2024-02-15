import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class TheatreTicketingSystem {
    private Map<Integer, Boolean> seats;
    private double totalSales;

    public TheatreTicketingSystem(int totalSeats) {
        seats = new HashMap<>();
        for (int i = 1; i <= totalSeats; i++) {
            seats.put(i, true);  // Initialize all seats to be available
        }
        totalSales = 0.0;
    }

    public boolean bookSeat(int seatNumber) {
        Boolean isAvailable = seats.get(seatNumber);
        if (isAvailable != null && isAvailable) {
            seats.put(seatNumber, false);  // Set the seat as booked
            totalSales += calculatePrice(seatNumber);
            return true;
        }
        return false;
    }

    public boolean cancelSeat(int seatNumber) {
        Boolean isBooked = seats.get(seatNumber);
        if (isBooked != null && !isBooked) {
            seats.put(seatNumber, true);  // Set the seat as available
            totalSales -= calculatePrice(seatNumber);
            return true;
        }
        return false;
    }

    public double calculatePrice(int seatNumber) {
        // Price calculation logic might be more complex, depending on the seat's location, etc.
        int basePrice = 100;
        return basePrice;  // Simplified base price for all seats
    }

    public void displaySeats() {
        seats.forEach((seatNumber, isAvailable) -> {
            System.out.println("Seat " + seatNumber + ": " + (isAvailable ? "Available" : "Booked"));
        });
    }

    public double getTotalSales() {
        return totalSales;
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        TheatreTicketingSystem system = new TheatreTicketingSystem(100);  // A theatre with 100 seats

        while (true) {
            System.out.println("Welcome to the Theatre Ticketing System");
            System.out.println("1. Book a seat\n" +
                    "            2. Cancel booking\n" +
                    "            3. Show available seats\n" +
                    "            4. Sales Report\n" +
                    "            5. Exit");
            System.out.print("Please enter an option: ");

            int option = scanner.nextInt();
            switch (option) {
                case 1:
                    System.out.print("Enter seat number to book: ");
                    int seatToBook = scanner.nextInt();
                    if (system.bookSeat(seatToBook)) {
                        System.out.println("Seat booked successfully!");
                    } else {
                        System.out.println("Seat booking failed!");
                    }
                    break;
                case 2:
                    System.out.print("Enter seat number to cancel: ");
                    int seatToCancel = scanner.nextInt();
                    if (system.cancelSeat(seatToCancel)) {
                        System.out.println("Booking cancelled successfully!");
                    } else {
                        System.out.println("Cancellation failed!");
                    }
                    break;
                case 3:
                    system.displaySeats();
                    break;
                case 4:
                    System.out.println("Total sales: $" + system.getTotalSales());
                    break;
                case 5:
                    System.out.println("Exiting system...");
                    scanner.close();
                    return;
                default:
                    System.out.println("Invalid option, please try again.");
                    break;
            }
        }
    }
}
