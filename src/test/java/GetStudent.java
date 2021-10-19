import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.restassured.RestAssured;
import io.restassured.config.EncoderConfig;
import io.restassured.response.Response;
import org.testng.annotations.Test;

public class GetStudent {
    @Test
    public void ttGetCall() throws JsonProcessingException {

        RestAssured.baseURI="http://qa.taltektc.com/api/";
        EncoderConfig encoderConfig = RestAssured.config().getEncoderConfig()
                .appendDefaultContentCharsetToContentTypeIfUndefined(false);
        RestAssured.config = RestAssured.config().encoderConfig(encoderConfig);

        Response response = RestAssured.given()
                .contentType("application/json")
                .get("student/TTCa2Rvl");
        System.out.println(response.getStatusCode());
        //System.out.println(response.getBody().asString());
        ObjectMapper map= new ObjectMapper();
        JsonNode body = map.readTree(response.getBody().asString());
        System.out.println(body.get("message"));
        System.out.println(body.get("data"));
        System.out.println(body.get("data").get("lastName"));
    }
}
