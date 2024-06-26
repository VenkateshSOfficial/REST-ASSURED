package stepDefinitions;

import AddPlacePojoClasses.AddPlaceResponse;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.testng.Assert;
import payloads.AllPayloads;
import utils.Utilities;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Arrays;

import static io.restassured.RestAssured.given;


public class AddPlaceAPI extends Utilities {

    String response;
    static String[] placeId;
    RequestSpecification request;
    Response addPlaceResponse;
    AddPlaceResponse placeResponse;

    @Given("User should have a predefined payload {string} {string} {string} {string} {string} {string}")
    public AddPlaceAPI user_should_have_a_predefined_payload(String name, String address, String phone_number, String language, String website, String accuracy) {
        request = given().spec(requestSpecification())
                                   .body(AllPayloads.addPlacePayload(name, address, phone_number, language, website, accuracy));
        return this;
    }
    @When("User will send a POST HTTP request with the predefined payload to create a new place in the server using AddPlaceAPI")
    public AddPlaceAPI user_will_send_a_post_http_request_with_the_predefined_payload_to_create_a_new_place_in_the_server_using_add_place_api() {
        try {
            addPlaceResponse = request.when().post(fetchDataFromGlobalProperties("addPlaceResource"));
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return this;
    }
    @Then("validate the correct status code as {int} for adding place in the api call")
    public AddPlaceAPI validate_the_correct_status_code_as_for_adding_place_in_the_api_call(Integer int1) {
        addPlaceResponse.then().spec(responseSpecification(200));
        return this;
    }
    @Then("Validate if {string} is {string}")
    public AddPlaceAPI validate_if_is(String key, String val) {
        response = addPlaceResponse.asPrettyString();
        Assert.assertEquals(rawToJson(response,key).toString(),val);
        return this;
    }
    @Then("store the place_id")
    public AddPlaceAPI store_the_place_id() {
        placeResponse = addPlaceResponse.as(AddPlaceResponse.class);
        placeId = new String[]{placeResponse.getPlace_id()};
        return this;
    }
}
