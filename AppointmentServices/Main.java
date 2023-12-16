import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Date;
import java.util.List;
import java.util.Scanner;

/**
 * The Main class represents a Java program for managing appointment information.
 * Users can add, view, update, and delete appointments in the database.
 * Enhancements include improved code structure, MySQL database integration, and Java connectivity.
 * 
 * @author Isabel Rodarte
 *
 */

public class Main {
	//connects to database
    private static final String JDBC_URL = "jdbc:mysql://localhost:3306/patientinformation";
    private static final String USERNAME = "root";
    private static final String PASSWORD = "Teacher";

    public static void main(String[] args) {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            try (Connection connection = DriverManager.getConnection(JDBC_URL, USERNAME, PASSWORD)) {
                boolean exit = false;
                Scanner scanner = new Scanner(System.in);

                while (!exit) {
                	//print titles
                    System.out.println("1. Add new appointment");
                    System.out.println("2. View all appointments");
                    System.out.println("3. Update an appointment");
                    System.out.println("4. Delete an appointment");
                    System.out.println("5. View appointment details by ID");
                    System.out.println("6. Exit");
                    System.out.print("Choose an option: ");

                    int choice = scanner.nextInt();
                    // Consume newline
                    scanner.nextLine(); 

                    // switch cases
                    switch (choice) {
                        case 1:
                            addNewAppointment(connection, scanner);
                            break;
                        case 2:
                            viewAllAppointments(connection);
                            break;
                        case 3:
                            updateAppointment(connection, scanner);
                            break;
                        case 4:
                            deleteAppointment(connection, scanner);
                            break;
                        case 5:
                            viewAppointmentDetailsById(connection, scanner);
                            break;

                        case 6:
                            exit = true;
                            break;
                        default:
                            System.out.println("Invalid option. Please choose a valid option.");
                    }
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    //add new appointment 
    private static void addNewAppointment(Connection connection, Scanner scanner) throws Exception {
        System.out.print("Enter appointment ID: ");
        String appointmentID = scanner.nextLine();

        System.out.print("Enter appointment description: ");
        String description = scanner.nextLine();

        Date appointmentDate = new Date(); // You can modify this as needed
        Appointment newAppointment = new Appointment(appointmentID, appointmentDate, description);

        try {
            newAppointment.saveToDatabase(connection);
            System.out.println("Appointment added successfully.");
        } catch (SQLException e) {
            System.out.println("Error adding appointment: " + e.getMessage());
        }
    }

    //view appointmets
    private static void viewAllAppointments(Connection connection) {
        try {
            List<Appointment> appointments = Appointment.getAllAppointments(connection);

            if (!appointments.isEmpty()) {
                System.out.println("All Appointments:");
                for (Appointment appointment : appointments) {
                    System.out.println(appointment);
                }
            } else {
                System.out.println("No appointments found.");
            }
        } catch (SQLException e) {
            System.out.println("Error fetching appointments: " + e.getMessage());
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    //update appointment
    private static void updateAppointment(Connection connection, Scanner scanner) {
        System.out.print("Enter appointment ID to update: ");
        String appointmentID = scanner.nextLine();

        try {
            Appointment existingAppointment = Appointment.getAppointmentById(appointmentID, connection);

            if (existingAppointment != null) {
                System.out.print("Enter new appointment description: ");
                String newDescription = scanner.nextLine();

                existingAppointment.setDescription(newDescription);
                existingAppointment.setAppointmentDate(new Date()); // You can modify this as needed

                existingAppointment.updateInDatabase(connection);
                System.out.println("Appointment updated successfully.");
            } else {
                System.out.println("Appointment not found.");
            }
        } catch (Exception e) {
            System.out.println("Error updating appointment: " + e.getMessage());
        }
    }

    //delete appointments
    private static void deleteAppointment(Connection connection, Scanner scanner) {
        System.out.print("Enter appointment ID to delete: ");
        String appointmentID = scanner.nextLine();

        try {
            Appointment existingAppointment = Appointment.getAppointmentById(appointmentID, connection);

            if (existingAppointment != null) {
                existingAppointment.deleteFromDatabase(connection);
                System.out.println("Appointment deleted successfully.");
            } else {
                System.out.println("Appointment not found.");
            }
        } catch (Exception e) {
            System.out.println("Error deleting appointment: " + e.getMessage());
        }
    }

    //views appointment details
    private static void viewAppointmentDetailsById(Connection connection, Scanner scanner) {
        System.out.print("Enter appointment ID to view details: ");
        String appointmentID = scanner.nextLine();

        try {
            Appointment appointment = Appointment.getAppointmentById(appointmentID, connection);

            if (appointment != null) {
                System.out.println("Appointment details:");
                System.out.println(appointment);
            } else {
                System.out.println("Appointment not found.");
            }
        } catch (Exception e) {
            System.out.println("Error fetching appointment details: " + e.getMessage());
        }
    }


}