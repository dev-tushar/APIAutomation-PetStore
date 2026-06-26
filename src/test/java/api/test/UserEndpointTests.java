package api.test;

import api.endpoints.UserEndpoints;
import api.payloads.User;
import com.github.javafaker.Faker;
import io.restassured.response.Response;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

public class UserEndpointTests {

    Faker fakedata;
    User user;

    Logger logger;

    @BeforeClass
    public void setup() {

        fakedata = new Faker();
        user = new User();

        user.setId(fakedata.idNumber().hashCode());
        user.setEmail(fakedata.internet().emailAddress());
        user.setPassword(fakedata.internet().password());
        user.setPhone(fakedata.phoneNumber().cellPhone());
        user.setFirstName(fakedata.name().firstName());
        user.setLastName(fakedata.name().lastName());
        user.setUsername(fakedata.howIMetYourMother().character());

        //logs
        logger = LogManager.getLogger(this.getClass());
    }

    @Test(priority=1)
    public void testCreateUser() {

        logger.info("********New user creation triggered********");
        Response response = UserEndpoints.createUser(user);
        response.then().log().all();
        Assert.assertEquals(response.getStatusCode(), 200);
        logger.info("********New user created********");

        }

    @Test(priority=2)
    public void testGetUserDetails() {

        Response response = UserEndpoints.getUser(this.user.getUsername());
        response.then().log().all();
        Assert.assertEquals(response.getStatusCode(), 200);

    }

    @Test(priority=3)
    public void testUpdateUser() {

        logger.info("********user update triggered********");
        user.setEmail("updated_"+fakedata.internet().emailAddress());
        Response response = UserEndpoints.updateUser(this.user.getUsername(),user);
        response.then().log().all();
        Assert.assertEquals(response.getStatusCode(), 200);
     //   UserEndpoints.getUser(this.user.getUsername()).then().body("email",equalTo(this.user.getEmail()));
        Response respAfterUpdate = UserEndpoints.getUser(this.user.getUsername());
        Assert.assertEquals(respAfterUpdate.jsonPath().get("email"), this.user.getEmail());
        System.out.println("Email successfully updated to: "+this.user.getEmail());
        logger.info("********user update done********");

    }

    @Test(priority = 4)
    public void testDeleteUser() {

        logger.info("********User deletion triggered********");
        UserEndpoints.deleteUser(this.user.getUsername()).then().statusCode(200);
        System.out.println("Successfully deleted user: "+this.user.getUsername());
        logger.info("********User deleted********");

    }


    @Test
    void reverseWordsInString()
    {
        String str = "Welcome to Java";

        String splitStr[] = str.split(" ");
        String revStr="";
        for(String s: splitStr) {
            String revWord = "";
            for(int i=s.length()-1;i>=0;i--) {
                revWord = revWord+s.charAt(i);
            }
            revStr=revStr+revWord+" ";
        }

        System.out.println(revStr);
    }

    @Test
    void numOfOccurrencesChar() {
        String str = "Welcome to Java";

        char c = 'e';
        int count=0;
        for(int i=0;i<str.length();i++) {

            if(str.charAt(i)==c)
                count++;
        }
        System.out.println(count+" is the number of occurences of "+c+" in given string");
    }

    @Test
    void numOfOccurrencesWord() {
        String str = "Welcome to Java Java";

        String c = "Java";

        String splitStr[] = str.split(" ");
        int count=0;

        for(String s: splitStr) {
            if(s.equals(c))
                count++;

        }
        System.out.println(count+" is the number of occurences of "+c+" in given string");
    }

    @Test
    void duplicateWords() {
        String str = "Find the duplicates in in string string string string string";

        String[] splitStr = str.split(" ");

        HashSet<String> set = new HashSet<>();
        List<String> toBeIgnored = new ArrayList<>();

        for(String s: splitStr) {
            if(!toBeIgnored.contains(s)) {
                if (!set.add(s)) {
                    toBeIgnored.add(s);
                    System.out.println("Found duplicate word " + s);
                }
            }
        }
        System.out.println(set);
    }

}
