import java.sql.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * The AppointmentService class manages appointment information and interacts with a MySQL database.
 * Enhancements include improved code structure, MySQL database integration, and Java connectivity.
 *
 * @author Isabel Rodarte
 */
public class AppointmentService {

    private List<Appointment> appointments;
    private Connection connection;

    /**
     * Adds an appointment to the service and saves it to the database.
     *
     * @param appointment The appointment to add.
     */
    public void addAppointment(Appointment appointment) {
        boolean appointmentAlreadyExists = false;
        for (Appointment app : appointments) {
            if (app.getAppointmentID().equals(appointment.getAppointmentID())) {
                appointmentAlreadyExists = true;
                break;
            }
        }

        if (!appointmentAlreadyExists) {
            appointments.add(appointment);
            // Save the appointment to the database
            try {
                appointment.saveToDatabase(connection);
            } catch (SQLException e) {
                // Handle the exception appropriately in a real-world application
                e.printStackTrace();
            }
        }
    }

    /**
     * Deletes an appointment from the service and the database.
     *
     * @param appointmentId The ID of the appointment to delete.
     */
    public void deleteAppointment(String appointmentId) {
        Appointment appointmentToRemove = null;
        for (Appointment appointment : appointments) {
            if (appointment.getAppointmentID().equals(appointmentId)) {
                appointmentToRemove = appointment;
                break;
            }
        }

        if (appointmentToRemove != null) {
            appointments.remove(appointmentToRemove);

            // Delete the appointment from the database
            try {
                appointmentToRemove.deleteFromDatabase(connection);
            } catch (SQLException e) {
                // Handle the exception appropriately in a real-world application
                e.printStackTrace();
            }
        }
    }

    /**
     * Gets a read-only list of appointments.
     *
     * @return The list of appointments.
     */
    public List<Appointment> getAppointments() {
        return Collections.unmodifiableList(appointments);
    }
}