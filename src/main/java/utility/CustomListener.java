package utility;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.ITestContext;
import org.testng.ITestResult;
import org.testng.TestListenerAdapter;
import ru.yandex.qatools.allure.annotations.Attachment;
import ru.yandex.qatools.allure.annotations.Step;

import java.util.Properties;

public class CustomListener extends TestListenerAdapter {

    private static final Logger log = LogManager.getLogger(CustomListener.class);

    @Override
    public void onStart(ITestContext arg0) {
        log.info("Test suite: {}", arg0.getName());
    }

    @Override
    public void onTestStart(ITestResult arg0) {
        log.info("Starting test {}", arg0.getMethod());
    }

    @Override
    public void onTestSuccess(ITestResult tr) {
        log.info("{} --- SUCCESS ---\n", tr.getName());
//        analyzeLog(test)
    }

    @Step("Step onFailure screenshot attach")
    @Override
    public void onTestFailure(ITestResult tr) {
//        String test = tr.getMethod();
//        analyzeLog(test)
        log.error("{} --- FAILED --- ", tr.getName());
        Throwable ex = tr.getThrowable();
        if (ex != null) {
            String cause = ex.toString();
            log.error(cause + "\n");
        }
    }

    @Override
    public void onTestSkipped(ITestResult tr) {
//        String test = tr.getMethod();
//        analyzeLog(test)
        log.info("{} --- SKIPPED ---\n", tr.getName());
        Throwable ex = tr.getThrowable();
        if (ex != null) {
            String cause = ex.toString();
            log.error("{}\n", cause);
        }
    }


    @Attachment(value = "System Environment", type = "text/plain")
    public byte[] attachSystemProperties() {
        Properties props = System.getProperties();
        StringBuilder result = new StringBuilder();
        for (String prop : props.stringPropertyNames()) {
            result.append(prop)
                    .append(" = ")
                    .append(System.getProperty(prop))
                    .append("\n");
        }
        return result.toString().getBytes();
    }

//    @Step("Adding collected logs")
//    def analyzeLog(String methodName) {
//        if (WebDriverHolder.getDriver() != null) {
//            LogEntries logEntries = WebDriverHolder.driver.manage().logs().get(LogType.BROWSER)
//            for (LogEntry entry in logEntries) {
//                log.warn("Following logs were found during execution test {} {}", methodName, entry.getMessage())
//                saveTextLog("Error Logs", entry.getMessage())
//            }
//        } else {
//            log.info("Drives is 'null' cant perform analyze logs")
//        }
//    }

//    @Attachment(value = "{0}", type = "text/plain")
//    public static String saveTextLog(String attachName, String message) {
//        return message;
//    }

}
