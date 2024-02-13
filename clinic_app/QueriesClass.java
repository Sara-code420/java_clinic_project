package clinic_app;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;


public class QueriesClass {

    public static void main(String[] args)  {

    
    Scanner scan = new Scanner(System.in);
    System.out.println("Type the table name \n List \n 1.doctors \n 2.patients \n 3.treatment");

    String table = scan.next();

    String mysql = "select * from " + table;

    if (table.equals("doctors")) {
        System.out.println("Doctor_Name Doctor_Address Doctor_Birthdate Doctor_PhoneContact "+
        "Doctor_StartDate Doctor_EndDate DoctorID Doctor_Specialty");
        System.out.println("--------------------------------------------------------------------------------");
        try{
            runQuery(mysql);
        } catch (SQLException e) {
                e.printStackTrace();
            }
    } else if ( table.equals("patients")){
        System.out.println(" Name Address\t Birthdate " +
                    "PhoneContact PatientID Insurance");
        System.out.println("--------------------------------------------------------------------------------");
        try{
            runQuery(mysql);
        } catch (SQLException e) {
                e.printStackTrace();
            }
    } else if (table.equals( "treatment")){
        System.out.println(" TreatmentId PatientId Birthdate " +
        "TreatmentName TherapyNameName ");
        System.out.println("--------------------------------------------------------------------------------");
        try{
            runQuery(mysql);
        } catch (SQLException e) {
                e.printStackTrace();
            }
    } else if (!table.equals("doctors")|| !table.equals("patients") || !table.equals("treatment")){
        System.out.println(" Please type a valid table name or Access Denied");
       
    } 
    scan.close();


    } 
        
        
    public static void runQuery( String sql) throws SQLException {

        try{

        DatabaseConnection dbconn = new DatabaseConnection();
        Connection conn = dbconn.getConnection();
    

        Statement statement = conn.createStatement();
        ResultSet rs = statement.executeQuery(sql); // Execute the SQL query and store the results in a ResultSet object

        ResultSetMetaData metaData = rs.getMetaData(); // Get metadata about the ResultSet

        int columnCount = metaData.getColumnCount(); // Get the number of columns in the ResultSet

        while (rs.next()) { // Loop through the ResultSet
            for (int i = 1; i <= columnCount; i++) { // Loop through each column
                System.out.print(rs.getString(i) + " "); // Print the value of the current cell
            }
            System.out.println(); // Print a newline character to move to the next row
        }
    } catch (SQLException e) {
        e.printStackTrace();
    }
}

}



