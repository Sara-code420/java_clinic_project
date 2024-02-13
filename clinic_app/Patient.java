package clinic_app;

import java.util.Date;



public class Patient extends Person{

    private String Patient_ID;
    private String Insurance_Provider;
    private Treatment treatment;

    /*
     * 
     */
    public Patient(String sName, String sAddress, Date dBirthdate, String sPhoneContact, String sInsurance_Provider, 
    String sPatient_ID){
     //       throws IllegalArgumentException {
        super(sName, sAddress, dBirthdate, sPhoneContact);
        this.setPatient_ID(sPatient_ID);
        this.setInsurance_Provider(sInsurance_Provider);
    }
    /*
     * 
     */
    public void setPatient_ID(String thePatient_ID) throws IllegalArgumentException{
        
        /*if(thePatient_ID.isEmpty() || thePatient_ID == null ){
            throw new IllegalArgumentException("ID has to be a valid number.");
        }
        */
        this.Patient_ID = thePatient_ID;
    }
    /*
     * 
     */
    public void setInsurance_Provider(String theInsurance_Provider){
        if(theInsurance_Provider == null || theInsurance_Provider.isEmpty()){
            this.Insurance_Provider = "Flower Insurance";
        }else this.Insurance_Provider = theInsurance_Provider;
    }

    /*
     * 
     */
    public void setTreatment(Treatment theTreatment ){
       if(theTreatment == null ){
        throw new IllegalArgumentException("The treatment couldn't be empty ");         
       }
    }
    /*
     * 
     */
    public String getPatient_ID(){
        return this.Patient_ID;
    }
    /*
     * 
     */
    public String getInsurance_Provider(){
        return this.Insurance_Provider;
    }


}
