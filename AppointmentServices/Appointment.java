import java.sql.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * The Appointment class represents information about appointments.
 * It includes methods for interacting with a MySQL database.
 *
 * Enhancements include improved code structure, MySQL database integration, and Java connectivity.
 *
 * @author Isabel Rodarte
 */
public class Appointment {

    private String appointmentID;
    private Date appointmentDate;
    private String description;

    /**
     * Constructs an Appointment object with the specified details.
     *
     * @param appointmentID   The ID of the appointment.
     * @param appointmentDate The date of the appointment.
     * @param description     The description of the appointment.
     * @throws Exception if the input parameters are invalid.
     */
    public Appointment(String appointmentID, Date appointmentDate, String description) throws Exception {
        if (appointmentID == null || appointmentID.length() > 10 || appointmentDate == null || description == null || description.length() > 50) {
            throw new Exception("Invalid input parameters");
        }
        this.appointmentID = appointmentID;
        this.appointmentDate = appointmentDate;
        this.description = description;
    }

    /**
     * Gets the date of the appointment.
     *
     * @return The date of the appointment.
     */
    public Date getAppointmentDate() {
        return appointmentDate;
    }

    /**
     * Sets the date of the appointment.
     *
     * @param appointmentDate The new date of the appointment.
     */
    public void setAppointmentDate(Date appointmentDate) {
        this.appointmentDate = appointmentDate;
    }

    /**
     * Gets the description of the appointment.
     *
     * @return The description of the appointment.
     */
    public String getDescription() {
        return description;
    }

    /**
     * Sets the description of the appointment.
     *
     * @param description The new description of the appointment.
     */
    public void setDescription(String description) {
        this.description = description;
    }

    /**
     * Gets the ID of the appointment.
     *
     * @return The ID of the appointment.
     */
    public String getAppointmentID() {
        return appointmentID;
    }

    // Database operations

    /**
     * Saves the appointment to the database.
     *
     * @param connection The database connection.
     * @throws SQLException if a database access error occurs.
     */
    public void saveToDatabase(Connection connection) throws SQLException {
        try (PreparedStatement statement = connection.prepareStatement(
                "INSERT INTO appointment (appointmentID, appointmentDate, description) VALUES (?, ?, ?)")) {
            statement.setString(1, this.appointmentID);
            statement.setDate(2, new java.sql.Date(this.appointmentDate.getTime()));
            statement.setString(3, this.description);

            statement.executeUpdate();
        }
    }

    /**
     * Retrieves an appointment by ID from the database.
     *
     * @param appointmentID The ID of the appointment to retrieve.
     * @param connection    The database connection.
     * @return The Appointment object if found, otherwise null.
     * @throws Exception if a database access error occurs.
     */
    public static Appointment getAppointmentById(String appointmentID, Connection connection) throws Exception {
        try (PreparedStatement statement = connection.prepareStatement(
                "SELECT * FROM appointment WHERE appointmentID = ?")) {
            statement.setString(1, appointmentID);
            ResultSet resultSet = statement.executeQuery();

            if (resultSet.next()) {
                return new Appointment(
                        resultSet.getString("appointmentID"),
                        resultSet.getDate("appointmentDate"),
                        resultSet.getString("description")
                );
            } else {
                return null; // Appointment not found
            }
        }
    }

    /**
     * Updates the appointment in the database.
     *
     * @param connection The database connection.
     * @throws SQLException if a database access error occurs.
     */
    public void updateInDatabase(Connection connection) throws SQLException {
        try (PreparedStatement statement = connection.prepareStatement(
                "UPDATE appointment SET appointmentDate = ?, description = ? WHERE appointmentID = ?")) {
            statement.setDate(1, new java.sql.Date(this.appointmentDate.getTime()));
            statement.setString(2, this.description);
            statement.setString(3, this.appointmentID);

            statement.executeUpdate();
        }
    }

    /**
     * Deletes the appointment from the database.
     *
     * @param connection The database connection.
     * @throws SQLException if a database access error occurs.
     */
    public void deleteFromDatabase(Connection connection) throws SQLException {
        try (PreparedStatement statement = connection.prepareStatement(
                "DELETE FROM appointment WHERE appointmentID = ?")) {
            statement.setString(1, this.appointmentID);

            statement.executeUpdate();
        }
    }

    /**
     * Retrieves all appointments from the database.
     *
     * @param connection The database connection.
     * @return A list of all appointments in the database.
     * @throws Exception if a database access error occurs.
     */
    public static List<Appointment> getAllAppointments(Connection connection) throws Exception {
        List<Appointment> appointments = new ArrayList<>();

        try (PreparedStatement statement = connection.prepareStatement("SELECT * FROM appointment");
             ResultSet resultSet = statement.executeQuery()) {
				 while (resultSet.next()) {
					 String appointmentID = resultSet.getString("appointmentID");
					 Date appointmentDate = resultSet.getDate("appointmentDate");
					 String description = resultSet.getString("description");
					 Appointment appointment = new Appointment(appointmentID, appointmentDate, description);
					 appointments.add(appointment);
            }
        }
        return appointments;
    }
}