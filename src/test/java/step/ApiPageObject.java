package step;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import static io.restassured.RestAssured.given;
//import lombok.Builder;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import java.util.ArrayList;
import java.util.List;

import org.testng.Assert;
//import step.User;
import java.util.ArrayList;

public class ApiPageObject {
    private String browser;
    private int threadCount;


    private List<User> users;
    private User user;
    private User userOne;

    @BeforeMethod
    public void setUp() {
        String threadCount = System.getProperty("threadCount", "1");
        String browser = System.getProperty("browser", "chrome");

        System.out.println("!!!!!!!!Thread Count: " + threadCount);
        System.out.println("Browser: " + browser);
        RestAssured.baseURI = "https://petstore.swagger.io/v2";
        users = new ArrayList<>();
        user = User.builder()
                .id(0)
                .username("Mari")
                .firstName("Maryna")
                .lastName("Mykhailova")
                .email("123@gmail.com")
                .password("1234")
                .phone("12345678")
                .userStatus(0)
                .build();
        userOne = User.builder()
                .id(1)
                .username("Bodya")
                .firstName("Ma")
                .lastName("lova")
                .email("23@gmail.com")
                .password("1")
                .phone("45678")
                .userStatus(1)
                .build();
        users.add(user);
        //  users.addAll(List.of(user, userOne));
    }

    @Test(priority = 1)
    public void createPost() {

        Response createResponse = given()
                .contentType("application/json")
                .body(users)
                .when()
                .post("/user/createWithArray");
        Assert.assertEquals(createResponse.getStatusCode(), 200, "Status code is not as expected");
    }

    @Test(priority = 2)
    public void getPost() {
        Response getResponse = RestAssured.given()
                .when()
                .get("/user/" + user.getUsername());
        Assert.assertEquals(getResponse.getStatusCode(), 200, "Status code is not as expected");
        Assert.assertTrue(getResponse.getBody().asString().contains(user.getUsername()));
        String responseBody = getResponse.getBody().asString();
        System.out.println("Array" + responseBody);
    }

    @Test(priority = 3)
    public void putPost() {
        Response putResponse = RestAssured.given()
                .log().all()
                .contentType("application/json")
                .body(userOne)
                .when()
                .put("/user/" + user.getUsername());
        Assert.assertEquals(putResponse.getStatusCode(), 200, "Status code is not as expected");
        String responseBody = putResponse.getBody().asString();
        System.out.println("Array" + responseBody);

    }

    @Test(priority = 4)
    public void getPostAgain() {
        Response getResponseAgain = RestAssured.given()
                .when()
                .get("/user/" + userOne.getUsername());
        Assert.assertEquals(getResponseAgain.getStatusCode(), 200, "Status code is not as expected");
        Assert.assertTrue(getResponseAgain.getBody().asString().contains(userOne.getFirstName()));
        String responseBody = getResponseAgain.getBody().asString();
        System.out.println("Array" + responseBody);
    }
    @Test(priority = 5)
    public void deletePost() {
        Response deleteResponse = RestAssured.given()
        .when()
        .delete("/user/" + userOne.getUsername());
        Assert.assertEquals(deleteResponse.getStatusCode(), 200, "Status code is not as expected");

    }
    @Test(priority = 6)
    public void getPostInformationAboutDeleteUser() {
       Response getPostResponse = RestAssured.given()
                .when()
               .get("/user/" + userOne.getUsername());

        Assert.assertEquals(getPostResponse.getStatusCode(), 404, "Status code is not as expected");
       Assert.assertFalse(getPostResponse.getBody().asString().contains(userOne.getUsername()));
}
}
