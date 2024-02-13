package clinic_app;

import java.util.List;

public class DoctorUtility implements Utility {

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
    public Person findPerson(List listPerson, String IdPerson) {
        // TODO Auto-generated method stub
        List<Doctor> listDoctor = listPerson;
        for (Doctor doctor: listDoctor){
                   if(doctor.getsDoctorID().equals(IdPerson)){
                        return (Person)doctor;
                   } 
        }
        throw new UnsupportedOperationException("Unimplemented method 'findPatient'");
    }
    
}
