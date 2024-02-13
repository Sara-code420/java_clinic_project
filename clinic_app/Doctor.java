package clinic_app;

import java.util.ArrayList;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;

/**
 * 
 * @author Saranya Vijayan
 * @since 2023-03-12
 * @version 1.0
 * @return the ...
 */
/*
 * 
 */
public class Doctor extends Person { 
    private Date startDate;
    private Date endDate; 
    private String doctorID;
    private String speciality;
   
    //private List<Appointment> appointments;
    public Doctor (String sName, String sAddress, Date dBirthdate, String sPhoneContact, 
    Date dStartDate, Date dEndDate, String sDoctorID, String sSpeciality ){
        super(sName, sAddress, dBirthdate, sPhoneContact);
        setdStartDate(dStartDate);
        setdEndDate(dEndDate);
        setsDoctorID(sDoctorID);
        setsSpeciality(sSpeciality);
    //   this.appointments = new ArrayList<Appointment>();
    }

    public void setdStartDate(Date thestartDate){

        this.startDate = thestartDate;
    }

    public Date getdStarDate(){
        return this.startDate;
    }

    public void setdEndDate(Date theendDate){
        this.endDate = theendDate;
    }

    public Date getdEndDate(){
        return this.endDate; 
    
    }

    public void setsDoctorID( String thedoctorID){
        this.doctorID = thedoctorID; 
    }

    public String getsDoctorID(){
        return this.doctorID;
    }

    public void setsSpeciality( String theSpeciality){
        this.speciality = theSpeciality; 
    }

    public String getsSpeciality(){
        return this.speciality;
    }

    /**

    public List<Appointment> getAppointments() {
        return appointments;
    }

    public void setAppointments(List<Appointment> appointmentsList) {
        this.appointments = appointmentsList;
    }

    public void AddAppointment(Appointment theAppointment){
        this.appointments.add(theAppointment);
    }

    
     * 
    public void updateAppointmentElement(Appointment theAppointment){
        
        for(Appointment app : this.appointments){
            if(app.getDate().compareTo(theAppointment.getDate())) {

            }

        }
    }


    /**
     * @override tostring method 
     */
    

}


