package resourceS;

import com.github.javafaker.Faker;

import java.time.LocalDateTime;

public class CommonData {

    static Faker faker = new Faker();

    public static String baseURLTTech="http://qa.taltektc.com/";
    public static String endPointSignUp="api/signup";
    public static String endPointGetStudent="api/student/";
    public static String endPointStudentLogin="api/login";
    public static String endPointStudentUpdatePassword="api/updatePassword/";
    public static String endPointAllStudents="api/students";


    public int rand=faker.number().numberBetween(100,999);
    public String firstName=faker.name().firstName();
    public String lastName=faker.name().lastName();
    public String email="Test2"+rand+"@gmail.com";
    public String password="Test99"+rand;
    public String updatePassword="Update11"+rand;
    public int year= LocalDateTime.now().minusMonths(120).getYear();
    public int month=LocalDateTime.now().getMonthValue();
    public int day=LocalDateTime.now().getDayOfMonth();

    public String signUpBody(String userEmail,String userPassword){
        String body="{\n" +
                "    \"firstName\" : \""+firstName+"\",\n" +
                "    \"lastName\" : \""+lastName+"\",\n" +
                "    \"email\"     : \""+userEmail+"\",\n" +
                "    \"password\"  : \""+userPassword+"\",\n" +
                "    \"confirmPassword\"  : \""+userPassword+"\",\n" +
                "    \"dob\"       : {\n" +
                "        \"year\"      : "+year+",\n" +
                "        \"month\"     : "+month+",\n" +
                "        \"day\"       : "+day+"\n" +
                "    },\n" +
                "    \"gender\"    : \"male\",\n" +
                "    \"agree\"     : true\n" +
                "}";
        return body;

    }

    public String loginBody(String studentEmail, String studentPassword){

        String body="{\n" +
                "    \"email\" : \""+studentEmail+"\",\n" +
                "    \"password\" : \""+studentPassword+"\"\n" +
                "}";
        return body;
    }

    public String passwordPatchBody(String password){

        String body="{\n" +
                "    \"password\"  : \""+password+"\",\n" +
                "    \"confirmPassword\"  : \""+password+"\"\n" +
                "}";
        return body;
    }

    public String studentPutBody(String email){

        String body="{\n" +
                "    \"firstName\" : \""+firstName+"\",\n" +
                "    \"lastName\" : \""+lastName+"\",\n" +
                "    \"email\"     : \""+email+"\",\n" +
                "    \"dob\"       : {\n" +
                "        \"year\"      : 2013,\n" +
                "        \"month\"     : 12,\n" +
                "        \"day\"       : 31\n" +
                "    },\n" +
                "    \"gender\"    : \"male\",\n" +
                "    \"agree\"     : true\n" +
                "}";
        return body;
    }



}
