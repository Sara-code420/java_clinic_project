package clinic_app;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

public class AppointmentRetreiveMain{

    public static void main(String[] args) {  
    //Simulate Data of a Patient 
        retreiveAppointment();
    }

    /*
 * Porpuse: retrieve all information of the table appointments
 */

public static void loadAppointments(){
    final String SELECT_QUERY = "SELECT AppointmentId, PatientId, DoctorId, DateApp, HourApp, MinuteApp  FROM appointment";
      retreiveDataMysql(SELECT_QUERY);
 }
 /*
  * Porpuse:  Retreive detail Information of appointment for a specific doctor and patient  
  */
  public static void  retreiveAppointment(){
    try{
      Scanner input = new Scanner (System.in);
      System.out.println ( "Please type Id of the doctor" );
      String idDoctor = input.next();
      System.out.println ( "Please type Id of the Patient" );
      String idPatient = input.next();      
        retreiveInformationAppointment(idDoctor,idPatient);

  //      selectAppointmentDoc(idPatient); 
    }catch(Exception ex){
        retreiveAppointment();
        ex.printStackTrace();
    }
  }

 /*
  * Porpuse: Retreive the  Appointments by Doctor
  */
  public static void  retreiveAppointmentByDoctor(){
    try{
      Scanner input = new Scanner (System.in);
      System.out.println ( "Please type Id of the doctor" );
      String idDoctor = input.next();
        selectAppointmentDoc(idDoctor);
  //      selectAppointmentDoc(idPatient); 
    }catch(Exception ex){
        ex.printStackTrace();
    }
  }
 
  
 /*
  * Porpuse: Retreive  the Appointments by Doctor
  */
  public static void  retreiveAppointmentByPatient(){
    try{
      Scanner input = new Scanner (System.in);
      System.out.println ( "Please type Id of the patient" );
      String idPatient = input.next();
       selectAppointmentPat(idPatient); 
    }catch(Exception ex){
        ex.printStackTrace();
    }
  }

  /**
   * Porpuse Retreive information of the appointment 
   */
private static void retreiveInformationAppointment(String idDoctor, String idPatient) {
  final String SELECT_QUERY = "SELECT AppointmentId, PatientId, DoctorId, DateApp, HourApp, MinuteApp, StateAppointment   FROM appointment"+
      " Where DoctorId = '"+idDoctor+"' and PatientId ='"+idPatient+"'";
      System.out.println("**!!Appointments Details by Doctors and Patient!!**");
        retreiveDataMysql(SELECT_QUERY);
  }

