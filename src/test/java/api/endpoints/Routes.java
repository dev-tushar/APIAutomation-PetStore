package api.endpoints;

public class Routes {

    public static final String baseURL = "https://petstore.swagger.io/v2";

    //User module endpoints:
    public static  String getUserURL = baseURL+ "/user/{username}";
    public static  String postUserURL =baseURL+ "/user";
    public static  String updateUserURL = baseURL+ "/user/{username}";
    public static  String deleteUserURL = baseURL+ "/user/{username}";
}
