package testProjectName;

import utility.helpers;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;

import io.restassured.response.Response;
import org.testng.annotations.Test;

import java.io.IOException;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.contains;
import static org.hamcrest.Matchers.equalTo;

import static testProjectName.reusableMethods.getProperty;

public class samplePostRequest extends helpers {


    @Test
    public void samplePostRequest() throws IOException {

        RestAssured.baseURI=getProperty("HOST");

        log.info("POST - This request is to create a place");

        String latitude = "-33.8669710";
        String longitude = "151.1958750";

        given().
                log().body().
                queryParam("key",getProperty("KEY")).
                body(payLoad.createValidPlaceViaPOST(latitude,longitude)).
        when().
                post(resources.addNewPlace).
        then().
                log().body().
                assertThat().statusCode(200).and().
                contentType(ContentType.JSON).and().
                body("status",equalTo("OK"));
    }

    @Test
    public void testClaimCreation() throws IOException {
        RestAssured.baseURI=getProperty("HOST_ACC");
        log.info("POST - This request is to create a claim");
        Response rawRes =given().
                auth().basic("acc_user","fea638af-7ead-4a37-8373-0ae17484af9f").
                header("X-Parse-Application-Id","").
                header("X-Parse-REST-API-Key","").
                log().body().
                body(payLoad.createClaim(2)).
                contentType(ContentType.JSON).and().
        when().
                post(resources.createClaim).
        then().
                log().body().
                assertThat().statusCode(201).and().
                contentType(ContentType.JSON).and().
                //body("reason",contains("Claim created"));
        extract().response();

        String claimId = convertRawToJson(rawRes).get("creationData.id");
        log.info(claimId);
    }

}
