package utils;

import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.filter.log.RequestLoggingFilter;
import io.restassured.filter.log.ResponseLoggingFilter;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;

import java.io.*;
import java.util.Properties;

public class Utilities {
    Properties properties;
    FileInputStream fis;
    String property;
    public static RequestSpecification requestPreRequisite;
    PrintStream logs;
    ResponseSpecification responseSpec;
    JsonPath js;
    Object value;

    public RequestSpecification requestSpecification() {
        /* creating a file to add all the logs */
       if(requestPreRequisite==null){
           try {
               logs = new PrintStream(new FileOutputStream("logging.txt"));
               requestPreRequisite = new RequestSpecBuilder()
                       .setBaseUri(fetchDataFromGlobalProperties("baseURI"))
                       .addQueryParam("key", "qaclick123")
                       /* adding logs using RequestLoggingFilter for request and response logging */
                       .addFilter(RequestLoggingFilter.logRequestTo(logs))
                       .addFilter(ResponseLoggingFilter.logResponseTo(logs))
                       .setContentType(ContentType.JSON)
                       .build();
               return requestPreRequisite;
           } catch (FileNotFoundException e) {
               throw new RuntimeException(e);
           } catch (IOException e) {
               throw new RuntimeException(e);
           }
       }
       return requestPreRequisite;
    }

    public ResponseSpecification responseSpecification(int status_code){
        responseSpec = new ResponseSpecBuilder().expectContentType(ContentType.JSON).expectStatusCode(status_code).build();
        return responseSpec;
    }
    public String fetchDataFromGlobalProperties(String val) throws IOException {
        properties = new Properties();
        fis = new FileInputStream(System.getProperty("user.dir") + "\\src\\test\\java\\utils\\global.properties");
        properties.load(fis);
        property = properties.getProperty(val);
        return property;
    }
    public Object rawToJson(String response, String val){
        js=new JsonPath(response);
        value = js.get(val);
        return value;
    }
}
