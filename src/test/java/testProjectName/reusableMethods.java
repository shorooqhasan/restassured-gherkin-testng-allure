package testProjectName;

import utility.helpers;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import java.io.*;
import java.util.Properties;
import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;


public class reusableMethods extends helpers {

    protected static Properties prop;
    protected static String propValue;

    public static String getProperty(String propName) throws IOException {
        prop = new Properties();
        FileInputStream fis = new FileInputStream("src/main/java/Utility/testProjectName.properties");
        prop.load(fis);

        propValue = prop.getProperty(propName);
        log.info("Property - "+propName+" is : "+propValue);
        return  propValue;
    }

    public static Response getRequest(String location,String radius,String resources,String statusCode){
        log.info("GET request to retrieve nearby Places with location - "+location+" and radius - "+radius);

        RestAssured.baseURI = prop.getProperty("HOST");

        Response rawRes = given().
                param("location",location).
                param("radius",radius).
                param("key",prop.getProperty("key")).
        when().
                get(testProjectName.resources.getNearbyPlaces).
        then().
                log().headers().
                assertThat().statusCode(200).and().
                contentType(ContentType.JSON).and().
                body("results[0].name",equalTo("Sydney")).and().
                body("results[0].place_id",equalTo("ChIJP3Sa8ziYEmsRUKgyFmh9AQM")).
        extract().response();

        return rawRes;
    }

    public static Response postRequest(String latitude,String longitude){
        log.info("POST request to create Places with latitude - "+latitude+" and radius - "+longitude);

        RestAssured.baseURI=prop.getProperty("HOST");

        Response rawRes = given().log().params().
                queryParam("key",prop.getProperty("KEY")).
                body(testProjectName.payLoad.createValidPlaceViaPOST(latitude,longitude)).
        when().
                post(testProjectName.resources.addNewPlace).
        then().
                log().body().
                assertThat().statusCode(200).and().
                contentType(ContentType.JSON).and().
                body("status",equalTo("OK")).
        extract().response();

        return rawRes;
    }
}
