import java.sql.*;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Java program is for appointment information.
 * Enhancements includes improved code structure, creating a database with MySQL and building a path to connect it to Java.
 * 
 * @author Isabel Rodarte
 */

public class AppointmentService {

    private List<Appointment> appointments;
    private Connection connection;
    public void addAppointment(Appointment appointment) {
        boolean appointmentAlreadyExists = false;
        for (Appointment app : appointments) {
            if (app.getAppointmentID().equals(appointment.getAppointmentID())) {
                appointmentAlreadyExists = true;
                break;
            }
        }

        if (!appointmentAlreadyExists)
        {
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

    public void deleteAppointment(String appointmentId) {
        for (Appointment appointment : appointments) {
            if (appointment.getAppointmentID().equals(appointmentId)) {
                appointments.remove(appointment);

                // Delete the appointment from the database
                try {
                    appointment.deleteFromDatabase(connection);
                } catch (SQLException e) {
                	 // Handle the exception appropriately in a real-world application
                    e.printStackTrace();
                }

                return;
            }
        }
    }

    public List<Appointment> getAppointments() {
        return Collections.unmodifiableList(appointments);
    }
}