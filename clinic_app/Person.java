package clinic_app;
import java.util.Date;
// Person.java
// Abstract class.

/* ******************************************

 * @author: DMS
 * @version: 1.0
 * @since: 2023-11-03
/*
 * Porpuse: Define the main data members and method assoicate to a person  in the program. 
 *
 ******************************************** */
/*
 * Porpuse: Constructor of  the super Person Class
 */
public abstract  class Person{
    //Data Member of super class
    private String name; 
    private String address;
    private Date birthDate;
    private String phoneContact;

    /*
     * Porpuse Create the object of a person  with main information requerided 
     */
    public Person(String sName, String sAddress, Date dBirthdate, String sPhoneContact) throws IllegalArgumentException{

        this.setName(sName);
        this.setAddress(sAddress);
        this.setBirthDate(dBirthdate);
        this.setPhoneContact(sPhoneContact);
    }
    /*
     * Porpuse set name of a person
     */
    public void setName(String sName) throws IllegalArgumentException{
        if(sName == null || sName.isEmpty()){
            throw new IllegalArgumentException("The name can't be empty");
        }
        this.name = sName;
    }
    /*
     * Porpuse set Address od a Person
     */
    public void setAddress(String sAddress) throws IllegalArgumentException{
        if(sAddress == null || sAddress.isEmpty()){
            throw new IllegalArgumentException("The address can't be empty");
        }
    
        this.address = sAddress;
    }
    /*
     *  Set Phone contact  Of Person
     */
    public void setPhoneContact(String sPhoneContact) throws IllegalArgumentException{
        if(sPhoneContact == null || sPhoneContact.isEmpty()){
            throw new IllegalArgumentException("The sPhoneContact can't be empty");
        }
        this.phoneContact = sPhoneContact;
    }
    /**
     * 
     * @param dBirthDate Date of birth
     */
    public void setBirthDate(Date dBirthDate) throws IllegalArgumentException{
        if(dBirthDate == null){
            throw new IllegalArgumentException("The date can't be empty");
        }
        this.birthDate = dBirthDate;
    }
    /*
     * Get Name Of Person
     */
    public  String getName(){
        return this.name;
    }
    /*
     *  Get Phone Contact Of Person
     */
    public String getPhoneContact(){
        return this.phoneContact;
    }
    /*
     *  Get Birth Date Of Person
     */
    public Date getBirthDate(){
        return this.birthDate; 
    }
    /*
     * Get Address Of Person
     */
    public String getAddress() {
        return this.address;
    }
    
}
