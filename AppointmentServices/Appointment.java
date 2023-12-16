import java.sql.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Appointment {
	/**
	 * Java program is for appointment information.
	 * 
	 * Enhancements includes improved code structure, creating a database with MySQL and building a path to connect it to Java.
	 * 
	 * @author Isabel Rodarte
	 */

    private String appointmentID;
    private Date appointmentDate;
    private String description;

    public Appointment(String appointmentID, Date appointmentDate, String description) throws Exception {
        if (appointmentID == null || appointmentID.length() > 10 || appointmentDate == null || description == null || description.length() > 50) {
            throw new Exception("Invalid");
        }
        this.appointmentID = appointmentID;
        this.appointmentDate = appointmentDate;
        this.description = description;
    }

    public Date getAppointmentDate() {
        return appointmentDate;
    }

    public void setAppointmentDate(Date appointmentDate) {
        this.appointmentDate = appointmentDate;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getAppointmentID() {
        return appointmentID;
    }

    // Database operations

    public void saveToDatabase(Connection connection) throws SQLException {
        try (PreparedStatement statement = connection.prepareStatement(
                "INSERT INTO appointment (appointmentID, appointmentDate, description) VALUES (?, ?, ?)")) {
            statement.setString(1, this.appointmentID);
            statement.setDate(2, new java.sql.Date(this.appointmentDate.getTime()));
            statement.setString(3, this.description);

            statement.executeUpdate();
        }
    }

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

    public void updateInDatabase(Connection connection) throws SQLException {
        try (PreparedStatement statement = connection.prepareStatement(
                "UPDATE appointment SET appointmentDate = ?, description = ? WHERE appointmentID = ?")) {
            statement.setDate(1, new java.sql.Date(this.appointmentDate.getTime()));
            statement.setString(2, this.description);
            statement.setString(3, this.appointmentID);

            statement.executeUpdate();
        }
    }

    public void deleteFromDatabase(Connection connection) throws SQLException {
        try (PreparedStatement statement = connection.prepareStatement(
                "DELETE FROM appointment WHERE appointmentID = ?")) {
            statement.setString(1, this.appointmentID);

            statement.executeUpdate();
        }
    }
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