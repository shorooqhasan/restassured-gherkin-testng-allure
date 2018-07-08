package acc_timeOut;

import groovy.util.logging.Log4j2;
import jdk.nashorn.internal.runtime.logging.Logger;
import org.testng.annotations.Test;

import ru.yandex.qatools.allure.annotations.*;
import utility.helpers;

import java.io.IOException;

import static acc_timeOut.reusableMethods.*;
import static org.testng.Assert.assertEquals;

@Features("ACC")

@Stories("ACC test for Claims & Assets")

public class sampleAccTests extends reusableMethods {

    @Test(description = "Claim creation test")
    @Description("Create Claim")
    public void testClaimCreation() throws IOException {
     createAndGetClaimId(1);
    }

    @Test(description = "Add allowed asset")
    @Description("Add asset to claim")
    public void permittedAssetAttachment() throws IOException{
        String claimId = createAndGetClaimId(1);
        int responseCode = assetAttachment(claimId,"test1");
        assertEquals(810, responseCode, "Failure message");
    }

    @Test(description = "Add more than the permitted asset")
    @Description("Add more than the permitted asset to claim")
    public void overloadedAssetAttachment() throws IOException{
        String claimId = createAndGetClaimId(1);
        assetAttachment(claimId,"test1");
        int responseCode = assetAttachment(claimId,"test2");
        assertEquals(810, responseCode, "Failure message");
    }

    @Test(description = "Add asset after time out")
    @Description("Add asset after time out to the claim")
    public void timedOutAssetAttachment() throws Exception {
        log.info("Test Case Name : timedOutAssetAttachment");
        String claimId = createAndGetClaimId(1);
        Thread.sleep(3000);
        assetAttachment(claimId,"test1");
        int responseCode = assetAttachment(claimId,"test2");
        assertEquals(0, responseCode, "Failure message");

    }


}
