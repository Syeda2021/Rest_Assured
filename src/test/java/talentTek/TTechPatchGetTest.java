package talentTek;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.restassured.http.Method;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.Test;
import resourceS.BaseTest;
import resourceS.CommonData;
import resourceS.UtilitY;


public class TTechPatchGetTest extends BaseTest {

    ObjectMapper mapper = new ObjectMapper();


    @Test
    public void patchTTechTest() throws JsonProcessingException {

        CommonData cdata=new CommonData();
        UtilitY.encodeConfig();
        Response response = UtilitY.callPatchEndpoint(Method.PATCH, getTestCache().get("accessToken").toString(),cdata.passwordPatchBody(cdata.updatePassword),cdata.baseURLTTech,cdata.endPointStudentUpdatePassword+ getTestCache().get("studentId").toString());

        System.out.println(response.getStatusCode());
        System.out.println(response.getBody().asString());
        getTestCache().put("updatedPassword",cdata.updatePassword);
        JsonNode obj = mapper.readTree(response.getBody().asString());
        Assert.assertEquals(obj.get("message").toString().replaceAll("\"",""),"Password update success");


    }


    @Test(dependsOnMethods = "patchTTechTest")
    public void studentLoginWithUpdatedPassword() throws JsonProcessingException {

        CommonData cdata=new CommonData();
        UtilitY.encodeConfig();
        Response response =UtilitY.callPostEndpoint(Method.POST,cdata.loginBody(getTestCache().get("email").toString(), getTestCache().get("updatedPassword").toString()),cdata.baseURLTTech,cdata.endPointStudentLogin);
        System.out.println(response.getStatusCode());
        System.out.println(response.getBody().asString());
        JsonNode obj = mapper.readTree(response.getBody().asString());
        Assert.assertEquals(obj.get("message").toString().replaceAll("\"",""),"Login Success");
        getTestCache().put("accessToken",obj.get("token").toString().replaceAll("\"",""));

    }


}