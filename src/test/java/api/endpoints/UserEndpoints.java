package api.endpoints;

import api.payloads.User;
import api.utilities.ConfigReader;
import io.restassured.http.ContentType;
import io.restassured.response.Response;

import static io.restassured.RestAssured.given;

public class UserEndpoints {

    static String baseURL = ConfigReader.get("base.url");

    public static Response createUser(User payload)
    {
        Response ro = given()
                .accept(ContentType.JSON)
                .contentType(ContentType.JSON)
                .body(payload)
                .when()
                .post(baseURL+ConfigReader.get("user.create"));
        return ro;
    }

    public static Response getUser(String username)
    {
        Response ro = given()
                .pathParam("username",username)
                .when()
                .get(baseURL+ConfigReader.get("user.get"));
        return ro;
    }

    public static Response updateUser(String username,  User payload)
    {
        Response ro = given()
                .accept(ContentType.JSON)
                .contentType(ContentType.JSON)
                .pathParam("username", username)
                .body(payload)
                .when()
                .put(baseURL+ConfigReader.get("user.update"));
        return ro;
    }

    public static Response deleteUser(String username)
    {
        Response ro = given()
                .pathParam("username",username)
                .when()
                .delete(baseURL+ConfigReader.get("user.delete"));
        return ro;
    }
}
