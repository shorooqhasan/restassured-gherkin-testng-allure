package acc_timeOut;

import ru.yandex.qatools.allure.annotations.Features;
import ru.yandex.qatools.allure.annotations.Step;
import utility.helpers;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

import static io.restassured.RestAssured.given;
import static org.hamcrest.core.StringContains.containsString;

@Features("ACC")

public class reusableMethods extends helpers {

    protected static Properties prop;
    protected static String propValue;


    public static String getProperty(String propName) throws IOException {
        prop = new Properties();
        FileInputStream fis = new FileInputStream("src/main/java/acc_timeOut/acc_timeOut.properties");
        prop.load(fis);

        propValue = prop.getProperty(propName);
        log.info("Property - " + propName + " is : " + propValue);
        return propValue;
    }


    //description = "Create claim and return claimID"

    @Step("Claim creation")
    public String createAndGetClaimId(int assetLimit) throws IOException {
        RestAssured.baseURI = getProperty("HOST");
        logToReport("Base URI : "+RestAssured.baseURI);

        logToReport("Path : "+acc_timeOut.resources.createClaim);

        Response rawRes = given().
                auth().basic(getProperty("USERNAME"), getProperty("PASSWORD")).
                header("X-Parse-Application-Id", "").
                header("X-Parse-REST-API-Key", "").
                body(acc_timeOut.payLoad.createClaim(assetLimit)).
                contentType(ContentType.JSON).and().
        when().
                post(acc_timeOut.resources.createClaim).
        then().
                //log().all().
                assertThat().statusCode(201).and().
                contentType(ContentType.JSON).and().
                body("reason",containsString("Claim created")).
        extract().response();

        String claimId = convertRawToJson(rawRes).get("creationData.id");
        logToReport("Claim created and claim Id is "+claimId);
        return claimId;
    }

    // Create Claim, attach asset and return
    @Step("Attach Asset to the claim created")
    public int assetAttachment(String claimId, String assetId) throws IOException {
        RestAssured.baseURI = getProperty("HOST");
        String resourcePath = acc_timeOut.resources.createAsset+claimId+"/assets";

        logToReport("Base URI : "+RestAssured.baseURI);

        logToReport("Path : "+resourcePath);

        //String dataFinal = prop.getProperty("DATA").trim();

        Response rawRes = given().
                log().params().
                log().body().
                auth().basic(getProperty("USERNAME"), getProperty("PASSWORD")).
                header("X-Parse-Application-Id", "").
                header("X-Parse-REST-API-Key", "").
                body(acc_timeOut.payLoad.createAsset(assetId,prop.getProperty("DATA"))).
                contentType(ContentType.JSON).and().
        when().
                post(acc_timeOut.resources.createAsset+claimId+"/assets").
        then().
                //log().body().
                //assertThat().statusCode(201).and().
                contentType(ContentType.JSON).and().
        extract().response();

        int responseCode = convertRawToJson(rawRes).get("responseCode");
        logToReport("response code received for Asset attachment request is "+responseCode);

        return responseCode;
    }

}
