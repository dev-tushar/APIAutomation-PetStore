package api.test;

import api.endpoints.UserEndpoints;
import api.payloads.User;
import api.utilities.DataProviders;
import io.restassured.response.Response;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

public class DDTests {

    @Test(priority = 1, dataProvider = "excelData", dataProviderClass = DataProviders.class)
    public void testCreateUser_DD(String userId, String username, String fname,
                                  String lname, String email, String pwd, String phno) {

    User userPayload = new User();

    userPayload.setId(Integer.parseInt(userId));
    userPayload.setUsername(username);
    userPayload.setFirstName(fname);
    userPayload.setLastName(lname);
    userPayload.setEmail(email);
    userPayload.setPassword(pwd);
    userPayload.setPhone(phno);

    UserEndpoints.createUser(userPayload).then().statusCode(200);



    }

    @Test(priority =2, dataProvider = "excelData_usernames", dataProviderClass = DataProviders.class)
    public void testGetUsers_DD(String uname) {

        UserEndpoints.getUser(uname).then().statusCode(200);
    }

    @Test(priority =3, dataProvider = "excelData_usernames", dataProviderClass = DataProviders.class)
    public void testDeleteUsers_DD(String uname) {

        UserEndpoints.deleteUser(uname).then().statusCode(200);
    }
}
