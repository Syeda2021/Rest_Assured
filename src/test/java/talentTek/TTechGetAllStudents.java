package talentTek;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.restassured.http.Method;
import io.restassured.response.Response;
import org.testng.annotations.Test;
import resourceS.CommonData;
import resourceS.UtilitY;

public class TTechGetAllStudents {
    ObjectMapper mapper = new ObjectMapper();

    @Test
    public void allStudentsInfo() throws JsonProcessingException {

        CommonData cdata=new CommonData();
        UtilitY.encodeConfig();
        Response response = UtilitY.callGetEndPointTTech(Method.GET,cdata.baseURLTTech,cdata.endPointAllStudents);

        System.out.println(response.getStatusCode());
        //System.out.println(response.getBody().asString());
        JsonNode obj = mapper.readTree(response.getBody().asString());
        //System.out.println(tree.get("data"));
        //System.out.println(obj.get("data").get(3));
        System.out.println(obj.get("data").get(2).get("email"));
        for(int i=0;i<obj.get("data").size();i++){
            System.out.println(obj.get("data").get(i).get("lastName"));
        }


    }

}