  /*
 * Porpuse select information of appointment by doctor 
 * 
 */
  public static void selectAppointmentDoc(String idDoctor) throws Exception{      
      final String SELECT_QUERY = "SELECT AppointmentId, PatientId, DoctorId, DateApp, HourApp, MinuteApp, StateAppointment  FROM appointment"+
          " Where DoctorId = '"+idDoctor+"'";
          System.out.println("******************************!!Appointments Details by Doctor!!***********************");
            retreiveDataMysql(SELECT_QUERY);
}

/*
 * Porpuse select information of appointment of Patient 
 * 
 */
public static void selectAppointmentPat(String idPatient) throws Exception{      
    final String SELECT_QUERY = "SELECT AppointmentId, PatientId, DoctorId, DateApp, HourApp, MinuteApp, StateAppointment  FROM appointment"+
        " Where PatientId = '"+idPatient+"'";
        System.out.println("*****************************!!Appointments Details by Patient!!******************************");
          retreiveDataMysql(SELECT_QUERY);
}
/*
 * Porpuse Validate if the patient has available to get appointment  for specific time
 */
public static boolean validateAvailablePatient(String idPatient, String yearAp, String dayAp, String monthAp,int hourAp, int minuteAp){
    boolean apply = true;
    final String SELECT_QUERY = "SELECT count(*)  FROM appointment"+
          " Where PatientId = '"+idPatient+"' and DateApp = '"+yearAp+"-"+monthAp+"-"+dayAp+
          "' and HourApp="+hourAp+" and minuteApp = "+minuteAp;
  if(validateDataMysql(SELECT_QUERY) > 0){
      System.out.println("there is already an appointment for patient "+idPatient+" for this time");
      apply = false;
  }
  return apply;
}
/*
 * Porpuse Validate if the patient has available to get appointment  for specific time
 */
public static boolean validateAvailableDoctor(String idDoctor, String yearAp, String dayAp, String monthAp, int hourAp, int minuteAp){
  boolean apply = true;
  final String SELECT_QUERY = "SELECT count(*) FROM appointment"+
        " Where DoctorId = '"+idDoctor+"' and DateApp = '"+yearAp+"-"+monthAp+"-"+dayAp+
        "' and HourApp="+hourAp+" and minuteApp = "+minuteAp;
if(validateDataMysql(SELECT_QUERY) > 0){
    System.out.println("there is already an appointment for doctor "+idDoctor+" for this time");
    apply = false;
}
return apply;
}
/*
 * 
 * Porpuse retreive information from mysql 
 */
/**
 * @param sql_query
 * @return
 */
public static void retreiveDataMysql(String sql_query){
   // PatientUtility pat = new PatientUtility(); 
   // DoctorUtility doc = new DoctorUtility();
   String state = null;
    try{
        final Connection conn = DriverManager.getConnection("jdbc:mysql://localhost/clinic_app", "root", "Sa.,");
        Class.forName("com.mysql.cj.jdbc.Driver");
        Statement statement = conn.createStatement();
        ResultSet resultSet = statement.executeQuery(sql_query);
     //  ResultSetMetaData metaData = resultSet.getMetaData();
        //int numberOfColumns = metaData.getColumnCount();
        //Columns -> PatientId, DoctorId, DateApp, HourApp, MinuteApp
//       String patientId = null;
//       String doctorId = null;
//       Date dateApp = null;
//       int hourApp = 0;
//       int minuteApp = 0;
    //recuperacion de la info.
        
    System.out.println("--------------------------------------------------------------------------------------------------------------");
    System.out.println("AppointmentId\t" + "PatientId\t" + "DoctorId\t" + "DateApp\t\t"+"HourApp\t\t" + "MinuteApp\t\t" + "State\t");
    System.out.println("--------------------------------------------------------------------------------------------------------------");
      while (resultSet.next()) {
        state = "Open";
       // for (int countRow = 1; countRow <= numberOfColumns; countRow++) {
           //   if("PatientId".equalsIgnoreCase(metaData.getColumnName(countRow)))
           //      patientId = (String) resultSet.getObject(countRow);                
           //   else if("DoctorId".equalsIgnoreCase(metaData.getColumnName(countRow)))
           //     doctorId = (String)resultSet.getObject(countRow);
           //   else if("DateApp".equalsIgnoreCase(metaData.getColumnName(countRow)))
            //    dateApp = (Date)resultSet.getObject(countRow);
            //  else if("HourApp".equalsIgnoreCase(metaData.getColumnName(countRow)))
             //    hourApp = (int)resultSet.getObject(countRow);
             // else if("MinuteApp".equalsIgnoreCase(metaData.getColumnName(countRow)))
             //    minuteApp = (int)resultSet.getObject(countRow);  
              //System.out.printf("%-2s\t", metaData.getColumnName(countRow));
              //System.out.printf(":");
             // System.out.printf("%-2s\t", resultSet.getObject(countRow));
             
             if(resultSet.getString("StateAppointment").equals("0"))
                state = "Cancel";
              System.out.println();
             System.out.println(resultSet.getString("AppointmentId") 
             + "\t\t" + resultSet.getString("PatientId") 
             + "\t\t" + resultSet.getString("DoctorId") 
             + "\t\t" + resultSet.getString("DateApp")
             +  "\t\t" + resultSet.getString("HourApp")
             +  "\t\t" + resultSet.getString("MinuteApp")
             +  "\t\t" + state); 
  //      }
      //  Patient thePatient = (Patient)pat.findPerson(listPatients, patientId);
      //  Doctor theDoctor = (Doctor)doc.findPerson(listDoctors, doctorId);
      //  app = new Appointment(dateApp,hourApp,minuteApp,thePatient,true);
      //  boolean update = theDoctor.updateAppointmentElement(app); 
       // System.out.println("update: "+update);
      //    if(!update){         
       //         theDoctor.AddAppointment(app);
        //        System.out.println("the Appointment didn´t find, so the appointment was add ");
         //     }else{
          //        System.out.println("the Appointment  found, so  the appointment was update ");
          //    }
  //        System.out.println(""); 
   //       System.out.println("-------------------------********APPOINTMENTS************-------------------------------"); 
    }
  //      System.out.printf("Authors Table of Books Database:%n%n");
    }catch(SQLException sql){
        sql.printStackTrace();
    }catch(Exception ex){ 
        ex.printStackTrace();
    }
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

   /*
    * Porpuse
    */

    public static void CancelAppointment(){

      Scanner scan = new Scanner (System.in);
  
      System.out.println("Type in the DoctorID to cancel the appointment");
      String docID = scan.next();
      scan.nextLine();
  
      
      System.out.println("Type in the PatientID to cancel the appointment");
      String patID = scan.next();
      scan.nextLine();
  

      DatabaseConnection dbconn = new DatabaseConnection();
      Connection conn = dbconn.getConnection();
  
      Long quantityRecords = (long) 0; 
  
      try{
  
  
           Statement statement = conn.createStatement();
           ResultSet resultSet = statement.executeQuery("SELECT count(*)  FROM appointment"+
                    " Where DoctorId = '"+docID+"' and PatientID = '"+patID+"' and StateAppointment = 1");
           ResultSetMetaData metaData = resultSet.getMetaData();
           int numberOfColumns = metaData.getColumnCount();
         while (resultSet.next()) {
           for (int countRow = 1; countRow <= numberOfColumns; countRow++) {
  
              quantityRecords = (Long)resultSet.getObject(countRow);
  
             // System.out.println(quantityRecords);
  
          } 
  
      }
  
             if ((long) quantityRecords > 0){
  
              System.out.println("Type the end date in format yyyy-mm-dd");
  
              String endDate = scan.next();
              scan.nextLine();
  
              String sql = "UPDATE appointment SET StateAppointment = 0 where DoctorID = ? and PatientID = ? and DateApp = ?";
  
              PreparedStatement stmt = conn.prepareStatement(sql);
  
              stmt.setString(1, docID);
              stmt.setString(2, patID);
              stmt.setString(3, endDate);  
              stmt.executeUpdate();
              System.out.println("The Appointment was canceled");  
            }else{
              throw new IllegalArgumentException("Incorrect information, please try again"); 
            }   

        }catch (SQLException sqlExp) {
                          sqlExp.printStackTrace();
                          CancelAppointment();                                          
        }catch(Exception ex){
            ex.printStackTrace();
            CancelAppointment();
        } 
  }
  
}