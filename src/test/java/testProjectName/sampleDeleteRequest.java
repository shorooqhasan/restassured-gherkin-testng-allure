package testProjectName;

import utility.helpers;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.testng.annotations.Test;
import java.io.IOException;
import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;
import static testProjectName.reusableMethods.*;

public class sampleDeleteRequest extends helpers {

    @Test
    public void sampleDeleteRequest1() throws IOException {

        RestAssured.baseURI= getProperty("HOST");

        String latitude = "-33.8669710";
        String longitude = "151.1958750";

        log.info("DELETE - This request is to delete a created place");

        //############ POST request to create a new place - Can be moved to ReUsable methods class, if required ###############
        Response rawRes =  given().
                queryParam("key",getProperty("KEY")).
                body(testProjectName.payLoad.createValidPlaceViaPOST(latitude,longitude)).
        when().
                post(testProjectName.resources.addNewPlace).
        then().
                log().body().
                assertThat().statusCode(200).and().contentType(ContentType.JSON).and().
                body("status",equalTo("OK")).
        extract().response();

        //#####################################################################################################################

/*
        // response is in Raw format and has to be converted into a String
       String responseString = rawRes.asString();

       // Response in String format has to be converted in to a Json Format
        JsonPath jsonResponse = new JsonPath(responseString);
*/
        // reUue method 'convertRawToJson(rawRes)' from reusableMethods class
        String placeId = convertRawToJson(rawRes).get("place_id");

        given().
                log().body().
                queryParam("key",getProperty("KEY")).
                body(testProjectName.payLoad.deletePlace(placeId)).
         when().
                post(testProjectName.resources.deletePlace).
         then().
                assertThat().statusCode(200).and().
                body("status",equalTo("OK"));
    }

    @Test
    public void sampleDeleteRequest2() throws IOException {

        String latitude = "-33.8669710";
        String longitude = "151.1958750";

        log.info("DELETE - This request is to delete a created place - ReUse method from reusableMethods class");

        Response rawRes = postRequest(latitude,longitude);

        String placeId = convertRawToJson(rawRes).get("place_id");

        given().
                log().body().
                queryParam("key",getProperty("KEY")).
                body(testProjectName.payLoad.deletePlace(placeId)).
        when().
                post(testProjectName.resources.deletePlace).
        then().
                assertThat().statusCode(200).and().
                body("status",equalTo("OK"));
    }
}
