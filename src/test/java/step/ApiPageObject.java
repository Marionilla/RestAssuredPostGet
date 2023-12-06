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

    private Response response;
    private List<User> users;
    @BeforeMethod
    public void setUp() {
        String threadCount = System.getProperty("threadCount", "1");
        String browser = System.getProperty("browser", "chrome");

        System.out.println("!!!!!!!!Thread Count: " + threadCount);
        System.out.println("Browser: " + browser);
        RestAssured.baseURI = "https://petstore.swagger.io/v2";
        users = new ArrayList<>();
        User user = User.builder()
                .id(0)
                .username("Mari")
                .firstName("Maryna")
                .lastName("Mykhailova")
                .email("123@gmail.com")
                .password("1234")
                .phone("12345678")
                .userStatus(0)
                .build();

        users.add(user);
    }

    @Test
    public void createPost() {

        given()
                .contentType("application/json")
                .body(users)
                .when()
                .post("/user/createWithList")
                .then()
                .statusCode(200);
    }
    @Test
    public void getPost() {

    }
    @Test
    public void putPost() {

    }
    @Test
    public void getPostAgain() {

    }
    @Test
    public void deletePost() {

    }
    @Test
    public void getPostInformationAboutDeleteUser() {

    }

}
