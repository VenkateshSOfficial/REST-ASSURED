package stepDefinitions;

import io.cucumber.java.Before;
import stepDefinitions.AddPlaceAPI;

public class Hooks {
    AddPlaceAPI addPlace;
    @Before
    public void fetchPlaceID(){
        addPlace=new AddPlaceAPI();
      if(AddPlaceAPI.placeId==null){
          addPlace.user_should_have_a_predefined_payload("A-house",	"1st main road",	"91-1234567",	"English-EN","www.1st.com	23.567","23.567").
                  user_will_send_a_post_http_request_with_the_predefined_payload_to_create_a_new_place_in_the_server_using_add_place_api().
                  validate_the_correct_status_code_as_for_adding_place_in_the_api_call(200).store_the_place_id();
      }
    }
}
