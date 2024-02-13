package clinic_app;
import java.util.Scanner;

public class ClinicMain {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int selectedOption;
        do {
            System.out.println("Please input the associated number:\n"+
            "Add_Doctor_Details(1)\n"+
            "Add_Patient_Details(2)\n"+
            "Add_Treatment(3)\n"+
            "Add_Appointment(4)\n"+
            "Retrieve_Appointment_byDoctor(5)\n"+
            "Retrieve_Appointment_byPatiend(6)\n" +
            "Retrieve_Appointment_Information(7)\n" +
            "Remove a Patient (8)\n" +
            "Search_DoctorbySpeciality (9)\n" +
            "Terminating a Doctor (10)\n" +
            "To see all details of a table (11)\n" +
            "To cancel an appointmet (12)\n" +
            "Exit (13)\n"
             );
          

            selectedOption = scanner.nextInt();

            TaskOption selectedTaskOption = null;
            for (TaskOption option : TaskOption.values()) {
                if (option.getValue() == selectedOption) {
                    selectedTaskOption = option;
                    break;
                }
            }

            if (selectedTaskOption == null) {
                System.out.println("Invalid option selected!");
            } else {
                switch (selectedTaskOption) {
                    case DOCTORMAIN:
                        DoctorMain.main(args);                         
                        break;
                    case PATIENTMAIN:
                        PatientMain.main(args);
                        break;
                     case ADD_TREATMENT:
                        TreatmentMain.main(args);
                        break;
                     case ADD_APPOINTMENT:
                        AppointmentMain.main(args);
                        break;
                    case RETRIEVE_APPOINTMENT_INFORMATION_BY_DOC:
                        AppointmentRetreiveMain.retreiveAppointmentByDoctor();
                        break; 

                    case RETRIEVE_TREATMENT_INFORMATION_BY_PAT:
                        AppointmentRetreiveMain.retreiveAppointmentByPatient();
                        break;
                    
                    case RETRIEVE_APPOINTMENT_INFORMATION:
                        AppointmentRetreiveMain.retreiveAppointment();
                        break;                        
                    case REMOVEPATIENTBYID:
                        RemovePatientbyID.main(args);
                        break;
                         
                    case DOCTORBYSPECIALTY:
                //       DoctorbySpecialty.main(args);
                        break;

                    case DOCTORENDDATE:
                        DoctorMain.DoctorEnddate();
                        break;

                    case QUERIES:
                        QueriesClass.main(args);
                        break;
                    case CANCEL:
                        AppointmentRetreiveMain.CancelAppointment();
                        break;
                    case EXIT:
                        System.out.println("Exiting clinic app...");
                        break;
                    default:
                        System.out.println("Invalid menu option");
                        break;
                }
            }
        } while (selectedOption != TaskOption.EXIT.getValue());

        scanner.close();
}
}

    

