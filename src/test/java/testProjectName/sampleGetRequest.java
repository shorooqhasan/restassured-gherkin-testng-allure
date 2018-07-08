package testProjectName;

import utility.helpers;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.testng.annotations.*;

import java.io.IOException;
import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;
import static testProjectName.reusableMethods.*;

public class sampleGetRequest extends helpers {


    //
    @Test
    public void sampleGetRequest() throws IOException {

        RestAssured.baseURI=getProperty("HOST");

        log.info("GET - This request is to delete a created place");

        given().
                param("location","-33.8670522,151.1957362").
                param("radius","500").
                param("key",getProperty("KEY")).
        when().
                get(resources.getNearbyPlaces).
        then().
                log().body().
                assertThat().
                statusCode(200).and().
                contentType(ContentType.JSON).and().
                body("results[0].name",equalTo("Sydney")).and().
                body("results[0].place_id",equalTo("ChIJP3Sa8ziYEmsRUKgyFmh9AQM"));
    }

    // To retrieve the list of places nearby the provided coordinates
    @Test
    public void sampleGetRequest2() throws IOException {

        RestAssured.baseURI = getProperty("HOST");

        log.info("DELETE - This request is to delete a created place - Carry out validation on the response body");

        Response res = RestAssured.given().
                //log().all().
                param("location", "-33.8670522,151.1957362").
                param("radius", "500").
                param("key", getProperty("KEY")).
        when().
                get(resources.getNearbyPlaces).
        then().
                //log().body().
                assertThat().
                statusCode(200).and().
                contentType(ContentType.JSON).and().
                body("results[0].name", equalTo("Sydney")).and().
                body("results[0].place_id", equalTo("ChIJP3Sa8ziYEmsRUKgyFmh9AQM")).
        extract().response();

        JsonPath js = convertRawToJson(res);

        int resultSize = js.get("results.size()");
        log.info("Number of nearby places: "+resultSize);

        for (int i = 0; i < resultSize; i++) {
            String resultName = js.get("results[" + i + "].name");
            log.info(resultName);
        }
    }
}