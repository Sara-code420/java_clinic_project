package clinic_app;

import java.util.List;



public class PatientUtility implements Utility {

    @Override
    public void create() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'create'");
    }

    @Override
    public void cancel() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'cancel'");
    }

    @Override
    public Person findPerson(List listPerson, String idPerson) {
        // TODO Auto-generated method stub
        List<Patient> listPatient = listPerson;
        for (Patient patient: listPatient){
                   if(patient.getPatient_ID().equals(idPerson)){
                        return (Person)patient;
                   } 
        }
        throw new UnsupportedOperationException("Unimplemented method 'findPerson'");
    }
}
