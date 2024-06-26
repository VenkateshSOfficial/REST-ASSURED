package stepDefinitions;

import AddPlacePojoClasses.GetPlaceResponse;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.testng.Assert;
import utils.Utilities;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static io.restassured.RestAssured.given;

public class GetPlaceAPI extends Utilities {
    RequestSpecification getPlaceRequest;
    Response getPlaceResponse;
    GetPlaceResponse getPlaceResp;
    List<String> myPlaceIdList;


    @Given("User should have the {string} value to fetch the place details")
    public void user_should_have_the_value_to_fetch_the_place_details(String place_id) {
        myPlaceIdList=Arrays.asList(AddPlaceAPI.placeId);
        System.out.println(myPlaceIdList);
        /*for(int i=0;i<myPlaceIdList.size();i++){
            getPlaceRequest = given().spec(requestSpecification()).queryParam(place_id, myPlaceIdList.get(i));
            System.out.println(getPlaceRequest.toString());
        }*/

    }
    @When("User will send HTTP GET request along with the place id to fetch the place details")
    public void user_will_send_http_get_request_along_with_the_place_id_to_fetch_the_place_details() {
        for(int i=0;i<myPlaceIdList.size();i++){
            try {
                getPlaceResponse = getPlaceRequest.when().get(fetchDataFromGlobalProperties("getPlaceResource"));
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
    }
    @Then("Validate if the status code is {int}")
    public void validate_if_the_status_code_is(Integer status_code) {
        for(int i=0;i<myPlaceIdList.size();i++){
            getPlaceResp = getPlaceResponse.then().spec(responseSpecification(200)).extract().response().as(GetPlaceResponse.class);
            System.out.println(getPlaceResp.getName());
        }
    }
}
