import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.restassured.RestAssured;
import io.restassured.config.EncoderConfig;
import io.restassured.response.Response;

import org.testng.annotations.Test;

public class HWrandomID {

    String email;
    String password;
    String n ;
    String tkn;

     @Test
    public void hwID() throws JsonProcessingException {
        Utility util = new Utility();
        RestAssured.baseURI = "http://qa.taltektc.com/api/";

        EncoderConfig encoderConfig = RestAssured.config().getEncoderConfig()
                .appendDefaultContentCharsetToContentTypeIfUndefined(false);
        RestAssured.config = RestAssured.config().encoderConfig(encoderConfig);

        email = util.email;
        password = util.password;


        Response rest = RestAssured.given()
                .contentType("application/json")
                .body(util.signUpBody(email, password))
                .post("signup");

        System.out.println(rest.getStatusCode());
        System.out.println(rest.getBody().asString());
        ObjectMapper map = new ObjectMapper();
        JsonNode js = map.readTree(rest.getBody().asString());
        System.out.println(js.get("success"));
        n = js.get("id").toString().replaceAll("\"","");


    }

    @Test (dependsOnMethods = "hwID")
    public void getStudent() throws JsonProcessingException {


        RestAssured.baseURI = "http://qa.taltektc.com/api/";
        EncoderConfig encoderConfig = RestAssured.config().getEncoderConfig()
                .appendDefaultContentCharsetToContentTypeIfUndefined(false);
        RestAssured.config = RestAssured.config().encoderConfig(encoderConfig);

        Response response = RestAssured.given()
                .contentType("application/json")
                .get("student/"+ n);

        System.out.println(response.getStatusCode());
        //System.out.println(response.getBody().asString());
        ObjectMapper map = new ObjectMapper();
        JsonNode body = map.readTree(response.getBody().asString());
        System.out.println(body.get("data"));

    }
    @Test
    public void logInPost() throws JsonProcessingException {

        RestAssured.baseURI = "http://qa.taltektc.com/api/";

        EncoderConfig encoderConfig = RestAssured.config().getEncoderConfig()
                .appendDefaultContentCharsetToContentTypeIfUndefined(false);
        RestAssured.config = RestAssured.config().encoderConfig(encoderConfig);

        Response res = RestAssured.given()
                .contentType("application/json")
                .body("{\n" +
                        "    \"email\" : \""+email+"\",\n" +
                        "    \"password\" : \""+password+"\"\n" +
                        "}")
                .post("login");
        System.out.println(res.getStatusCode());
        System.out.println(res.getBody().asString());

        ObjectMapper map= new ObjectMapper();
        JsonNode js = map.readTree(res.getBody().asString());

        System.out.println(js.get("success"));
        System.out.println(js.get("message"));
        System.out.println(js.get("token"));

        tkn = js.get("token").toString().replaceAll("\"","");

    }

   // @Test (dependsOnMethods = "logInPost")
    public void putUpdate() throws JsonProcessingException {


        RestAssured.baseURI = "http://qa.taltektc.com/api/";
        EncoderConfig encoderConfig = RestAssured.config().getEncoderConfig()
                .appendDefaultContentCharsetToContentTypeIfUndefined(false);
        RestAssured.config = RestAssured.config().encoderConfig(encoderConfig);

        Response response = RestAssured.given()
                .contentType("application/json")
                .header("Authorization", "Bearer" + tkn)
                .body("{\n" +
                        "    \"firstName\" : \"Jhon\",\n" +
                        "    \"lastName\" : \"Doe\",\n" +
                        "    \"email\"     : \"jhon.doe27@gmail.com\",\n" +
                        "    \"dob\"       : {\n" +
                        "        \"year\"      : 2013,\n" +
                        "        \"month\"     : 11,\n" +
                        "        \"day\"       : 31\n" +
                        "    },\n" +
                        "    \"gender\"    : \"female\",\n" +
                        "    \"agree\"     : true\n" +
                        "}\n")
                .put("student/"+ n);
//         .body(
//                "    \"password\"  : \"1234577\",\n" +
//                        "    \"confirmPassword\"  : \"1234577\"\n" +
//                        "}")
//                .patch("updatePassword/"+id);
        //if you want to update PARTIAL UPDATE- U HAVE TO USE "patch" instead of "put"



        System.out.println(response.getStatusCode());
        //System.out.println(response.getBody().asString());
        ObjectMapper map = new ObjectMapper();
        JsonNode body = map.readTree(response.getBody().asString());
        System.out.println(body.get("data"));

    }

    @Test (dependsOnMethods = "logInPost")
    public void changePasswordUpdate() throws JsonProcessingException {


        RestAssured.baseURI = "http://qa.taltektc.com/api/";
        EncoderConfig encoderConfig = RestAssured.config().getEncoderConfig()
                .appendDefaultContentCharsetToContentTypeIfUndefined(false);
        RestAssured.config = RestAssured.config().encoderConfig(encoderConfig);

        Response response = RestAssured.given()
                .contentType("application/json")
                .header("Authorization", "Bearer" + tkn)
                .body("{\n" +
                        "    \"firstName\" : \"Jhon\",\n" +
                        "    \"lastName\" : \"Doe\",\n" +
                        "    \"email\"     : \"jhon.doe27@gmail.com\",\n" +
                        "    \"dob\"       : {\n" +
                        "        \"year\"      : 2013,\n" +
                        "        \"month\"     : 11,\n" +
                        "        \"day\"       : 31\n" +
                        "    },\n" +
                        "    \"gender\"    : \"female\",\n" +
                        "    \"agree\"     : true\n" +
                        "}\n")
                .put("student/"+ n);

        System.out.println(response.getStatusCode());
        //System.out.println(response.getBody().asString());
        ObjectMapper map = new ObjectMapper();
        JsonNode body = map.readTree(response.getBody().asString());
        System.out.println(body.get("data"));

    }



}