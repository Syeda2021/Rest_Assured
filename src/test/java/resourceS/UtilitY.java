package resourceS;

import com.fasterxml.jackson.databind.ObjectMapper;
import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.config.EncoderConfig;
import io.restassured.http.ContentType;
import io.restassured.http.Method;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class UtilitY {

    static ObjectMapper mapper = new ObjectMapper();
    static RequestSpecBuilder builder = new RequestSpecBuilder();
    static ResponseSpecBuilder response = new ResponseSpecBuilder();
    static RequestSpecification spec;


    public static void encodeConfig(){

        EncoderConfig encoderConfig = RestAssured.config().getEncoderConfig()
                .appendDefaultContentCharsetToContentTypeIfUndefined(false);
        RestAssured.config = RestAssured.config().encoderConfig(encoderConfig);
    }



    public static Response callPostEndpoint(Method httpMethod, Object body, String baseurl, String endpoint) {

        if (httpMethod != Method.POST){
            throw new IllegalArgumentException("Wrong http method requested ");
        }

        try {
            spec = RestAssured.given().spec(BaseTest.getRequestSpecification());
            Response post = spec.header("Content-Type", "application/json").accept(ContentType.JSON)
                    .baseUri(baseurl).body(body).post(endpoint);
            return post;
        }catch(Exception exception){
            throw new RuntimeException("Could not call endpoint successfully");
        }
    }


    public static Response callGetEndPoint(Method httpMethod,String baseurl,String id, String endpoint) {

        if (httpMethod!= Method.GET) {
            throw new IllegalArgumentException("Wrong http method requested ");
        }
        try {
            spec = RestAssured.given().spec(BaseTest.getRequestSpecification());
            Response get = spec.header("Content-Type", "application/json").accept(ContentType.JSON)
                    .baseUri(baseurl).formParam("id", id).get(endpoint);
            return get;
        } catch (Exception exception) {
            throw new RuntimeException("could not call endpoint successfully");
        }
    }


    public static Response callGetEndPointTTech(Method httpMethod,String baseurl, String endpoint) {

        if (httpMethod!=Method.GET) {
            throw new IllegalArgumentException("Wrong http method requested ");
        }
        try {
            spec = RestAssured.given().spec(BaseTest.getRequestSpecification());
            Response get = spec.header("Content-Type", "application/json").accept(ContentType.JSON)
                    .baseUri(baseurl).get(endpoint);
            return get;
        } catch (Exception exception) {
            throw new RuntimeException("could not call endpoint successfully");
        }
    }


    public static Response callDeleteEndPoint(Method httpMethod, String baseUrl,String id, String endpoint) {

        if (httpMethod!=Method.DELETE){
            throw new IllegalArgumentException("Wrong http method requested ");
        }
        try{
            spec = RestAssured.given().spec(BaseTest.getRequestSpecification());
            Response delete=spec.header("Content-Type","application/json").accept(ContentType.JSON)
                    .baseUri(baseUrl).queryParam("id",id).delete(endpoint);
            return delete;
        }catch(Exception exception){
            throw new RuntimeException("could not call endpoint successfully");
        }

    }


    public static Response callPutEndpoint(Method httpMethod,String token, Object body, String baseurl, String endpoint) {

        if (httpMethod != Method.PUT){
            throw new IllegalArgumentException("Wrong http method requested ");
        }

        try {
            spec = RestAssured.given().spec(BaseTest.getRequestSpecification());
            Response put = spec.header("Content-Type", "application/json").accept(ContentType.JSON)
                    .header("Authorization", "Bearer "+token)
                    .baseUri(baseurl).body(body).put(endpoint);
            return put;
        }catch(Exception exception){
            throw new RuntimeException("Could not call endpoint successfully");
        }
    }


    public static Response callPatchEndpoint(Method httpMethod,String token, Object body, String baseurl, String endpoint) {

        if (httpMethod != Method.PATCH){
            throw new IllegalArgumentException("Wrong http method requested ");
        }

        try {
            spec = RestAssured.given().spec(BaseTest.getRequestSpecification());
            Response patch = spec.header("Content-Type", "application/json").accept(ContentType.JSON)
                    .header("Authorization", "Bearer "+token)
                    .baseUri(baseurl).body(body).patch(endpoint);
            return patch;
        }catch(Exception exception){
            throw new RuntimeException("Could not call endpoint successfully");
        }
    }

    public static Response callDeleteEndPointTTech(Method httpMethod,String token, String baseUrl, String endpoint) {

        if (httpMethod!=Method.DELETE){
            throw new IllegalArgumentException("Wrong http method requested ");
        }
        try{
            spec = RestAssured.given().spec(BaseTest.getRequestSpecification());
            Response delete=spec.header("Content-Type","application/json").accept(ContentType.JSON)
                    .header("Authorization", "Bearer "+token)
                    .baseUri(baseUrl).delete(endpoint);
            return delete;
        }catch(Exception exception){
            throw new RuntimeException("could not call endpoint successfully");
        }

    }


}
