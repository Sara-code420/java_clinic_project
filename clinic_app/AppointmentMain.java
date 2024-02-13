package clinic_app;
/**
 * @author DMS
 * @since 2023-03-11
 * @version 1.0
 * @return This class include the main execute of appointment 
 */

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Scanner;

public class AppointmentMain{
   public static void main(String[] args) { 
    //Simulate Data of a Patient 
        addAppointent(); 
    }
    /**
     * addAppointment
     */
    public static void addAppointent(){
        try{
            Scanner input = new Scanner (System.in);
            System.out.println ( "Please type Id of the doctor" );
            String doctorId =  input.next();
            //Validate if the cdoctor exist
            if(!DoctorMain.ValidateExistDoctor(doctorId))
                throw new IllegalArgumentException("The Doctor doesn´t exist in the system"); 
            if(!DoctorMain.ValidateDoctorStillDoctor(doctorId))
                throw new IllegalArgumentException("Actually the doctor with ID "+doctorId+" doesn't works in the hospital, it isn't possible schedule an appointment");     
                           //Retreive information form appointmet
            
            AppointmentRetreiveMain.selectAppointmentDoc(doctorId);
            System.out.println("Please Id of the patient");
            String patientId = 	input.next();
            if(!PatientMain.ValidateExistPatient(patientId)){
                throw new IllegalArgumentException("The Patient doesn´t exist in the system"); 
                //Retreive information form appointmet
            }
            AppointmentRetreiveMain.selectAppointmentPat(patientId);
            System.out.println("Please day of appointment");
            String dayAp = 	input.next();
            System.out.println("Please month of appointment");
            String monthAp = input.next();
            System.out.println("Please year of appointment");
            String yearAp = input.next();
            System.out.println("Please Hour  of appointment this must be beetwent 9 and 18 ");
            int hourAp = new Integer(input.next());
            System.out.println("Please minutes  of appointment, this must be zero or thirty");
            int minuteAp = new Integer(input.next());
            //validate available for docotor
            boolean availablePat =  AppointmentRetreiveMain.validateAvailablePatient(patientId, yearAp, dayAp, monthAp, hourAp, minuteAp);
            boolean availableDoc =  AppointmentRetreiveMain.validateAvailableDoctor(doctorId, yearAp, dayAp, monthAp, hourAp, minuteAp);
            if(availableDoc && availablePat){
                String[] dataInsert= new String[6]; 
                dataInsert[0] = patientId;
                dataInsert[1] = doctorId;
                dataInsert[2] = yearAp+"-"+monthAp+"-"+dayAp;
                dataInsert[3] = Integer.toString(hourAp);
                dataInsert[4] = Integer.toString(minuteAp);
                dataInsert[5] = Integer.toString(1);
                insertDataAppointent(dataInsert);
            }
        }catch(SQLException sql){
                System.err.println("MysqlError in the Main class ");
                sql.printStackTrace();
                addAppointent();
        }catch(IllegalArgumentException lx){
            System.err.println("Error in the Main class ");
            lx.printStackTrace();
            addAppointent();
        }catch(Exception ex){
            System.err.println("Error in the Ilegal exception class ");
            ex.printStackTrace();
            addAppointent();
        }
    }

    /*
    * Method that define the metedada to Insert Data Mysql
    */    
    public static void insertDataAppointent(String[] dataInsert) throws SQLException{
        String tableApointemnt = "Appointment";
        String[] columnsApointemnt ={"PatientId","DoctorId","DateApp","HourApp","MinuteApp","StateAppointment"}; 
        String valuesApp = "?,?,?,?,?,?";		
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
        final Connection conn = DriverManager.getConnection("jdbc:mysql://localhost/clinic_app", "root", "Sa.,");
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
