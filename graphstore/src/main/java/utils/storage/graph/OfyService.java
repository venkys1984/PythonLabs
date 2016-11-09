package utils.storage.graph;

import com.googlecode.objectify.Objectify;
import com.googlecode.objectify.ObjectifyService;


public class OfyService{

    //register entity class
    static{
        ObjectifyService.factory().register(Node.class);
    }

    public static Objectify ofy(){
        return ObjectifyService.ofy();
    }
}