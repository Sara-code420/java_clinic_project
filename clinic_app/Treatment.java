package clinic_app;
/* ******************************************

 * @author: DMS
 * @version: 1.0
 * @since: 2023-11-03
/*
 * Porpuse: Define the main data members and method assoicate to a treatment in the program. 
 *
 ******************************************** */
/*
 * Porpuse: Constructor of  Treatment Class
 */
public class Treatment{

     // Data Member      
 private String name;
 private Doctor doctor;
 private String therapy;
 

/*
 * 
 */
public Treatment(Doctor theDoctor,  String theTherapy, String theName){
    this.setDoctor(theDoctor);      
    this.setTherapy(theTherapy);
    this.setName(theName);
}
/*
 * @Param Object the Patient
 */
 public void setDoctor(Doctor theDoctor){
     if(theDoctor == null){
        throw new IllegalArgumentException("The Object Doctor can't be null ");
     }
     this.doctor = theDoctor;
 }
/*
 * @Param Object The Patient
 */
 public void setTherapy(String theTherapy ){
    if(theTherapy == null){
        throw new IllegalArgumentException("The Object Patient can't be null ");
     }
    this.therapy= theTherapy;
 }

/*
 * @Param set Name of therapy
 */
 public void setName(String theName) {
    if(theName.isEmpty()){
        throw new IllegalArgumentException("The name can't be empty ");
     }
   this.name = theName; 
 }

/*
 *  @Param get Information doctor
 */
 public Doctor getDoctor(){
     return this.doctor;
 }
/*
 * @Param get Information about Name
 */
 public String getName(){
     return this.name;
 }
 /*
  *  get Information about Therapy
  */
 public String getTherapy() {
     return therapy;
 }
}

