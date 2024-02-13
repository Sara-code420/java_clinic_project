package clinic_app;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

public class TreatmentRetreiveMain {
    
    public static void main(String[] args){
        try{
            Scanner input = new Scanner (System.in);
            System.out.println ( "Please type Id of the patient" );
            String idPatient = input.next();
            if(!PatientMain.ValidateExistPatient(idPatient))
                throw new IllegalArgumentException("The Patient doesn´t exist in the system");
               selectTreatments(idPatient); 
          }catch(Exception ex){
              ex.printStackTrace();
          }

    }

    /*
     * 
     * 
     */
    private static void selectTreatments(String idPatient) {
        final String SELECT_QUERY = "SELECT TreatmentId, PatientId, DoctorId, TherapyNameName   FROM treatment"+
        " Where PatientId = '"+idPatient+"'";
          retreiveDataMysql(SELECT_QUERY);
      }

    /**
     * @param <ResultSetMetaData>
     * @param sql_query
     */
    public static <ResultSetMetaData> void retreiveDataMysql(String sql_query){
         try{
             
             final Connection conn = DriverManager.getConnection("jdbc:mysql://localhost/clinic_app", "root", "Root_2023");
             Class.forName("com.mysql.cj.jdbc.Driver");
             Statement statement = conn.createStatement();
             ResultSet resultSet = statement.executeQuery(sql_query);              
         System.out.println("TreatmentId\t\t" + "PatientId\t\t" + "DoctorId\t\t" + "TherapyNameName\t\t");
         System.out.println("--------------------------------------------------------------------------------------------------------------");
           while (resultSet.next()) {
                  System.out.println(resultSet.getString("TreatmentId") 
                  + "\t\t\t" + resultSet.getString("PatientId") 
                  + "\t\t\t" + resultSet.getString("DoctorId") 
                  + "\t\t\t" + resultSet.getString("TherapyNameName")
                  ); 
            }
         }catch(SQLException sql){
             sql.printStackTrace();
         }catch(Exception ex){ 
             ex.printStackTrace();
         }
       }
       
}
