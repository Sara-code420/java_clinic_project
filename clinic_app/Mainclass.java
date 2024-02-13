package clinic_app;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Scanner;

public class Mainclass{
    private final static TaskOption[] choices = TaskOption.values();
    private static List<Patient> listPatients = new ArrayList<Patient>();
    private static List<Doctor> listDoctors = new ArrayList<Doctor>();

/**
 * @param args
 */
public static void main(String[] args) {  
   //Simulate Data of a Patient 
    String sName = "Anny Lord";
    String sAddress = "XX.XX.XX";
    Date date = new Date(01022020);
    String sPhoneContact = "721000";
    String sInsurance_Provider = "5555-667";
    String iPatient_ID = "999";
    Scanner input = new Scanner(System.in);
    TaskOption TaskType = getRequest(input);
    //Instance Object Patient and add to List
    Patient patient = new Patient(sName,sAddress,date,sPhoneContact,sInsurance_Provider,iPatient_ID);
    //add a List of Patient
    listPatients.add(patient);   
    //-----------------------------------
    //Simulate Data of a Doctor
    String sNameDc = "Diego Mesa";
    String sAddressDc = "AA.AAA.AAA";
    Date dBirthdateDc = new Date(1021988);
    String sPhoneContactDC = "999"; 
    Date dStartDateDC = new Date(22032023); 
    Date dEndDateDC = new Date(22032023);
    String sDoctorID = "999";
    int sSpeciality = 2;
  //Instance Object Doctor and add to List
    Gen_Special doctor = new Gen_Special(sNameDc,sAddressDc,dBirthdateDc,sPhoneContactDC,dStartDateDC,dEndDateDC,sDoctorID,sSpeciality); 
    listDoctors.add(doctor);
   //Menu
    while (TaskType != TaskOption.EXIT) {
         switch (TaskType) {
           // case ADD_DOCTOR_DETAILS:
           //    AddDoctor(); //Calls the method to add records 
            //   break;
            //case ADD_PATIENT_DETAILS: 
             //  break;
           // case RETRIEVE_DOCTOR_INFORMATION (5):
            //   retreiveDoctor(); //Calls the method to get data from file through MySQl 
            // break;
			 case ADD_APPOINTMENT:
                addAppointent();
			 break;
             default:
               System.out.println("Invalid choice");
             break;
         } // end switch ase
    }
}
/*
 *TaskOption 
 */
private static TaskOption getRequest(Scanner input) {
    int request = 10;
    System.out.printf("%nEnter request%n%s",
    " 4 - Add Appointment Information");
    try {
        do { // input user request
           System.out.printf("%n? ");
           request = input.nextInt();
        } while ((request < 1) || (request > 10));
     } catch (NoSuchElementException noSuchElementException) {
        System.err.println("Invalid input. Terminating.");
     }
     return choices[request - 1]; // return enum value for option
  }

/**
 * addAppointment
 */
public static void addAppointent(){
        try{
            DoctorUtility doc = new DoctorUtility();
            PatientUtility pat = new PatientUtility(); 
            Scanner input = new Scanner (System.in);
            System.out.println ( "Please type Id of the doctor" );
            String doctorId =  input.next();
            System.out.println("Please Id of the pacient");
            String patientId = 	input.next();
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
            //Get Patient from clinic object
            Patient patient = (Patient)pat.findPerson(listPatients, patientId);
            Doctor doctor = (Doctor)doc.findPerson(listDoctors, doctorId);
            Date theDate = new Date(new Integer(dayAp+monthAp+yearAp));
            Appointment app = new Appointment(theDate,hourAp,minuteAp,patient,true);
            doctor.AddAppointment(app);
            String[] dataInsert= new String[5]  ; 
            dataInsert[0] = patientId;
            dataInsert[1] = doctorId;
            dataInsert[2] = yearAp+"-"+monthAp+"-"+dayAp;
            dataInsert[3] = Integer.toString(hourAp);
            dataInsert[4] = Integer.toString(minuteAp);
            insertData(dataInsert);
 //           appOutput.writeObject(app);
  //          readRecords();
  //          closeFile("Appointment");
        }catch(SQLException sql){
                System.err.println("Error in the Main class ");
                sql.printStackTrace();
        }catch(Exception ex){
          System.err.println("Error in the Main class ");
          ex.printStackTrace();
        }
    }

   /*
    * Insert Data Mysql
    */
    
    public static void insertData(String[] dataInsert) throws SQLException{
        //String fileName = "DoctorFile.ser";
        //String tableName = "DoctorTable";
        String tableApointemnt = "Appointment";
        //String[] columns = { "firstName", "lastName", "doctorID" };
        String[] columnsApointemnt ={"PatientId","DoctorId","DateApp","HourApp","MinuteApp"}; 
        //String values = "?,?";
        String valuesApp = "?,?,?,?,?";		
          // Read the data from the text file
        //BufferedReader reader = new BufferedReader(new FileReader(DoctorFile.ser));
        //BufferedReader readerApp = new BufferedReader(new FileReader(AppointmentFile.ser));
        //Call for Doctor
        //InsertDataMysql(reader,tableName,columns,values);
        //Call for Appointment
        InsertDataMysql(dataInsert,tableApointemnt,columnsApointemnt,valuesApp);
  //    reader.close();
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
      final Connection conn = DriverManager.getConnection("jdbc:mysql://localhost/clinic_app", "root", "sa.,");
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


