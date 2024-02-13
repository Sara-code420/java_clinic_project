package clinic_app;

import java.util.List;

/**
 * @author Sara
 * @since 2023-03-11
 * @version 1.0
 * @return this interface is created to used common method for creating 
 * and cancelling events or other utilities
 */

public interface Utility{

    public void create(); // To create 
    public void cancel();
    public Person findPerson(List listPerson, String idPerson);

}


