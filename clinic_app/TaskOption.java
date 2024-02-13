package clinic_app;
public enum  TaskOption{
        DOCTORMAIN(1), 
        PATIENTMAIN(2),
        ADD_TREATMENT(3),
        ADD_APPOINTMENT(4),
        RETRIEVE_APPOINTMENT_INFORMATION_BY_DOC(5),
        RETRIEVE_TREATMENT_INFORMATION_BY_PAT(6),
        RETRIEVE_APPOINTMENT_INFORMATION(7),
        REMOVEPATIENTBYID(8), 
        DOCTORBYSPECIALTY (9),  
        DOCTORENDDATE (10),
        QUERIES(11),
        CANCEL(12),
        EXIT (13);

        private final int value; // current menu option
        
        private TaskOption (int value){
            this.value = value;
        }

        public int getValue(){
            return value;
        }
 }
 





