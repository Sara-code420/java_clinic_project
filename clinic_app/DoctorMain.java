package clinic_app;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;
import java.util.ArrayList;
import java.util.Date;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;


public class DoctorMain  {

    private static List<Doctor> listDoctors = new ArrayList<Doctor>();
    
   
    public static void main(String[] args) {

        Scanner doctorInput = new Scanner (System.in);
        DateFormat dateFormat = new SimpleDateFormat("yyyy-mm-dd"); 
        Date doctorDOBDate = null;
        boolean dobValid = false;
        Date doctorStartDate = null;
        boolean startDateValid = false;
        Date doctorEndDate = null;
        boolean endDateValid = false;
       

            

            System.out.println("Type Doctor's Details");

            System.out.println("Full name in format lastname,firstname");
            String doctorName  = doctorInput.next();
            doctorInput.nextLine();
           
            
            System.out.println("Address with comma as separator");
            String doctorAddress = doctorInput.next();
            doctorInput.nextLine();

            while (!dobValid) {
                try {
                    System.out.println("Birthdate in the format yyyy-MM-dd");
                    String doctorDOB = doctorInput.nextLine();
                    doctorDOBDate = dateFormat.parse(doctorDOB);
                    dobValid = true;
                } catch (ParseException e) {
                    System.out.println("Invalid date format. Please enter the date in the format yyyy-MM-dd.");
                }
            }
       

            System.out.println("Phone Number");
            String doctorPhone = doctorInput.next();
            doctorInput.nextLine();

            while (!startDateValid) {
                try {
                    System.out.println("Start Date in the format yyyy-MM-dd");
                    String doctorStart = doctorInput.nextLine();
                    doctorStartDate = dateFormat.parse(doctorStart);
                    startDateValid = true;
                } catch (ParseException e) {
                    System.out.println("Invalid date format. Please enter the date in the format yyyy-MM-dd.");
                }
            }
        
            while (!endDateValid) {
                try {
                    System.out.println("End Date in the format yyyy-MM-dd and type 1111-11-11 if not applicable");
                    String doctorEnd = doctorInput.nextLine();
                    if (doctorEnd.equals("1111-11-11")) {
                        endDateValid = true;
                    } else {
                        doctorEndDate = dateFormat.parse(doctorEnd);
                        endDateValid = true;
                    }
                } catch (ParseException e) {
                    System.out.println("Invalid date format. Please enter the date in the format yyyy-MM-dd.");
                }
            }
            
            System.out.println("Doctor ID");
            String doctorID  = doctorInput.next();
            doctorInput.nextLine();

            System.out.println("\nType the Speicality from the list");
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

          
            String doctorSpecialty  = doctorInput.next();
            doctorInput.nextLine(); 

        /**   Doctor doctor = new Doctor (doctorName, doctorAddress, doctorDOBDate, doctorPhone, doctorStartDate, doctorEndDate, doctorID, doctorSpecialty);
            listDoctors.add(doctor);

            String [] docs = new String[8];
            docs[0] = doctorName;
            docs[1] = doctorAddress;
            docs[2] = doctorDOB;
            docs[3] = doctorPhone;
            docs[4] = doctorStart;
            docs[5] = doctorEnd;
            docs[6] = doctorID;
            docs[7] = doctorSpecialty;

            insertDocData(docs);

        */  

        doctorInput.close();

   // } 
   
}

public static void insertDocData(String[] docs) throws SQLException{
    
    String tabledoctors = "Doctors";
  
    String[] columnsDoctors ={"Doctor_Name", "Doctor_Address", "Doctor_Birthdate", "Doctor_PhoneContact", 
    "Doctor_StartDate", "Doctor_EndDate", "Doctor_DoctorID", "Doctor_Specialty"}; 
    //String values = "?,?";
    String valuesdocs = "?,?,?,?,?,?,?,?";		
     
    InsertDocDataMysql(docs,tabledoctors,columnsDoctors,valuesdocs);

}


public static void InsertDocDataMysql(String[] docs, String tableName, String[] columns,String values) throws SQLException{ 
    // Load the MySQL JDBC driver
      // Connect to the database 
      
      final String DATABASE_URL = "jdbc:mysql://localhost:3306/clinic_app";
       Connection conn = null;
       PreparedStatement statement = null;
      
      
    try{

         conn = DriverManager.getConnection(DATABASE_URL, "root", "Sa.,");
        
        //Class.forName("com.mysql.cj.jdbc.Driver").newInstance();
        // Build the SQL insert statement
        String sql = "INSERT INTO " + tableName + " (" + String.join(", ", columns) + ") VALUES ("+values+")";
        statement = conn.prepareStatement(sql);
        for (int i = 0; i < columns.length; i++) {
            statement.setString(i + 1, docs[i]);
        }
       
      // Execute the insert statement
        statement.executeUpdate();
      // Close the database connection and the file reader
        System.out.println("Data inserted successfully!");
    } catch (SQLException sqlExp) {
    sqlExp.printStackTrace();	  
    } catch (Exception e) {
    e.printStackTrace();
    }finally{
        if (conn != null) {
            conn.close();
        }
    }
    }


/*
*
* porpuse Validate if a docotr existe in the system
*/   
public static boolean ValidateExistDoctor(String idDoctor){
       boolean apply = true;
        final String SELECT_QUERY = "SELECT count(*)  FROM Doctors"+
              " Where Doctor_DoctorID = '"+idDoctor+"'";
          if(validateDataMysql(SELECT_QUERY) == 0){
          apply = false;
      }
      return apply;
}

/*
 * Porpuse Validate if a dcotor still working in the hospital
 * 
 */
public static boolean ValidateDoctorStillDoctor(String idDoctor){
    boolean apply = true;
     final String SELECT_QUERY = "SELECT count(*)  FROM Doctors"+
           " Where Doctor_DoctorID = '"+idDoctor+"' and  Doctor_EndDate = '1111-11-11'";
       if(validateDataMysql(SELECT_QUERY) == 0){
       apply = false;
   }
   return apply;
}

/*
* Porpuse Validate quantity of records of un table
*/
public static Long validateDataMysql(String sql_query){
 Long quatityRecords = (long) 0; 
  try{
      final Connection conn = DriverManager.getConnection("jdbc:mysql://localhost/clinic_app", "root", "Sa.,");
      Class.forName("com.mysql.cj.jdbc.Driver");
      Statement statement = conn.createStatement();
      ResultSet resultSet = statement.executeQuery(sql_query);
      ResultSetMetaData metaData = resultSet.getMetaData();
      int numberOfColumns = metaData.getColumnCount();
    while (resultSet.next()) {
      for (int countRow = 1; countRow <= numberOfColumns; countRow++) {
//             System.out.printf("%-8s\t", metaData.getColumnName(countRow));
//              System.out.printf(":");
//             System.out.printf("%-8s\t", resultSet.getObject(countRow));
//             System.out.printf("%-2s\t", resultSet.getObject(countRow));
//              System.out.println();
            quatityRecords = (Long)resultSet.getObject(countRow);
      }
 }
//      System.out.printf("Authors Table of Books Database:%n%n");
  }catch(SQLException sql){
      sql.printStackTrace();
  }catch(Exception ex){ 
      ex.printStackTrace();
  }finally{
    return quatityRecords;
  }
}

public static void DoctorEnddate (){

    Scanner scan = new Scanner (System.in);

    System.out.println("Type in the DoctorID to enter date of termination");

    String docID = scan.next();
    scan.nextLine();

    DatabaseConnection dbconn = new DatabaseConnection();
    Connection conn = dbconn.getConnection();

    Long quantityRecords = (long) 0; 

    try{


         Statement statement = conn.createStatement();
         ResultSet resultSet = statement.executeQuery("SELECT count(*)  FROM appointment"+
                  " Where DoctorId = '"+docID+"'");
         ResultSetMetaData metaData = resultSet.getMetaData();
         int numberOfColumns = metaData.getColumnCount();
       while (resultSet.next()) {
         for (int countRow = 1; countRow <= numberOfColumns; countRow++) {

            quantityRecords = (Long)resultSet.getObject(countRow);

           // System.out.println(quantityRecords);

        } 

    }

           if ((long) quantityRecords == 0){

            System.out.println("Type the end date in format yyyy-mm-dd");

            String endDate = scan.next();
            scan.nextLine();

            String sql = "UPDATE doctors SET Doctor_EndDate = ? where Doctor_DoctorID = ?";

            PreparedStatement stmt = conn.prepareStatement(sql);

            stmt.setString(1, endDate);
            stmt.setString(2, docID);

            stmt.executeUpdate();

        
            ResultSet queryResult = statement.executeQuery("select Doctor_Name, Doctor_DoctorID, Doctor_StartDate, Doctor_EndDate from doctors where Doctor_DoctorID = '"+docID+"'");

            System.out.println(" Name\t\t" + "DoctorID\t" 
                     + "Start_Date\t\t" +  "End_Date");
            System.out.println("--------------------------------------------------------------------------------");

            while (queryResult.next()) {
                System.out.print(queryResult.getString("Doctor_Name")
                        + "\t\t" + queryResult.getString("Doctor_DoctorID") + "\t\t"
                        + queryResult.getString("Doctor_StartDate") + "\t"
                        + queryResult.getString("Doctor_EndDate") + "\t\t");              
                System.out.println();
            }   
            
           } else {

            System.out.println("There are upcoming Appointments under this Doctor, hence cannot be terminated");

            ResultSet queryApp = statement.executeQuery("select *"+
             "from Appointment where DoctorID = '"+docID+"'" );

             System.out.println(" AppointmentID\t\t" + "PatientId\t"+"DoctorID\t" +"AppointmentDate\t\t" +
              "Hour\t" +  "Minutes");
            System.out.println("--------------------------------------------------------------------------------");

        while (queryApp.next()) {
        System.out.print(queryApp.getString("AppointmentID")
                + "\t\t" + queryApp.getString("PatientId") + "\t\t"
                + queryApp.getString("DoctorID") + "\t"
                + queryApp.getString("DateApp") + "\t"
                + queryApp.getInt("HourApp") + "\t"
                + queryApp.getInt("MinuteApp") + "\t");            
        System.out.println();
    }   

           }
           
    
}    catch (SQLException sqlExp) {
    sqlExp.printStackTrace();

} 

}

}


