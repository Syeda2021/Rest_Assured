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

public class TTechDeleteGetTest extends BaseTest{

    ObjectMapper mapper = new ObjectMapper();

    @Test
    public void studentDeleteTest() throws JsonProcessingException {

        CommonData cdata=new CommonData();
        UtilitY.encodeConfig();
        Response response = UtilitY.callDeleteEndPointTTech(Method.DELETE, BaseTest.getTestCache().get("accessToken").toString(),cdata.baseURLTTech,cdata.endPointGetStudent+BaseTest.getTestCache().get("studentId").toString());
        System.out.println(response.getStatusCode());
        System.out.println(response.getBody().asString());
        JsonNode obj = mapper.readTree(response.getBody().asString());
        Assert.assertEquals(obj.get("messages").toString().replaceAll("\"",""),"Data Deleted");
    }

    @Test(dependsOnMethods = "studentDeleteTest")
    public void studentDeleteVerify() throws JsonProcessingException {

        CommonData cdata=new CommonData();
        UtilitY.encodeConfig();
        Response response = UtilitY.callGetEndPointTTech(Method.GET,cdata.baseURLTTech,cdata.endPointGetStudent+BaseTest.getTestCache().get("studentId").toString());

        System.out.println(response.getStatusCode());
        System.out.println(response.getBody().asString());
        Assert.assertEquals(response.getStatusCode(),404);

    }
}
