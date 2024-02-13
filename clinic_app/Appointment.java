package clinic_app;

import java.util.Date;

/**
 * @author DMS
 * @since 2023-03-11
 * @version 1.0
 * @return This class include the information about  an Appointment
 * and cancelling events 
 */

public class Appointment{
//  Data Members
    private Date date;
    private int hour;
    private int minutes;
    private Boolean stateActive; 
    private Patient patient;
/*
*
* Porpuse Constructor method  Appointmet 
*
 */  
public Appointment(Date theDate, int theHour,int theMinute, Patient thePatient, Boolean theStateActive ) throws Exception{
        this.setDate(theDate);
        this.setHour(theHour);
        this.setMinutes(theMinute);
        this.setPatient(thePatient);       
        this.stateActive = theStateActive;
    } 
    /*
     * Set the information about Patient 
     */
    public void setPatient(Patient thePatient) throws IllegalArgumentException {
        if(thePatient == null){
            throw new IllegalArgumentException("The patient can't be empty");
        }
        this.patient = thePatient;
    }
    /*
     * Set the information about Date of appointment
     */
    public void setDate(Date theDate)throws IllegalArgumentException {
        if(theDate == null ){
            throw new IllegalArgumentException("The date of appointment can't be empty");     
           }
        this.date = theDate;
    }
    /*
     * Set the information about of Hour
     */
    public void setHour(int theHour) throws IllegalArgumentException {
        if(theHour < 8 || theHour > 18 ) {
            throw new IllegalArgumentException("The hour must to between 8  to 18 ");     
        }
        this.hour = theHour;
    }

    public void setMinutes(int theMinutes) throws IllegalArgumentException{
        if(theMinutes == 0 || theMinutes == 30 ) {
                this.minutes = theMinutes;
            }else{
                throw new IllegalArgumentException("The appointment must to  0 minutes or 30 minutes");
            }
    }
    /*
     * Set the information  about Status
     */
    public void setStateActive(Boolean theStatus) throws IllegalArgumentException{
        if(theStatus == null){
            throw new IllegalArgumentException("The status of the appointment couldn't  be empty");
        }
        this.stateActive = theStatus;
    }


    /**
     * 
     * @return
     */
    public Date getDate() {
        return this.date;
    }
    /**
     * 
     * @return
     */
    public int getHour() {
        return this.hour;
    }
    /**
     * 
     * @return
     */
       public Patient getThePatient() {
        return this.patient;
    }
    /*
     * 
     */
    public Boolean getStateActive() {
        return this.stateActive;
 
    }
    /*
     * 
     */
    public int getMinutes() {
        return minutes;
    }
}