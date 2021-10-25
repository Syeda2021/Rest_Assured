import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.restassured.RestAssured;
import io.restassured.config.EncoderConfig;
import io.restassured.response.Response;
import org.testng.annotations.Test;

public class LogIn {
    String tkn;
    @Test
    public void logInPost() throws JsonProcessingException {

        RestAssured.baseURI = "http://qa.taltektc.com/api/";

        EncoderConfig encoderConfig = RestAssured.config().getEncoderConfig()
                .appendDefaultContentCharsetToContentTypeIfUndefined(false);
        RestAssured.config = RestAssured.config().encoderConfig(encoderConfig);

        Response res = RestAssured.given()
                .contentType("application/json")
                .body("{\n" +
                        "    \"email\" : \"jhon.doe102@gmail.com\",\n" +
                        "    \"password\" : \"123456\"\n" +
                        "}")
                .post("login");
        System.out.println(res.getStatusCode());
        System.out.println(res.getBody().asString());

        ObjectMapper map= new ObjectMapper();
        JsonNode js = map.readTree(res.getBody().asString());

        System.out.println(js.get("success"));
        System.out.println(js.get("message"));

        System.out.println(js.get("token"));
       // tkn = js.get("token");

    }
}