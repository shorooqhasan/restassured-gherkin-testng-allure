package utility;

import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.Reporter;
import ru.yandex.qatools.allure.annotations.Attachment;
import ru.yandex.qatools.allure.annotations.Step;

import static org.testng.Reporter.log;


public class helpers {

    public static final Logger log = LogManager.getLogger(helpers.class);

    @Step("{0}")
    public static void logToReport(String message) {
        log(message); //or System.out.println(message);
    }

    @Attachment( value = "response body :")
    public static String logResponse(String message) {
        return message;
    }


    @Step("Response Body : ")
    public static JsonPath convertRawToJson(Response rawRes){
        String responseString = rawRes.asString();

        // Response in String format has to be converted in to a Json Format
        JsonPath jsonResponse = new JsonPath(responseString);

        // returns the body in the form of Json path
        logResponse(responseString);
        return jsonResponse;
    }


}
