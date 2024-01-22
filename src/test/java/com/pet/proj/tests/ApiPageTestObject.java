package com.pet.proj.tests;
import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import step.User;
import com.pet.proj.tests.UserPage;
import java.util.ArrayList;
import java.util.List;

public class ApiPageTestObject {
    private List<User> users;
    private UserPage userPage;
    private User userNew;


    @BeforeMethod
    public void setUpUser() {
        RestAssured.baseURI = "https://petstore.swagger.io/v2";

        users = new ArrayList<>();
        userNew = User.builder()
                .id(0)
                .username("Mari")
                .firstName("Maryna")
                .lastName("Mykhailova")
                .email("123@gmail.com")
                .password("1234")
                .phone("12345678")
                .userStatus(0)
                .build();
        users.add(userNew);
        userPage = new UserPage(users);
        userPage.createUser();

    }

    @Test
    public void testCreateUser() {

        userPage.createUser();

    }

    @Test
    public void testGetUser() {

        userPage.getUser();

    }

    @Test
    public void testUpdateUser() {
       // User user = userPage.getCreatedUser();
//User usero = users.get(0);
        User updatedUser = User.builder()
                .id(1)
                .username("Bodya")
                .firstName("Ma")
                .lastName("lova")
                .email("23@gmail.com")
                .password("1")
                .phone("45678")
                .userStatus(1)
                .build();
        userPage.updateUser(updatedUser);
        // Додайте перевірки для оновленого користувача
    }
    @Test
    public void testGetUserAgain() {
        userPage.getUser();
    }
    @Test
    public void testDeleteUser() {
        userPage.deleteUser();
    }
}

