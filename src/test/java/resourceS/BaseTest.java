package resourceS;

import io.restassured.builder.RequestSpecBuilder;
import io.restassured.filter.log.RequestLoggingFilter;
import io.restassured.filter.log.ResponseLoggingFilter;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;
import org.testng.annotations.BeforeSuite;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.PrintStream;
import java.util.HashMap;
import java.util.Map;

public class BaseTest {

    private static Map<String, Object> testCache;
    private static RequestSpecification req;

    @BeforeSuite
    public void setCache() {
        testCache = new HashMap<>();
    }

    public static Map<String, Object> getTestCache() {

        return testCache;
    }

    public static RequestSpecification  getRequestSpecification() {

        if (req == null) {
            PrintStream log;
            try {
                log = new PrintStream(new FileOutputStream("logging.txt"));
                req = new RequestSpecBuilder().addFilter(RequestLoggingFilter.logRequestTo(log))
                        .addFilter(ResponseLoggingFilter.logResponseTo(log)).setContentType(ContentType.JSON).build();
            } catch (FileNotFoundException e) {

                e.printStackTrace();
            }

            return req;
        }
        return req;
    }






}
