package payloads;

import AddPlacePojoClasses.AddPlace;
import AddPlacePojoClasses.Location;
import java.util.ArrayList;
import java.util.List;

public class AllPayloads {
    static AddPlace addPlace;
    static Location location;
    static List<String> myList;
    public static AddPlace addPlacePayload(String name,String Address,String language,String phone_number,String website,String accuracy){
        addPlace=new AddPlace();
        location=new Location();
        location.setLat(23.907656);
        location.setLng(-45.67889);
        addPlace.setAccuracy(accuracy);
        addPlace.setName(name);
        addPlace.setPhone_number(phone_number);
        addPlace.setAddress(Address);
        addPlace.setWebsite(website);
        addPlace.setLanguage(language);
        myList=new ArrayList<>();
        myList.add("milk shop");
        myList.add("beeda shop");
        addPlace.setLocation(location);
        addPlace.setTypes(myList);

        return addPlace;
    }
}
