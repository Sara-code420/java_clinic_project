package clinic_app;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

public class DoctorbySpecialty {

    public static void main(String[] args) {

        Scanner doctorInput = new Scanner (System.in);


            System.out.println("\nGENERAL PRACTIONER"+
            "\nALLERGY"+
            "\nIMMUNOLOGY"+
            "\nANESTHESIOLOGY"+
            "\nCARDIOLOGY"+
            "\nDERMATOLOGY"+
            "\nENDOCRINOLOGY"+
            "\nGASTROENTEROLOGY"+
            "\nGYNECOLOGY"+
            "\nNEUROLOGY"+
            "\nONCOLOGY"+
            "\nPEDIATRIC"+
            "\nPSYCHIATRY"+
            "\nUROLOGY"
            );
            System.out.println("\nType the Speicality");

            String doctorSpecialty  = doctorInput.next();
            doctorInput.nextLine(); 

        final String DATABASE_URL = "jdbc:mysql://localhost:3306/clinic_app";
        Connection conn = null;

        try{

        conn = DriverManager.getConnection(DATABASE_URL, "root", "Root_2023");

        Statement statement = conn.createStatement();
      
        String sql = "SELECT Doctor_Name, Doctor_PhoneContact, Doctor_DoctorID, Doctor_Specialty FROM doctors WHERE Doctor_Specialty = " + "'" + doctorSpecialty + "'";
        ResultSet queryResult = statement.executeQuery(sql);
      

            System.out.println("Doctor_Name\t" + "Doctor_PhoneContact\t" + "Doctor_DoctorID\t\t" + "Doctor_Specialty");
            System.out.println("-----------------------------------------------------------------------------------");

            while ( queryResult.next() ){
                System.out.println(queryResult.getString("Doctor_Name") 
                + "\t\t" + queryResult.getString("Doctor_PhoneContact") 
                + "\t\t" + queryResult.getString("Doctor_DoctorID") 
                + "\t\t\t" + queryResult.getString("Doctor_Specialty")
                ); 
                System.out.println();
            } // end while loop
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        } // to catch other exception
        catch(Exception e) {
            System.out.println(e.getMessage());
            e.printStackTrace();
        } finally {
            // close the database connection 
            if (conn != null) {
                try {
                    conn.close();
                } catch (SQLException ex) {
                    System.out.println("Error closing database connection: " + ex.getMessage());
                }
            }
        }
        

    }
        
}


    

