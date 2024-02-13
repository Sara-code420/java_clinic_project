package clinic_app;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

public class RemovePatientbyID {

    public static void main(String[] args) {
        

    Scanner patientInput = new Scanner (System.in);

    System.out.println("\nTo delete a patient record from patient table. Please provide PatientID");

    String patientID  = patientInput.next();
      patientInput.nextLine();

      final String DATABASE_URL = "jdbc:mysql://localhost:3306/clinic_app";
      Connection conn = null;

      try{

      conn = DriverManager.getConnection(DATABASE_URL, "root", "Root_2023");

      Statement statement = conn.createStatement();

      System.out.println("\nDeleting the patient from Patients table.");
            
            statement.executeUpdate("delete from patients where Patient_PatientID = " + "'" + patientID + "'");
            System.out.println("After the deletion patient, here is the updated table.**");
            ResultSet queryResult = statement.executeQuery("select * from patients");

            queryResult = statement.executeQuery("select * from patients");
            System.out.println(" Name\t\t" + "Address\t\t" + "Birthdate\t" +
                    "PhoneContact\t" + "PatientID\t" + "Insurance");
            System.out.println("--------------------------------------------------------------------------------");

    //       

            while (queryResult.next()) {
                System.out.print(queryResult.getString("Patient_Name")
                        + "\t\t" + queryResult.getString("Patient_Address") + "\t\t"
                        + queryResult.getString("Patient_Birthdate") + "\t"
                        + queryResult.getString("Patient_PhoneContact") + "\t"
                        + queryResult.getString("Patient_PatientID") + "\t\t"
                        + queryResult.getString("Patient_Insurance"));
                System.out.println();
            } 
            
        }  catch (SQLException ex) {
                System.out.println(ex.getMessage());
            }
            // To catch any other exception
            catch (Exception ex) {
                System.out.println(ex.getMessage());
                ex.printStackTrace();
            } finally {
                // Close the connection
                if (conn != null) {
                    try {
                        conn.close();
                    } catch (SQLException ex) {
                        System.out.println("Error closing database connection: " + ex.getMessage());
                    }
                }
            }
     // end method main
    // end class ParameterizedQuery

    
}

    }







