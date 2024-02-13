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
import java.text.SimpleDateFormat;
import java.util.List;

public class PatientMain {

  private static List<Patient> listPatients = new ArrayList<Patient>();
    
   
    public static void main(String[] args) {

      Scanner patientInput = new Scanner (System.in);

      //System.out.print("Enter the number of Patients: ");
     // int numPatients = patientInput.nextInt(); 

      
      //for (int i = 1; i < numPatients; i++){
 
          try{
           

      System.out.println("Type Patient's Details");

      System.out.println("Full name");
      String patientName  = patientInput.next();
      patientInput.nextLine();

      System.out.println("Address");
      String patientAddress = patientInput.next();
      patientInput.nextLine();
           
      System.out.println("Birthdate in the format yyyy-mm-dd");
      String patientDOB = patientInput.next();
      patientInput.nextLine();

      DateFormat dateFormat = new SimpleDateFormat("yyyy-mm-dd"); 
            
      Date patientDOBDate = null;
          
      patientDOBDate = dateFormat.parse(patientDOB);
            
            
      System.out.println("Phone Number");
      String patientPhone = patientInput.next();
      patientInput.nextLine();

          
      System.out.println("Patient ID");
      String patientID  = patientInput.next();
      patientInput.nextLine();

      System.out.println("Insurance Provider name, if no Insurance please Type NONE");
      String patientInsurance  = patientInput.next();
      patientInput.nextLine();

      Patient patient = new Patient(patientName, patientAddress, patientDOBDate, patientPhone, patientID, patientInsurance);
      listPatients.add(patient);

      String [] pats = new String[6];
      pats[0] = patientName;
      pats[1] = patientAddress;
      pats[2] = patientDOB;
      pats[3] = patientPhone;
      pats[4] = patientID;
      pats[5] = patientInsurance;
          insertPatData(pats);
        }
      catch (Exception e){
        System.out.println("Please provide the date in format yyyy-mm-dd");
        e.printStackTrace();
        main(args);  
      }   
      
      
    }

    public static void insertPatData(String[] pats) throws SQLException{
    
      String tablePatients = "Patients";
    
      String[] columnsPatients ={"Patient_Name", "Patient_Address", "Patient_Birthdate", "Patient_PhoneContact", 
      "Patient_PatientID", "Patient_Insurance"}; 
      //String values = "?,?";
      String valuespats = "?,?,?,?,?,?";		
       
      InsertDocDataMysql(pats,tablePatients,columnsPatients,valuespats);
  
  }
  
  
  public static void InsertDocDataMysql(String[] pats, String tableName, String[] columns,String values) throws SQLException{ 
      // Load the MySQL JDBC driver
        // Connect to the database      
        final Connection conn = DriverManager.getConnection("jdbc:mysql://localhost/clinic_app", "root", "Sa.,");
      try{
          Class.forName("com.mysql.cj.jdbc.Driver");
          // Build the SQL insert statement
          String sql = "INSERT INTO " + tableName + " (" + String.join(", ", columns) + ") VALUES ("+values+")";
          PreparedStatement statement = conn.prepareStatement(sql);
          for (int i = 0; i < columns.length; i++) {
              statement.setString(i + 1, pats[i]);
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
      conn.close();
      }
    }
/* 
*
*
* porpuse Validate if a Patient existe in the system
*/   
public static boolean ValidateExistPatient(String idPatient){
  boolean apply = true;
   final String SELECT_QUERY = "SELECT count(*)  FROM Patients"+
         " Where Patient_PatientID = '"+idPatient+"'";
     if(validateDataMysql(SELECT_QUERY) == 0){
     apply = false;
 }
 return apply;
}

/*
   * Porpuse Validate quantity of records of un table
   */
  /**
   * @param sql_query
   * @return
   */
  public static Long validateDataMysql(String sql_query){
    Long quatityRecords = (long) 0; 
     try{
         Connection conn = DriverManager.getConnection("jdbc:mysql://localhost/clinic_app", "root", "Sa.,");
         Class.forName("com.mysql.cj.jdbc.Driver");
         Statement statement = conn.createStatement();
         ResultSet resultSet = statement.executeQuery(sql_query);
         ResultSetMetaData metaData = resultSet.getMetaData();
         int numberOfColumns = metaData.getColumnCount();
       while (resultSet.next()) {
         for (int countRow = 1; countRow <= numberOfColumns; countRow++) {
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


      
  }
