package clinic_app;
/**
 * @author DMS
 * @since 2023-03-11
 * @version 1.0
 * @return This class include the main execute of treatment 
 */

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Scanner;

public class TreatmentMain {
    public static void main(String[] args) {  
        addTreatment();
    }

    /**
 * 
 * treatment
 */
public static void addTreatment(){
    try{
       //  DoctorUtility doc = new DoctorUtility();
       // PatientUtility pat = new PatientUtility(); 
        Scanner input = new Scanner (System.in);
        System.out.println ( "Please type name of the treatment " );
        String treatmentName =  input.next();
        System.out.println ( "Please type Id of the doctor" );
        String doctorId =  input.next();
        if(!DoctorMain.ValidateExistDoctor(doctorId))
            throw new IllegalArgumentException("The Doctor doesn´t exist in the system"); 
        System.out.println("Please type Id of the patient");
        String patientId = 	input.next();
        if(!PatientMain.ValidateExistPatient(patientId))
          throw new IllegalArgumentException("The Patient doesn´t exist in the system");
        System.out.println("Please type therapy name ");      
        String therapyName = 	input.next();
        //Patient patient = (Patient)pat.findPerson(listPatients, patientId);
        //Doctor doctor = (Doctor)doc.findPerson(listDoctors, doctorId);
        //Treatment treatment = new Treatment(doctor, therapyName, treatmentName); 
        //patient.addTreatment(treatment);
        String[] dataInsert= new String[5]  ; 
        dataInsert[0] = patientId;
        dataInsert[1] = doctorId;
        dataInsert[2] = treatmentName;
        dataInsert[3] = therapyName;
        insertDataTreatment(dataInsert);
    }catch(SQLException sql){
            System.err.println("MysqlError in the Main class ");
            sql.printStackTrace();
    }catch(Exception ex){
      System.err.println("Error in the Main class ");
      ex.printStackTrace();
    }
  }

  /*
     * 
     *  Method that define the metedada to Insert Data Mysql  
     */
    public static void insertDataTreatment(String[] dataInsert) throws SQLException{
        String tableApointemnt = "Treatment";
        String[] columnsApointemnt ={"PatientId","DoctorId","TreatmentName","TherapyNameName"}; 
        String valuesApp = "?,?,?,?";		
        InsertDataMysql(dataInsert,tableApointemnt,columnsApointemnt,valuesApp);
    }
/*
  *Insert information MySQL
  *
  */
	/**
	 * @param reader
	 * @param tableName
	 * @param columns
	 * @param values
	 * @throws SQLException
	 */
	public static void InsertDataMysql(String[] dataInsert, String tableName, String[] columns,String values) throws SQLException{ 
        // Load the MySQL JDBC driver
          // Connect to the database      
          final Connection conn = DriverManager.getConnection("jdbc:mysql://localhost/clinic_app", "root", "Root_2023");
        try{
            Class.forName("com.mysql.cj.jdbc.Driver");
            // Build the SQL insert statement
            String sql = "INSERT INTO " + tableName + " (" + String.join(", ", columns) + ") VALUES ("+values+")";
            PreparedStatement statement = conn.prepareStatement(sql);
            for (int i = 0; i < columns.length; i++) {
                statement.setString(i + 1, dataInsert[i]);
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
}
