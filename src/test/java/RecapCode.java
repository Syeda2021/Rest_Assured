import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.restassured.RestAssured;
import io.restassured.config.EncoderConfig;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.Test;

public class RecapCode {
    String id;
    String btoken;
    String email;
    String password;

    @Test
    public void signupTTech() throws JsonProcessingException {

        Utility util= new Utility();
        RestAssured.baseURI="http://qa.taltektc.com/api/";
        EncoderConfig encoderConfig = RestAssured.config().getEncoderConfig()
                .appendDefaultContentCharsetToContentTypeIfUndefined(false);
        RestAssured.config = RestAssured.config().encoderConfig(encoderConfig);

        email=util.email;
        password=util.password;
        Response rest = RestAssured.given()
                .contentType("application/json")
                .body(util.signUpBody(email,password))
                .post("signup");

        System.out.println(rest.getStatusCode());
        Assert.assertEquals(rest.getStatusCode(),201);
        System.out.println(rest.getBody().asString());
        ObjectMapper map= new ObjectMapper();
        JsonNode js = map.readTree(rest.getBody().asString());
        System.out.println(js.get("success"));
        System.out.println(js.get("id"));
        id=js.get("id").toString().replaceAll("\"","");
    }

    // @Test(dependsOnMethods = "signupTTech")
    public void ttGetCall() throws JsonProcessingException {

        RestAssured.baseURI="http://qa.taltektc.com/api/";
        EncoderConfig encoderConfig = RestAssured.config().getEncoderConfig()
                .appendDefaultContentCharsetToContentTypeIfUndefined(false);
        RestAssured.config = RestAssured.config().encoderConfig(encoderConfig);

        Response response = RestAssured.given()
                .contentType("application/json")
                .get("student/"+id);
        System.out.println(response.getStatusCode());
        //System.out.println(response.getBody().asString());
        ObjectMapper map= new ObjectMapper();
        JsonNode body = map.readTree(response.getBody().asString());
        System.out.println(body.get("message"));
        System.out.println(body.get("data"));
        System.out.println(body.get("data").get("lastName"));
    }

    //@Test
    public void getAllStudentInfo() throws JsonProcessingException {

        RestAssured.baseURI="http://qa.taltektc.com/api/";
        EncoderConfig encoderConfig = RestAssured.config().getEncoderConfig()
                .appendDefaultContentCharsetToContentTypeIfUndefined(false);
        RestAssured.config = RestAssured.config().encoderConfig(encoderConfig);

        Response response = RestAssured.given()
                .contentType("application/json")
                .get("students");

        System.out.println(response.getStatusCode());
        System.out.println(response.getBody().asString());
        ObjectMapper map= new ObjectMapper();
        JsonNode body = map.readTree(response.getBody().asString());
        JsonNode data = body.get("data");
        for(int i=0;i<data.size();i++){
            String firstName = data.get(i).get("firstName").toString().replaceAll("\"","");
            if(firstName.equals("Iqbal")){
                System.out.println(data.get(i).get("email"));
                break;
            }
        }

    }


    @Test(dependsOnMethods = "signupTTech")
    public void LoginTTech() throws JsonProcessingException {

        Utility util = new Utility();

        RestAssured.baseURI="http://qa.taltektc.com/api/";
        EncoderConfig encoderConfig = RestAssured.config().getEncoderConfig()
                .appendDefaultContentCharsetToContentTypeIfUndefined(false);
        RestAssured.config = RestAssured.config().encoderConfig(encoderConfig);


        Response rest = RestAssured.given()
                .contentType("application/json")
                .body("{\n" +
                        "    \"email\" : \""+email+"\",\n" +
                        "    \"password\" : \""+password+"\"\n" +
                        "}")
                .post("login");

        System.out.println(rest.getStatusCode());
        Assert.assertEquals(rest.getStatusCode(),200);
        System.out.println(rest.getBody().asString());
        ObjectMapper map= new ObjectMapper();
        JsonNode js = map.readTree(rest.getBody().asString());
        System.out.println(js.get("token"));
        btoken=js.get("token").toString().replaceAll("\"","");

    }

   // @Test(dependsOnMethods = "LoginTTech")
    public void updateStudentInfo() throws JsonProcessingException {

        RestAssured.baseURI="http://qa.taltektc.com/api/";
        EncoderConfig encoderConfig = RestAssured.config().getEncoderConfig()
                .appendDefaultContentCharsetToContentTypeIfUndefined(false);
        RestAssured.config = RestAssured.config().encoderConfig(encoderConfig);

        Response rest = RestAssured.given()
                .contentType("application/json")
                .header("Authorization","Bearer"+ btoken)
                .body(
                        "    \"password\"  : \"1234577\",\n" +
                                "    \"confirmPassword\"  : \"1234577\"\n" +
                                "}")
                .patch("updatePassword/"+id);

        System.out.println(rest.getStatusCode());
        //Assert.assertEquals(rest.getStatusCode(),200);
        System.out.println(rest.getBody().asString());
        ObjectMapper map= new ObjectMapper();
        JsonNode js = map.readTree(rest.getBody().asString());

    }


    @Test(dependsOnMethods = "LoginTTech")
    public void changePassword() throws JsonProcessingException {

        RestAssured.baseURI="http://qa.taltektc.com/api/";
        EncoderConfig encoderConfig = RestAssured.config().getEncoderConfig()
                .appendDefaultContentCharsetToContentTypeIfUndefined(false);
        RestAssured.config = RestAssured.config().encoderConfig(encoderConfig);

        Response rest = RestAssured.given()
                .contentType("application/json")
                .header("Authorization","Bearer"+ btoken)
                .body(
                        "    \"password\"  : \"1234578\",\n" +
                                "    \"confirmPassword\"  : \"1234578\"\n" +
                                "}")
                .patch("updatePassword/"+id);

        System.out.println(rest.getStatusCode());
        //Assert.assertEquals(rest.getStatusCode(),200);
        System.out.println(rest.getBody().asString());
        ObjectMapper map= new ObjectMapper();
        JsonNode js = map.readTree(rest.getBody().asString());
        System.out.println(js);

    }



}
